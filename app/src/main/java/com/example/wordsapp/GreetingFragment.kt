package com.example.wordsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wordsapp.WordListFragment.Companion.WORD
import com.example.wordsapp.databinding.FragmentGreetingBinding

class GreetingFragment : Fragment() {
  private var word: String? = null
  private var _binding: FragmentGreetingBinding? = null
  val binding get() = _binding!!

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      word = it.getString(WORD)
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    _binding = FragmentGreetingBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    binding.greetingTv.text = "You selected word: $word"
    super.onViewCreated(view, savedInstanceState)
  }
}