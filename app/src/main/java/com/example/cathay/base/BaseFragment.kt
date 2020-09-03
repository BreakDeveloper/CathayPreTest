package com.example.cathay.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutId, container, false)

    fun initToolbar(toolbar: Toolbar, isDisplay: Boolean) {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(isDisplay)
                setHomeButtonEnabled(isDisplay)
            }
        }
    }
}