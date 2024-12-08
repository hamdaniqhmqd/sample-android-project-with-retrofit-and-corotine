package com.tugas.coba_api_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tugas.coba_api_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_layout, TransaksiFragment())
                .commit()
        }
    }
}