package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.ui.DarkFragment
import com.example.myapplication.ui.LightFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menuLight -> openLight()
                R.id.menuDark -> openDark()
            }
            true
        }
    }

    fun openLight() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, LightFragment())
            .commit()
    }

    fun openDark() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, DarkFragment())
            .commit()
    }
}