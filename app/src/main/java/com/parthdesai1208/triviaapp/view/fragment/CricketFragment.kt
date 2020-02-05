package com.parthdesai1208.triviaapp.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.parthdesai1208.triviaapp.R
import com.parthdesai1208.triviaapp.databinding.FragmentCricketBinding
import com.parthdesai1208.triviaapp.view.SharedViewModel


class CricketFragment : Fragment() {

    private var viewModel : SharedViewModel? = null
    private lateinit var binding : FragmentCricketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this.activity!!).get(SharedViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cricket,container,false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel

    }

}
