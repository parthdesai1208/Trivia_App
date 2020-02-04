package com.parthdesai1208.triviaapp.view.fragment.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.parthdesai1208.triviaapp.R
import com.parthdesai1208.triviaapp.model.room.DataEntity

class HistoryAdapter(list : ArrayList<DataEntity>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var list: ArrayList<DataEntity> = ArrayList()

    init {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class HistoryViewHolder(view: View, val context: Context) :
        RecyclerView.ViewHolder(view.rootView) {
        private var view: View? = null
        var txt1: TextView? = null
        var txt2: TextView? = null
        var txt3: TextView? = null
        var txt4: TextView? = null

        init {
            super.itemView
            this.view = view
            txt1 = this.view!!.findViewById(R.id.txt1History)
            txt2 = this.view!!.findViewById(R.id.txt2History)
            txt3 = this.view!!.findViewById(R.id.txt4History)
            txt4 = this.view!!.findViewById(R.id.txt6History)
        }

        fun bind(model: DataEntity) {
            txt1?.text = HtmlCompat.fromHtml(
                String.format(
                    context.getString(R.string.txt1History),
                    model.id,
                    model.date
                ), 0
            )
            txt2?.text = HtmlCompat.fromHtml(
                String.format(
                    context.getString(R.string.txt2History),
                    model.name
                ), 0
            )
            txt3?.text = HtmlCompat.fromHtml(
                String.format(
                    context.getString(R.string.txt4History),
                    model.cricketer
                ), 0
            )
            txt4?.text = HtmlCompat.fromHtml(
                String.format(
                    context.getString(R.string.txt6History),
                    model.flagColor
                ), 0
            )
        }
    }

}