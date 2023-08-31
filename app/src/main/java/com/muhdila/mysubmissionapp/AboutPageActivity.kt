package com.muhdila.mysubmissionapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class AboutPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_page)

        // Setting color status bar and navigator bar
        NavBarColor.setStatusBarAndNavBarColors(this)

        // Setting action bar
        val actionBar = supportActionBar
        actionBar?.title = "About Me"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val btnEmail = findViewById<ImageButton>(R.id.btn_about_email)
        val btnGithub = findViewById<ImageButton>(R.id.btn_about_github)
        val btnYoutube = findViewById<ImageButton>(R.id.btn_about_youtube)
        val btnShare = findViewById<ImageButton>(R.id.btn_about_share)

        // Set click listener on the Email link to open the link in a browser
        btnEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto: muhhdila21@gmail.com"))
            startActivity(intent)
        }

        // Set click listener on the Github link to open the link in a browser
        btnGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MuhDila"))
            startActivity(intent)
        }

        // Set click listener on the Youtube link to open the link in a browser
        btnYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@muhammaddila1560"))
            startActivity(intent)
        }

        // Set click listener on the Share link to open the link in a browser
        btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, "Check out my GitHub profile: https://github.com/MuhDila")
            intent.setType("text/plain")
            intent.setPackage("com.whatsapp")
            startActivity(intent)
        }
    }

    // Action bar function
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish() // Close the current activity and go back
                return true
            }
            // Handle other menu items if needed
        }
        return super.onOptionsItemSelected(item)
    }
}