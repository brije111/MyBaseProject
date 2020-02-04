package com.example.brijeshkum.mybaseproject.di

import android.app.Application
import androidx.room.Room
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.brijeshkum.mybaseproject.NetManager
import com.example.brijeshkum.mybaseproject.db.MyRoomDatabase
import com.example.brijeshkum.mybaseproject.db.Repository
import com.example.brijeshkum.mybaseproject.db.WebServices
import com.example.brijeshkum.mybaseproject.ui.ViewModelFactory
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.concurrent.*
import javax.inject.Singleton

@Module
class NetModule // Constructor needs one parameter to instantiate.
(var mBaseUrl: String) {
    // Dagger will only look for methods annotatRepositoryed with @Provides
    @Provides
    @Singleton
    fun providesSharedPreferences(application: Application?): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache?): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.cache(cache)
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
// See http://square.github.io/okhttp/3.x/logging-interceptor/ to see the options.
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        client.networkInterceptors().add(httpLoggingInterceptor)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson?, okHttpClient: OkHttpClient?): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build()
        //return retrofit;
    }

    @Provides
    @Singleton
    fun provideApiEndpointInterface(retrofit: Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }

    @Provides
    @Singleton
    fun provideSimpleDateFormat(): SimpleDateFormat {
        return SimpleDateFormat("dd MM yyyy HH:mm:ss")
    }

    @Provides
    @Singleton
    fun provideObjectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        //objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return objectMapper
    }

    @Provides
    @Singleton
    fun provideNetManager(application: Application): NetManager {
        return NetManager(application.applicationContext)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application?): MyRoomDatabase {
        return Room.databaseBuilder(application!!, MyRoomDatabase::class.java, "my_db").build()
    }

    @Provides
    @Singleton
    fun provideExecutor(): Executor { /*
       * Gets the number of available cores
       * (not always the same as the maximum number of cores)
       */
        val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()
        // Sets the amount of time an idle thread waits before terminating
        val KEEP_ALIVE_TIME = 1
        // Sets the Time Unit to seconds
        val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
        // A queue of Runnables
        val mDecodeWorkQueue: BlockingQueue<Runnable>
        // Instantiates the queue of Runnables as a LinkedBlockingQueue
        mDecodeWorkQueue = LinkedBlockingQueue()
        // Creates a thread pool manager
        return ThreadPoolExecutor(
                NUMBER_OF_CORES,  // Initial pool size
                NUMBER_OF_CORES,  // Max pool size
                KEEP_ALIVE_TIME.toLong(),
                KEEP_ALIVE_TIME_UNIT,
                mDecodeWorkQueue)
    }

    @Provides
    @Singleton
    fun provideRepository(executor: Executor, myRoomDatabase: MyRoomDatabase, webServices: WebServices): Repository {
        return Repository(executor, myRoomDatabase, webServices)
    }

    @Provides
    fun provideViewModelFactory(repository: Repository?): ViewModelFactory {
        return ViewModelFactory(repository)
    }

}