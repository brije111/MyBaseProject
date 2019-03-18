package com.example.brijeshkum.mybaseproject.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.brijeshkum.mybaseproject.NetManager;
import com.example.brijeshkum.mybaseproject.db.MyRoomDatabase;
import com.example.brijeshkum.mybaseproject.db.WebServices;
import com.example.brijeshkum.mybaseproject.db.Repository;
import com.example.brijeshkum.mybaseproject.ui.ViewModelFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    String mBaseUrl;

    // Constructor needs one parameter to instantiate.
    public NetModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    // Dagger will only look for methods annotatRepositoryed with @Provides
    @Provides
    @Singleton
    // Application reference must come from AppModule.class
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
        // See http://square.github.io/okhttp/3.x/logging-interceptor/ to see the options.
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.networkInterceptors().add(httpLoggingInterceptor);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        //return retrofit;
    }



    @Provides
    @Singleton
    WebServices provideApiEndpointInterface(Retrofit retrofit) {
        return retrofit.create(WebServices.class);
    }

    @Provides
    @Singleton
    SimpleDateFormat provideSimpleDateFormat() {
        return new SimpleDateFormat("dd MM yyyy HH:mm:ss");
    }

    @Provides
    @Singleton
    ObjectMapper provideObjectMapper() {
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return objectMapper;
    }

    @Provides
    @Singleton
    NetManager provideNetManager(Application application){
        return new NetManager(application.getApplicationContext());
    }

    @Provides
    @Singleton
    MyRoomDatabase provideAppDatabase(Application application){
        return Room.databaseBuilder(application, MyRoomDatabase.class, "my_db").build();
    }

    @Provides
    @Singleton
    Executor provideExecutor(){
      /*
       * Gets the number of available cores
       * (not always the same as the maximum number of cores)
       */
      int NUMBER_OF_CORES =
          Runtime.getRuntime().availableProcessors();
      // Sets the amount of time an idle thread waits before terminating
      int KEEP_ALIVE_TIME = 1;
      // Sets the Time Unit to seconds
      TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
      // A queue of Runnables
      BlockingQueue<Runnable> mDecodeWorkQueue;
      // Instantiates the queue of Runnables as a LinkedBlockingQueue
      mDecodeWorkQueue = new LinkedBlockingQueue<>();
      // Creates a thread pool manager
        return new ThreadPoolExecutor(
            NUMBER_OF_CORES,       // Initial pool size
            NUMBER_OF_CORES,       // Max pool size
            KEEP_ALIVE_TIME,
            KEEP_ALIVE_TIME_UNIT,
            mDecodeWorkQueue);
    }

    @Provides
    @Singleton
    Repository provideRepository(Executor executor, MyRoomDatabase myRoomDatabase, WebServices
            webServices){
        return new Repository(executor, myRoomDatabase, webServices);
    }

    @Provides
    ViewModelFactory provideViewModelFactory(Repository repository) {
      return new ViewModelFactory(repository);
    }

}
