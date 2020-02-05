package com.parthdesai1208.triviaapp.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.parthdesai1208.triviaapp.R
import com.parthdesai1208.triviaapp.databinding.FragmentFlagBinding
import com.parthdesai1208.triviaapp.utils.cb1
import com.parthdesai1208.triviaapp.utils.cb2
import com.parthdesai1208.triviaapp.utils.cb3
import com.parthdesai1208.triviaapp.utils.cb4
import com.parthdesai1208.triviaapp.view.SharedViewModel
import kotlinx.android.synthetic.main.fragment_flag.*
import java.util.ArrayList

class FlagFragment : Fragment() {

    private var viewModel: SharedViewModel? = null
    private lateinit var binding: FragmentFlagBinding
    private var arrayList = arrayListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flag, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this.activity!!).get(SharedViewModel::class.java)

        binding.viewModel = viewModel

        cB1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                arrayList.add(cb1)
                saveViewModel()
            } else {
                removeFromListAndViewModel(cb1)
            }
        }
        cB2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                arrayList.add(cb2)
                saveViewModel()
            } else {
                removeFromListAndViewModel(cb2)
            }
        }
        cB3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                arrayList.add(cb3)
                saveViewModel()
            } else {
                removeFromListAndViewModel(cb3)
            }
        }
        cB4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                arrayList.add(cb4)
                saveViewModel()
            } else {
                removeFromListAndViewModel(cb4)
            }
        }
    }

    private fun removeFromListAndViewModel(flag: String) {
        val tempArrayList = ArrayList<String>(arrayList)
        if (arrayList.size > 0) {
            for (i in arrayList.indices) {
                if (arrayList[i] == flag) {
                    tempArrayList.removeAt(i)
                }
            }
            arrayList = tempArrayList
            viewModel!!.saveFlagColor(arrayList.toArray().joinToString())
        }
    }

    private fun saveViewModel() {
        val flagColor = arrayList.toArray().joinToString()
        viewModel!!.saveFlagColor(flagColor)
    }

}
