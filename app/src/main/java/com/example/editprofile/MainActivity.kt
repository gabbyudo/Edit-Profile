package com.example.editprofile

import android.media.MediaCodec.CryptoInfo.Pattern
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.editprofile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.name.observe(this, Observer {
            binding.username.setText(it)
           binding.username.doAfterTextChanged {
               binding.button.isEnabled =
                   Patterns.EMAIL_ADDRESS.matcher(binding.username.text.toString()).matches()
           }

           /* binding.username.setOnFocusChangeListener{_, focused->
                if (!focused){
                    binding.usernameLayout.helperText =validPattern()
                }
            }*/
            /*binding.button.setOnClickListener{
                it.isSelected != it.isSelected

            }*/
        })
        binding.switch1.setOnCheckedChangeListener{ buttonView, isChecked ->
           // it.isEnabled = isChecked
        }
        //binding.progressBar.visibility = View.VISIBLE
        viewModel.getName()
    }
    /*fun validPattern():String? {
        val userName = binding.username.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(userName).matches()){
            return "not correct"
        }
        return null
    }*/

}
