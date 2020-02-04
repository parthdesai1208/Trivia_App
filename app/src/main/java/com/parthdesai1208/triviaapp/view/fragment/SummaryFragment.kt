package com.parthdesai1208.triviaapp.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.parthdesai1208.triviaapp.R
import com.parthdesai1208.triviaapp.utils.PreferenceProvider
import kotlinx.android.synthetic.main.fragment_summary.*

/**
 * A simple [Fragment] subclass.
 */
class SummaryFragment : Fragment() {

    private var pref: PreferenceProvider? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref =
            PreferenceProvider(this.context!!)
        navController = Navigation.findNavController(this.activity!!,
            R.id.nav_host
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pref?.saveIsFinishReach(true)
        arguments?.let {
            val args =
                SummaryFragmentArgs.fromBundle(it)
            txtAnswerQ2Summary.text = HtmlCompat.fromHtml(
                String.format(
                    getString(R.string.txtAnswerQ2Summary),
                    args.flagColor
                ), HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        }

        txtNameSummary.text = HtmlCompat.fromHtml(
            String.format(getString(R.string.txtNameSummary), pref?.getName()),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        txtAnswerQ1Summary.text = HtmlCompat.fromHtml(
            String.format(
                getString(R.string.txtAnswerQ1Summary),
                pref?.getCricketer()
            ), HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        imgHistory.setOnClickListener {
            navController.navigate(R.id.action_summaryFragment_to_historyFragment)
        }
    }

}
