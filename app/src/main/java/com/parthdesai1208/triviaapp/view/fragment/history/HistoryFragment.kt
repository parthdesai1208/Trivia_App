package com.parthdesai1208.triviaapp.view.fragment.history


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.parthdesai1208.triviaapp.R
import com.parthdesai1208.triviaapp.model.room.AppDataBase
import com.parthdesai1208.triviaapp.model.room.DataEntity
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment() {


    private var dao: AppDataBase? = null
    private val uiScope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = AppDataBase.invoke(this.context!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        uiScope.launch {
            val list = getHistory()
            if (list?.size!! > 0) {
                recyclerview_history.visibility = View.VISIBLE
                txtNoHistoryFound.visibility = View.GONE
                val mAdapter =
                    HistoryAdapter(
                        list as ArrayList<DataEntity>
                    )
                recyclerview_history.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    adapter = mAdapter
                }
            } else {
                txtNoHistoryFound.visibility = View.VISIBLE
                recyclerview_history.visibility = View.INVISIBLE
            }
        }
    }

    private suspend fun getHistory(): List<DataEntity>? {
        return  withContext(Dispatchers.IO){
             dao?.getUserDao()?.getHistory()
        }
    }

}
