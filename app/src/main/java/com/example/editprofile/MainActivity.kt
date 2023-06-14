package com.example.editprofile

import android.media.MediaCodec.CryptoInfo.Pattern
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.editprofile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.name.observe(this, Observer {

            fun validPattern():String? {
                val userName = binding.Username.text.toString()
                if (!Patterns.EMAIL_ADDRESS.matcher(userName).matches()){
                    return "not correct"
                }
                return null
            }

            binding.Username.setOnFocusChangeListener{_, focused->
                if (!focused){
                    binding.UsernameLayout.helperText =validPattern()

                }

            }

            var button =binding.button
            var progressBar = binding.progressBar
            binding.button.setOnClickListener{
                button.isSelected != button.isSelected
                progressBar.visibility = View.VISIBLE

                binding.switch1.setOnCheckedChangeListener{ buttonView, isChecked ->
                    button.isEnabled = isChecked

                }
            }
        })
        viewModel.getName()

    }




    }
