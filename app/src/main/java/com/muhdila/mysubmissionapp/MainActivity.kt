package com.muhdila.mysubmissionapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvSinger: RecyclerView
    private val list = ArrayList<Singer>()

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting color status bar and navigator bar
        NavBarColor.setStatusBarAndNavBarColors(this)

        // Initialize RecyclerView and set its fixed size
        rvSinger = findViewById(R.id.rv_singer)
        rvSinger.setHasFixedSize(true)

        // Populate the list with singer data
        list.addAll(getListSinger())
        // Set up RecyclerView to display the list
        showRecyclerList()

        // Setting action bar
        val actionBar = supportActionBar
        actionBar?.title = " Sing-Er's"
        supportActionBar?.setLogo(R.drawable.genius_mini)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayUseLogoEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu resource and add it to the options menu
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Action bar function
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle menu item clicks
        when (item.itemId) {
            R.id.action_list -> {
                // Set layout manager to LinearLayoutManager (List)
                rvSinger.layoutManager = LinearLayoutManager(this)
            }

            R.id.action_grid -> {
                // Set layout manager to GridLayoutManager (Grid)
                rvSinger.layoutManager = GridLayoutManager(this, 2)
            }

            R.id.about_page -> {
                // Intent into about page
                val moveIntoAboutPage = Intent(this, AboutPageActivity::class.java)
                startActivity(moveIntoAboutPage)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListSinger(): ArrayList<Singer> {
        // Get arrays from resources
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val dataSpotify = resources.getStringArray(R.array.data_link_spotify)
        val dataYoutube = resources.getStringArray(R.array.data_link_youtube)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listSingers = ArrayList<Singer>()
        // Create Singer instances and add to list
        for (i in dataName.indices) {
            val singer = Singer(dataName[i], dataDescription[i], dataGenre[i], dataSpotify[i], dataYoutube[i], dataPhoto.getResourceId(i, -1))
            listSingers.add(singer)
        }
        return listSingers
    }

    private fun showRecyclerList() {
        // Set LinearLayoutManager to the RecyclerView
        rvSinger.layoutManager = LinearLayoutManager(this)
        // Create and set the adapter
        val listSingerAdapter = ListSingerAdapter(list)
        rvSinger.adapter = listSingerAdapter

        // Set item click callback
        listSingerAdapter.setOnItemClickCallback(object : ListSingerAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Singer) {
                // Handle item click by navigating to DetailActivity
                showSelectedSinger(data)
            }
        })
    }

    private fun showSelectedSinger(singer: Singer) {
        list.addAll(getListSinger())
        // Create an intent with singer data, start DetailActivity, and show a toast message
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("singer", singer)
        startActivity(intent)
        Toast.makeText(this, "You choose " + singer.name, Toast.LENGTH_SHORT).show()
    }
}