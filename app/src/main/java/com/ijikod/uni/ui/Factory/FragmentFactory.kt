package com.ijikod.uni.ui.Factory

import androidx.fragment.app.FragmentFactory
import com.ijikod.uni.ui.Fragments.ContentFeedFragment

class FragmentFactory : FragmentFactory() {

    override fun instantiate(
        classLoader: ClassLoader,
        className: String) = when(className){
        ContentFeedFragment::class.java.name -> ContentFeedFragment()
        else -> {
            super.instantiate(classLoader, className)
        }
    }
}