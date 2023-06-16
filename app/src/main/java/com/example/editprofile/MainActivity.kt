package com.example.editprofile

import android.media.MediaCodec.CryptoInfo.Pattern
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.editprofile.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.progressBar.visibility = View.VISIBLE
        binding.username.doAfterTextChanged {
            binding.button.isEnabled =
                Patterns.EMAIL_ADDRESS.matcher(binding.username.text.toString()).matches()

            if (!Patterns.EMAIL_ADDRESS.matcher(binding.username.text.toString()).matches())
            {  binding.username.error = "not correct"}
        }
        viewModel.name.observe(this, Observer {
            binding.username.setText(it)

            binding.progressBar.visibility = View.GONE

        })
        binding.switch1.setOnCheckedChangeListener{ buttonView, isChecked ->
           // it.isEnabled = isChecked
        }
        viewModel.getName()
    }


}
