/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder class for item. extend and init RecyclerView.ViewHolder() */
class LetterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var button: Button = view.findViewById(R.id.button_item)
}

/**
 * Adapter for the [RecyclerView] in [MainActivity].
 * must implement three methods (getItemCount, onCreate and onBind ViewHolder)
 * must has a var which is list. (we use livedata in another project)
 */
class LetterAdapter : RecyclerView.Adapter<LetterViewHolder>() {
    private var list = ('A' .. 'Z').toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val letterItem = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return LetterViewHolder(letterItem)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item: Char = list[position]
        holder.button.text = item.toString() // Char can .toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

// class LetterAdapter :
//     RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {
//
//     // Generates a [CharRange] from 'A' to 'Z' and converts it to a list
//     private val list = ('A'..'Z').toList()
//
//     /**
//      * Provides a reference for the views needed to display items in your list.
//      * 1. viewholder
//      * 2. constructor takes a view
//      * 3. field is the button of the view. TODO: what's the usage of this button?
//      */
//     class LetterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//         val button = view.findViewById<Button>(R.id.button_item)
//     }
//
//     override fun getItemCount(): Int {
//         return list.size
//     }
//
//     /**
//      * Creates new views with R.layout.item_view as its template
//      * 1. inflate item
//      * 2. create and return viewholder
//      */
//     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
//         val layout = LayoutInflater
//                 .from(parent.context)
//                 .inflate(R.layout.item_view, parent, false)
//         // Setup custom accessibility delegate to set the text read
//         layout.accessibilityDelegate = Accessibility
//         return LetterViewHolder(layout)
//     }
//
//     /**
//      * Replaces the content of an existing view with new data
//      * 1. get the item from list
//      * 2. set the text in the button of
//      */
//     override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
//         val item = list.get(position)
//         holder.button.text = item.toString()
//     }
//
//     // Setup custom accessibility delegate to set the text read with
//     // an accessibility service
//     companion object Accessibility : View.AccessibilityDelegate() {
//         @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//         override fun onInitializeAccessibilityNodeInfo(
//             host: View,
//             info: AccessibilityNodeInfo
//         ) {
//             super.onInitializeAccessibilityNodeInfo(host, info)
//             // With `null` as the second argument to [AccessibilityAction], the
//             // accessibility service announces "double tap to activate".
//             // If a custom string is provided,
//             // it announces "double tap to <custom string>".
//             val customString = host.context?.getString(R.string.look_up_words)
//             val customClick =
//                 AccessibilityNodeInfo.AccessibilityAction(
//                     AccessibilityNodeInfo.ACTION_CLICK,
//                     customString
//                 )
//             info.addAction(customClick)
//         }
//     }
// }