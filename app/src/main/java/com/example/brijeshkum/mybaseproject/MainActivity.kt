package com.example.brijeshkum.mybaseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.brijeshkum.mybaseproject.ui.MainViewModel
import com.example.brijeshkum.mybaseproject.ui.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mViewModelFactory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApp).appComponent!!.inject(this)
        val viewModel by viewModels<MainViewModel>{ mViewModelFactory }
        viewModel.listContact!!.observe(this, Observer { contacts ->
            if (contacts != null) {
                val builder = StringBuilder()
                for (contact in contacts) {
                    builder.append(contact?.firstName)
                    builder.append(", ")
                }
            }
            //Toast.makeText(MainActivity.this, "Got Total Data "+contacts.size(), Toast.LENGTH_SHORT).show();
        })
    }
}