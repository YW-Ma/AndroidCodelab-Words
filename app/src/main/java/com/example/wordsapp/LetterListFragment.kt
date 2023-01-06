package com.example.wordsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

class LetterListFragment : Fragment() {
  private var _binding: FragmentLetterListBinding? = null
  private val binding
    get() = _binding!! // NOTICE: get() uses = not {} to define the content

  private lateinit var recyclerView: RecyclerView
  private var isLinearLayoutManager = true

  /**
   * onCreate(): The fragment has been instantiated and is in the CREATED state.
   * However, its corresponding view has not been created yet.
   * [init but don't have view]
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
    // recyclerView = binding.recyclerView  -->  moved to onViewCreated
    // chooseLayout()
  }

  // Fragment need new methods: onCreateView / onViewCreated
  /**
   * onCreateView(): This method is where you **inflate the layout**.
   * The fragment has entered the CREATED state.
   *
   * onViewCreated(): This is called after the view is created.
   * In this method, you would typically **bind specific views to properties**
   * by calling findViewById()
   * */
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentLetterListBinding.inflate(inflater, container, false)
    // activity version will be ActivityXXXX.inflate(layoutInflater)
    val view = binding.root
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    recyclerView = binding.recyclerView
    chooseLayout()
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
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
  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.layout_menu, menu)

    val layoutChangeButton: MenuItem? = menu?.findItem(R.id.action_switch_layout)
    setIcon(layoutChangeButton)

    return
  }
  // override fun onCreateOptionsMenu(menu: Menu?): Boolean { <-- activity's signature is different
  //   menuInflater.inflate(R.menu.layout_menu, menu)
  //
  //   val layoutChangeButton: MenuItem? = menu?.findItem(R.id.action_switch_layout)
  //   setIcon(layoutChangeButton)
  //
  //   return true
  // }

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
      LinearLayoutManager(context)
    } else {
      GridLayoutManager(context, 4)
    }
    recyclerView.adapter = LetterAdapter()
  }

  @SuppressLint("NewApi")
  private fun setIcon(layoutChangeButton: MenuItem?) {
    if (layoutChangeButton == null) {
      return
    }

    layoutChangeButton.icon = if (isLinearLayoutManager) {
      requireContext().getDrawable(R.drawable.ic_grid_layout)
    } else {
      requireContext().getDrawable(R.drawable.ic_linear_layout)
    }
  }

}