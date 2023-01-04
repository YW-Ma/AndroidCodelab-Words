package com.example.wordsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.FragmentWordListBinding

class WordListFragment : Fragment() {
  companion object { // similar to abstract
    const val LETTER = "letter"
    const val SEARCH_PREFIX = "https://www.google.com/search?q="
  }

  private var _binding: FragmentWordListBinding? = null
  val binding get() = _binding!!

  // override fun onCreate(savedInstanceState: Bundle?) {
  //   super.onCreate(savedInstanceState)
  // }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentWordListBinding.inflate(inflater, container, false)
    val view = binding.root
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    // Retrieve the LETTER from the Intent extras
    // intent.extras.getString returns String? (String or null)
    // so toString() guarantees that the value will be a String
    val letterId = activity?.intent?.extras?.getString(LETTER).toString() // .toString() feed in ? and return not nullable.

    val recyclerView = binding.recyclerView
    recyclerView.layoutManager = LinearLayoutManager(requireContext())
    recyclerView.adapter = WordAdapter(letterId, requireContext())

    // Adds a [DividerItemDecoration] between items
    recyclerView.addItemDecoration(
      DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
    )

    // title = getString(R.string.detail_prefix) + " " + letterId
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }
}