package com.app.gitrepositories.utils

import android.content.Context
import android.view.View
import androidx.core.util.Pair
import com.app.gitrepositories.R
import java.util.*

/**
 * Created by mayank on October 11 2019
 */
object AnimUtils {

        fun getTransitionElements(
            context: Context,
            imageView: View,
            titleView: View
        ): Array<Pair<View, String>> {

            val pairs = ArrayList<Pair<View, String>>()
            pairs.add(Pair(imageView, context.getString(R.string.transition_image)))
            pairs.add(Pair(titleView, context.getString(R.string.transition_title)))

            return pairs.toTypedArray()
        }
}