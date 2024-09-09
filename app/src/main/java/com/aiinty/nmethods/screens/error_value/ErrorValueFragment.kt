package com.aiinty.nmethods.screens.error_value

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aiinty.nmethods.R

private const val TAG = "ErrorFragment"

class ErrorValueFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_error_value, container, false)

        return view
    }

    companion object {
        fun newInstance(): ErrorValueFragment {
            return ErrorValueFragment()
        }
    }
}