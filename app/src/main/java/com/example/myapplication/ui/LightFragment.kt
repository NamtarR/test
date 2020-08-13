package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.data.LightState
import com.example.myapplication.web.WebClient
import kotlinx.android.synthetic.main.fragment_light.*
import kotlinx.coroutines.launch

class LightFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_light, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        update()
        lightButton.setOnClickListener {
            lifecycleScope.launch {
                WebClient.setLightState(
                    LightState(!lamp, 1000, 2000)
                )
                update()
            }
        }

        back.setOnClickListener {
            (activity as? MainActivity)?.back()
        }
    }

    var lamp: Boolean = false

    fun update() {
        lifecycleScope.launch {
            val state = WebClient.getLightState()
            lamp = state.lamp
            if (lamp) {
                lightButton.text = "Выключить"
            } else {
                lightButton.text = "Включить"
            }
        }
    }
}