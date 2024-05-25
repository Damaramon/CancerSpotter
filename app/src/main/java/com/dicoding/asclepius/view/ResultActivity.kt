package com.dicoding.asclepius.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityResultBinding
class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil nilai URI dan hasil klasifikasi dari intent
        val imageUriString = intent.getStringExtra(EXTRA_IMAGE_URI)
        val hasil = intent.getStringExtra(EXTRA_RESULT)

        // Periksa nullability
        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            binding.resultImage.setImageURI(imageUri)
        } else {
            Log.e("ResultActivity", "Image URI is null")
        }

        if (hasil != null) {
            binding.resultText.text = hasil
        } else {
            Log.e("ResultActivity", "Result is null")
        }
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT = "extra_result"
    }
}
