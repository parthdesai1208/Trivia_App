package com.parthdesai1208.triviaapp.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.parthdesai1208.triviaapp.R
import com.parthdesai1208.triviaapp.databinding.FragmentSummaryBinding
import com.parthdesai1208.triviaapp.utils.PreferenceProvider


class SummaryFragment : Fragment() {

    private var pref: PreferenceProvider? = null
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSummaryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref =
            PreferenceProvider(this.context!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_summary,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navController = Navigation.findNavController(
            this.activity!!,
            R.id.nav_host
        )

        binding.pref = pref
        binding.click = this
        pref?.saveIsFinishReach(true)

    }

    fun onImgHistoryClick() {
        navController.navigate(R.id.action_summaryFragment_to_historyFragment)
    }

}
