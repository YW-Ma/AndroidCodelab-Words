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

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.ActivityMainBinding

/**
 * Main Activity and entry point for the app. Displays a RecyclerView of letters.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        // Sets the LinearLayoutManager of the recyclerview
        // recyclerView.layoutManager = LinearLayoutManager(this)
        // recyclerView.adapter = LetterAdapter()
        chooseLayout()
    }

    /**
     * To toggle between linearLayoutManager and gridLayoutManager
     * 1. use the options menu to perform (override onCreateOptionsMenu and onOptionsItemSelected)
     * 2. in onCreate, set using menuInflater to inflate the entire menu list, and then specific menu item button we use
     * 3. in onSelected, first filter out the item we interact with, then toggle the boolean, set layoutManager, and rerun the set content
     * 4. helper methods:
     *    - set content (setIcon, icon will change)
     *    - set layout (chooseLayout, assign layoutManager and adapter)
     * */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)

        val layoutChangeButton: MenuItem? = menu?.findItem(R.id.action_switch_layout)
        setIcon(layoutChangeButton)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_switch_layout -> {
                // toggle the boolean
                // set the layout
                isLinearLayoutManager = !isLinearLayoutManager
                setIcon(item)
                chooseLayout()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun chooseLayout() {
        recyclerView.layoutManager = if (isLinearLayoutManager) {
            LinearLayoutManager(this)
        } else {
            GridLayoutManager(this, 4)
        }
        recyclerView.adapter = LetterAdapter()
    }

    @SuppressLint("NewApi")
    private fun setIcon(layoutChangeButton: MenuItem?) {
        if (layoutChangeButton == null) {
            return
        }

        layoutChangeButton.icon = if (isLinearLayoutManager) {
            this.getDrawable(R.drawable.ic_grid_layout)
        } else {
            this.getDrawable(R.drawable.ic_linear_layout)
        }
    }

    // override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    //     menuInflater.inflate(R.menu.layout_menu, menu)
    //
    //     val layoutButton = menu?.findItem(R.id.action_switch_layout)
    //     // Calls code to set the icon based on the LinearLayoutManager of the RecyclerView
    //     setIcon(layoutButton)
    //
    //     return true
    // }
    //
    // override fun onOptionsItemSelected(item: MenuItem): Boolean {
    //     return when (item.itemId) {
    //         // use when, since may have multiple item.
    //         R.id.action_switch_layout -> {
    //             // Sets isLinearLayoutManager (a Boolean) to the opposite value
    //             isLinearLayoutManager = !isLinearLayoutManager
    //             // Sets layout and icon
    //             chooseLayout()
    //             setIcon(item)
    //
    //             return true
    //         }
    //         //  Otherwise, do nothing and use the core event handling
    //
    //         // when clauses require that all possible paths be accounted for explicitly,
    //         //  for instance both the true and false cases if the value is a Boolean,
    //         //  or an else to catch all unhandled cases.
    //         else -> super.onOptionsItemSelected(item)
    //     }
    // }
    //
    // private fun chooseLayout() {
    //     if (isLinearLayoutManager) {
    //         recyclerView.layoutManager = LinearLayoutManager(this)
    //     } else {
    //         recyclerView.layoutManager = GridLayoutManager(this, /* spanCount = */4)
    //     }
    //     recyclerView.adapter = LetterAdapter()
    // }
    //
    // private fun setIcon(menuItem: MenuItem?) {
    //     if (menuItem == null)
    //         return
    //
    //     // Set the drawable for the menu icon based on which LayoutManager is currently in use
    //
    //     // An if-clause can be used on the right side of an assignment if all paths return a value.
    //     // The following code is equivalent to
    //     // if (isLinearLayoutManager)
    //     //     menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
    //     // else menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
    //     menuItem.icon =
    //         if (isLinearLayoutManager)
    //             ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
    //         else ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
    // }
}
