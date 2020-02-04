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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.parthdesai1208.triviaapp.R
import com.parthdesai1208.triviaapp.utils.PreferenceProvider
import kotlinx.android.synthetic.main.fragment_name.*

/**
 * A simple [Fragment] subclass.
 */
class NameFragment : Fragment() {

    private var pref: PreferenceProvider? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref =
            PreferenceProvider(this.context!!)
        navController = Navigation.findNavController(this.activity!!,
            R.id.nav_host
        )

        if (pref!!.getIsFinishReach()!!) {
            val nextAction =
                NameFragmentDirections.actionNameFragmentToSummaryFragment(
                    pref!!.getFlagColor().toString()
                )
            navController.navigate(nextAction)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
