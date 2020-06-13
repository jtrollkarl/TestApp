package com.example.testapp

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.doTransaction(func: FragmentTransaction.() -> FragmentTransaction) = this.beginTransaction().func().commit()

fun Fragment.showToast(@StringRes stringRes: Int) = Toast.makeText(requireActivity(), stringRes, Toast.LENGTH_SHORT).show()

fun Fragment.showToast(msg: String) = Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()