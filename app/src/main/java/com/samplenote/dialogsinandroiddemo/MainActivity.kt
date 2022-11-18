package com.samplenote.dialogsinandroiddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.samplenote.dialogsinandroiddemo.Utils.showToast
import com.samplenote.dialogsinandroiddemo.Utils.validateLogin
import com.samplenote.dialogsinandroiddemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            btnLogin.setOnClickListener {
                val email = EditTextEmail.text.toString()
                val password = EditTextPassword.text.toString()
                if (validateLogin(email, password)) {
                    doLogin()
                } else {
                    showToast(this@MainActivity, getString(R.string.invalid_cred))
                }
            }
        }
    }

    private fun doLogin() {
        showToast(this@MainActivity, getString(R.string.login_success))
    }
}