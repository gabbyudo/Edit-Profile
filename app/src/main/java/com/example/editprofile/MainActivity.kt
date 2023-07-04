package com.example.editprofile

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
        binding.progressBar.visibility = View.VISIBLE

        binding.username.doAfterTextChanged {
            viewModel.onEmailChangeOrProfileVisibilityChanged(
                it.toString(), binding.password.text.toString(), binding.switch1.isChecked
            )
        }
        binding.password.doAfterTextChanged {
            viewModel.onEmailChangeOrProfileVisibilityChanged(
                binding.username.text.toString(), it.toString(), binding.switch1.isChecked
            )
        }

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEmailChangeOrProfileVisibilityChanged(
                binding.username.text.toString(), binding.password.text.toString(), isChecked
            )
        }

        viewModel.viewState.observe(this) {
            if (it.isInitialState) {
                binding.username.setText(it.email)
                binding.password.setText(it.password)
                binding.progressBar.visibility = View.GONE
            }
            binding.usernameLayout.error = it.errorMessage
        }

        viewModel.getUser()

        viewModel.isSaveButtonEnabled.observe(this, Observer {
            binding.saveButton.isEnabled = it
        })

        viewModel.errorMessagePassword.observe(this, Observer {
            binding.passwordLayout.error = it
        })
    }
}
