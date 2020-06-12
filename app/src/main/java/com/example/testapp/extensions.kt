package com.example.testapp

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.doTransaction(func: FragmentTransaction.() -> FragmentTransaction) = this.beginTransaction().func().commit()
