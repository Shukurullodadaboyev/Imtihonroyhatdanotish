package com.example.imtihon

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.imtihon.databinding.ActivityMainBinding
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    var hidding = true
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.next.setOnClickListener {
            if (checkOldSignIn()) {
                goNextAct()
            } else {
                checkLogins()
            }
        }

        // image view bosilishi
       /*
        binding.hide.setOnClickListener {
            if (hidding == true) {
                binding.hide.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.security))
                binding.btnParol.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                hidding = false
            } else {
                binding.btnParol.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.hide.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.hide))
                hidding = true
            }
        }
        */

    }

    private fun goNextAct() {
        val intent = Intent(this,MainActivity2::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkOldSignIn(): Boolean {
        val sharedPreferences = getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
        val data = sharedPreferences.getString("user", "empty")
        if (data != "empty") {
            return true
        } else {
            return false
        }
    }

    private fun checkLogins() {
        val login = binding.btnlogin.toString().trim()
        val password = binding.btnParol.text.toString().trim()
        if (login.isNotEmpty() && password.isNotEmpty()) {
            val gson = Gson()
            val sharedPreferences = getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
            val foydalanuvchi = Foydalanuvchi(login, password)
            val jsonKlass = gson.toJson(foydalanuvchi)
            val editor = sharedPreferences.edit()
            editor.putString("user", jsonKlass)
            editor.apply()
            goNextAct()
            Toast.makeText(this, "Saqlandi!", Toast.LENGTH_SHORT).show()

        } else if (login.isEmpty() && password.isNotEmpty()) {
            binding.btnlogin.error = "To`ldirilmagan!"
            binding.btnParol.isFocusable = true
        } else if (login.isNotEmpty() && password.isEmpty()) {
            binding.btnParol.error = "To`ldirilmagan"
            binding.btnParol.isFocusable = true
        } else {
            Toast.makeText(this, "Ma`lumotlarni to`ldiring!", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onResume() {
        super.onResume()
        if (checkOldSignIn()) {
            goNextAct()
        }
    }
}