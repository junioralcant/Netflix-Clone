package com.example.netflixclone

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.example.netflixclone.databinding.ActivityVideoBinding
import java.net.URI

class Video : AppCompatActivity() {

    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val videoUrl = Uri.parse("https://firebasestorage.googleapis.com/v0/b/netflixclone-86e06.appspot.com/o/THE%20WITCHER%20_%20TRAILER%20FINAL%20_%20NETFLIX.mp4?alt=media&token=66779dd2-ef84-4942-a1d6-c28e5b89c92c")

        val video = binding.video
        video.setMediaController(MediaController(this))
        video.setVideoURI(videoUrl)
        video.requestFocus()
        video.start()
    }
}