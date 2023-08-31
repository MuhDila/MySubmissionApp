package com.muhdila.mysubmissionapp

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val buttonImageView = findViewById<ImageButton>(R.id.btn_detail_photo)

        // Setting color status bar and navigator bar
        NavBarColor.setStatusBarAndNavBarColors(this)

        // Setting action bar
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // Retrieve the Parcelable extra named "singer" from the intent that started this activity
        val singer = intent.getParcelableExtra<Singer>("singer")

        // Set click listener on the photo
        buttonImageView.setOnClickListener {
            if (singer != null) {
                showFullSizePhotoDialog(singer.photo)
            }
        }

        // Check if the retrieved singer object is not null
        if (singer != null) {
            // Find the TextViews and ImageView in the layout using their IDs
            val nameTextView = findViewById<TextView>(R.id.tv_detail_name)
            val descriptionTextView = findViewById<TextView>(R.id.tv_detail_description)
            val photoImageView = findViewById<ImageView>(R.id.img_detail_photo)
            val genreTextView = findViewById<TextView>(R.id.tv_detail_genre)
            val btnSpotify = findViewById<ImageButton>(R.id.btn_spotify)
            val btnYoutube = findViewById<ImageButton>(R.id.btn_youtube)

            // Set the TextViews and ImageView with the data from the singer object
            nameTextView.text = singer.name
            descriptionTextView.text = singer.description
            photoImageView.setImageResource(singer.photo)
            genreTextView.text = singer.genre

            // Set action bar title
            supportActionBar?.title = singer.name

            // Set click listener on the Spotify link to open the link in a browser
            btnSpotify.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(singer.linkSpotify))
                startActivity(intent)
            }

            // Set click listener on the Youtube link to open the link in a browser
            btnYoutube.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(singer.linkYoutube))
                startActivity(intent)
            }
        }
    }

    private fun showFullSizePhotoDialog(photoResourceId: Int) {
        // Create a custom dialog
        val dialog = Dialog(this, R.style.CustomDialogStyle)
        dialog.setContentView(R.layout.dialog_photo)

        // Find the ImageView in the dialog layout
        val dialogPhotoImageView = dialog.findViewById<ImageView>(R.id.dialog_photo)
        val closePhotoImageView = dialog.findViewById<ImageButton>(R.id.btn_close_dialog)

        // Set the clicked photo as the source for the dialog's ImageView
        dialogPhotoImageView.setImageResource(photoResourceId)

        // Set click listener to dismiss the dialog when the photo in the dialog is clicked
        closePhotoImageView.setOnClickListener {
            dialog.dismiss()
        }

        // Show the dialog
        dialog.show()
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