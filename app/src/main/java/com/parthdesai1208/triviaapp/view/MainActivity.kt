package com.parthdesai1208.triviaapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.parthdesai1208.triviaapp.R
import com.parthdesai1208.triviaapp.model.room.AppDataBase
import com.parthdesai1208.triviaapp.model.room.DataEntity
import com.parthdesai1208.triviaapp.utils.*
import com.parthdesai1208.triviaapp.view.fragment.FlagFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_cricket.*
import kotlinx.android.synthetic.main.fragment_flag.*
import kotlinx.android.synthetic.main.fragment_name.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var id: Int = R.id.nameFragment
    private var pref: PreferenceProvider? = null
    private var dao: AppDataBase? = null
    private val uiScope = CoroutineScope(Dispatchers.Main + Job())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this,
            R.id.nav_host
        )

        pref = PreferenceProvider(baseContext)
        dao = AppDataBase.invoke(baseContext)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            id = destination.id
            if (id == R.id.nameFragment || id == R.id.summaryFragment) {
                btnBack.visibility = View.GONE
            } else {
                btnBack.visibility = View.VISIBLE
            }
            if (id == R.id.summaryFragment) {
                btnNext.text = getString(R.string.finish)
            } else {
                btnNext.text = getString(R.string.next)
            }
            if(id == R.id.historyFragment){
                btnNext.visibility = View.GONE
            }else{
                btnNext.visibility = View.VISIBLE
            }
        }


        btnNext.setOnClickListener {
            when (id) {
                R.id.nameFragment -> {
                    if (etNameFragment.text?.trim()?.isNotEmpty()!!) {
                        pref!!.saveName(etNameFragment.text?.trim().toString())
                        navController.navigate(R.id.action_nameFragment_to_cricketFragment)
                    }
                }

                R.id.cricketFragment -> {
                    when (radioGroupCriFrag.checkedRadioButtonId) {
                        R.id.rdb1 -> {
                            pref!!.saveCricketer(option1)
                            navController.navigate(R.id.action_cricketFragment_to_flagFragment)
                        }
                        R.id.rdb2 -> {
                            pref!!.saveCricketer(option2)
                            navController.navigate(R.id.action_cricketFragment_to_flagFragment)
                        }
                        R.id.rdb3 -> {
                            pref!!.saveCricketer(option3)
                            navController.navigate(R.id.action_cricketFragment_to_flagFragment)
                        }
                        R.id.rdb4 -> {
                            pref!!.saveCricketer(option4)
                            navController.navigate(R.id.action_cricketFragment_to_flagFragment)
                        }
                    }
                }

                R.id.flagFragment -> {
                    val arrayList = arrayListOf<String>()
                    if (cB1.isChecked) arrayList.add(cb1)
                    if (cB2.isChecked) arrayList.add(cb2)
                    if (cB3.isChecked) arrayList.add(cb3)
                    if (cB4.isChecked) arrayList.add(cb4)
                    if (arrayList.size > 0) {
                        val flagColor = arrayList.toArray().joinToString()
                        pref!!.saveFlagColor(flagColor)
                        val nextAction =
                            FlagFragmentDirections.actionFlagFragmentToSummaryFragment(
                                flagColor
                            )
                        navController.navigate(nextAction)
                    }
                }

                R.id.summaryFragment -> {
                    val model =
                        DataEntity(
                            0,
                            getCurrentDateTime(),
                            pref!!.getName().toString(),
                            pref!!.getCricketer().toString(),
                            pref!!.getFlagColor().toString()
                        )
                    uiScope.launch {
                        dao!!.getUserDao().insertHistory(model)
                        /*withContext(IO){
                            dao!!.getUserDao().insertHistory(model)
                        }*/
                    }
                    pref!!.saveIsFinishReach(false)
                    navController.navigate(R.id.action_summaryFragment_to_nameFragment)
                }
                else -> navController.navigate(R.id.action_nameFragment_to_cricketFragment)

            }
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }

    }

    @Suppress("UNUSED_VALUE")
    private fun getCurrentDateTime(): String {
        var date = SimpleDateFormat(
            "dd MMMM H:mm",
            Locale.ENGLISH
        ).format(Calendar.getInstance().time)
        val hourInInteger = date.substring(date.indexOf(":") - 2, date.indexOf(":")).toInt()
        if (hourInInteger >= 12) {
            if (hourInInteger > 12) {
                val diffHour = hourInInteger - 12
                val tempDate =
                    date.replaceRange(date.indexOf(":") - 2, date.indexOf(":"), diffHour.toString())
                date = "$tempDate PM"
            } else {
                date += " PM"
            }
        } else {
            date += " AM"
        }
        val dateInNumber = date.substring(0, 2).toInt()
        val remainDate = date.substring(2, date.length)
        var ordinalIndicator: String
        if (dateInNumber in 11..13) {
            ordinalIndicator = "th"
        }
        ordinalIndicator = when (dateInNumber % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
        return dateInNumber.toString() + ordinalIndicator + remainDate
    }

}
