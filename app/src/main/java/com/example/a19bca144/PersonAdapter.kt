package com.example.a19bca144

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*

class PersonAdapter(val context: Context, var arr:ArrayList<Person>)
    : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>()

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        var inflater= LayoutInflater.from(parent.context)
        var view= inflater.inflate(R.layout.card_item,parent,false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(arr[position])
        holder.itemView.imgDetele.setOnClickListener {
            if(context is ViewAllActivity)
            {
                context.delete(position)
            }
        }
        holder.itemView.imgUpdate.setOnClickListener {
            if(context is ViewAllActivity)
            {
                context.update(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return  arr.size
    }

    class PersonViewHolder(var view: View): RecyclerView.ViewHolder(view)
    {
        fun bind(p:Person)
        {
            view.tvPrID.setText(p.pr_id.toString())
            view.tvPrFname.setText(p.pr_fname)
            view.tvPrLname.setText(p.pr_lname)
            view.tvPrAge.setText(p.pr_age.toString())
        }
    }
}