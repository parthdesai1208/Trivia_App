package com.parthdesai1208.triviaapp.view.fragment


import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.parthdesai1208.triviaapp.R
import com.parthdesai1208.triviaapp.databinding.FragmentNameBinding
import com.parthdesai1208.triviaapp.utils.PreferenceProvider
import com.parthdesai1208.triviaapp.view.SharedViewModel
import kotlinx.android.synthetic.main.fragment_name.*

/**
 * A simple [Fragment] subclass.
 */
class NameFragment : Fragment() {

    private var pref: PreferenceProvider? = null
    private lateinit var navController: NavController
    private var viewModel : SharedViewModel? = null
    private lateinit var binding : FragmentNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref =
            PreferenceProvider(this.context!!)
        viewModel = ViewModelProvider(this.activity!!).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_name, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navController = Navigation.findNavController(this.activity!!, R.id.nav_host)

        if (pref!!.getIsFinishReach()!!) {
            val nextAction =
                NameFragmentDirections.actionNameFragmentToSummaryFragment(
                    pref!!.getFlagColor().toString()
                )
            navController.navigate(nextAction)
        }

        binding.viewModel = viewModel

        etNameFragment.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    val btn = activity?.findViewById<Button>(R.id.btnNext)
                    btn?.performClick()
                    return true
                }
                return false
            }

        })
    }
}
