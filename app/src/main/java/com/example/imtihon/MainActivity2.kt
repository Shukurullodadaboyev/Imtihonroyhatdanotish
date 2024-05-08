package com.example.imtihon

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.imtihon.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        getData()

        binding.save.setOnClickListener {
            saveData()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        saveData()
    }

    private fun saveData() {
        val name = binding.surname.text.toString().trim()
        val surname = binding.surname.text.toString().trim()
        val phone_number = binding.number.text.toString().trim()
        val age = binding.age.text.toString().trim()
        val nickname = binding.nickName.text.toString().trim()
        if (name.isNotEmpty() && surname.isNotEmpty() && phone_number.isNotEmpty() && age.isNotEmpty() && nickname.isNotEmpty()) {
            val sharedPreferences = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("name", name)
            editor.putString("surname", surname)
            editor.putString("number", phone_number)
            editor.putString("age", age)
            editor.putString("nickname", nickname)
            editor.apply()

        } else {
            binding.nickName.error = "To`ldirilmagan!"
            binding.nickName.isFocusable = true
            binding.surname.error = "To'ldirilmagan"
            binding.surname.isFocusable = true
            binding.number.error = "To'ldirilmagan"
            binding.number.isFocusable = true
            binding.age.error = "To'ldirilmagan"
            binding.age.isFocusable = true
            binding.nickName.error = "To'ldirilmagan"
            binding.nickName.isFocusable = true

        }
    }

    private fun getData() {
        val sharedPreferences = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "empty")
        val surname = sharedPreferences.getString("surname", "empty")
        val age = sharedPreferences.getString("age", "empty")
        val number = sharedPreferences.getString("number", "empty")
        val nickname = sharedPreferences.getString("nickname", "empty")
        if (name != "empty") {
            binding.name.setText(name)
            binding.surname.setText(surname)
            binding.age.setText(age)
            binding.number.setText(number)
            binding.nickName.setText(nickname)
        }

    }

    private fun deleteData() {
        val sharedPreferences = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("user")
        editor.remove("name")
        editor.remove("surname")
        editor.remove("age")
        editor.remove("number")
        editor.remove("nickname")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {
                deleteData()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
}