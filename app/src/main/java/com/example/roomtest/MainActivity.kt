package com.example.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.*
import com.example.roomtest.db.UserDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //AppBar에 xml파일명을 넣음
        setupActionBarWithNavController(findNavController(R.id.fragment))

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    //asdf
}