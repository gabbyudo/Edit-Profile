package com.example.editprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.editprofile.database.UserInfo
import com.example.editprofile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = MainViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
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
        binding.saveButton.setOnClickListener{
            val addUsername = UserInfo(binding.username.text.toString(),
                binding.password.text.toString())
           viewModel.addUser(addUsername)
        }
        viewModel.viewState.observe(this) {
            if (it.isInitialState) {
                    binding.username.setText(it.email)
                    binding.password.setText(it.password)
                    binding.progressBar.visibility = View.GONE
                }
                binding.usernameLayout.error = it.errorMessage
                binding.passwordLayout.error =it.errorMessagePassword
                binding.saveButton.isEnabled = it.isSaveButtonEnabled
            }

        viewModel.getUser()
    }
}
