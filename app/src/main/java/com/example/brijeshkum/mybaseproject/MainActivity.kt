package com.example.brijeshkum.mybaseproject

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import com.example.brijeshkum.mybaseproject.databinding.ActivityMainBinding
import com.example.brijeshkum.mybaseproject.ui.MainViewModel
import com.example.brijeshkum.mybaseproject.ui.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //@JvmField
    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApp).appComponent!!.inject(this)
        //setContentView(R.layout.activity_main);
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //init View Model
        //val viewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel::class.java)
        val viewModel by viewModels<MainViewModel>{ mViewModelFactory }
        binding.viewModel = viewModel
        //binding.setLifecycleOwner(this);
        setSupportActionBar(binding.appBarMain.toolbar)
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        val toggle = ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.appBarMain.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        binding.navView.setNavigationItemSelectedListener(this)
        //load data
        viewModel.listContact!!.observe(this, Observer { contacts ->
            if (contacts != null) {
                val builder = StringBuilder()
                for (contact in contacts) {
                    builder.append(contact?.firstName)
                    builder.append(", ")
                }
                binding.appBarMain.contentMain.txt.text = "Total contacts\n\n $builder"
            }
            //Toast.makeText(MainActivity.this, "Got Total Data "+contacts.size(), Toast.LENGTH_SHORT).show();
        })

        binding.appBarMain.contentMain.home.setOnClickListener {
            //startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as androidx.drawerlayout.widget.DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean { // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.nav_camera) { // Handle the camera action
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as androidx.drawerlayout.widget.DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}