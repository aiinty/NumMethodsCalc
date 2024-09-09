package com.aiinty.nmethods.screens

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.aiinty.nmethods.NumericalMethods
import com.aiinty.nmethods.R
import com.aiinty.nmethods.screens.error_value.ErrorValueFragment
import com.aiinty.nmethods.screens.operations.OperationsFragment
import java.math.BigDecimal

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var errorButton: Button
    private lateinit var operationsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            loadFragment(ErrorValueFragment())
        }

        errorButton = findViewById(R.id.error_button)
        operationsButton = findViewById(R.id.operations_button)

        errorButton.setOnClickListener { _ -> loadFragment(ErrorValueFragment.newInstance()) }
        operationsButton.setOnClickListener { _ -> loadFragment(OperationsFragment.newInstance()) }
    }

    private fun loadFragment(f: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, f)
            .commit()
    }
}