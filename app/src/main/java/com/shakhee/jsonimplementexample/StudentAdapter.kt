package com.shakhee.jsonimplementexample


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView

class StudentAdapter(context: Context, list: ArrayList<StudentModel>, private val i: Int) :
    BaseAdapter (){
    private val layoutInflater: LayoutInflater
    private val arrayListDetails:ArrayList<StudentModel>

    init {
        this.layoutInflater = LayoutInflater.from(context)

        this.arrayListDetails=list

    }
    override fun getCount(): Int {
        return arrayListDetails.size
    }

    override fun getItem(position: Int): Any {
        return arrayListDetails.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val listRowHolder: ListRowHolder
        if (convertView == null) {
            view = this.layoutInflater.inflate(R.layout.list_item_child, parent, false)
            listRowHolder = ListRowHolder(view)
            view.tag = listRowHolder
        } else {
            view = convertView
            listRowHolder = view.tag as ListRowHolder
        }
        listRowHolder.rollNumber.text=arrayListDetails.get(position).rollNumber
        listRowHolder.name.text=arrayListDetails.get(position).name
        listRowHolder.classes.text=arrayListDetails.get(position).classes
        listRowHolder.totalMarks.text=arrayListDetails.get(position).totalMarks
        listRowHolder.gender.text=arrayListDetails.get(position).gender


        return view
    }

}

class ListRowHolder(row:View) {
    public val rollNumber: TextView
    public val name: TextView
    public val classes: TextView
    public val totalMarks: TextView
    public val gender: TextView
    public val linearLayout: LinearLayout

    init {
        this.rollNumber = row.findViewById(R.id.rollNumber)
        this.name = row.findViewById(R.id.name)
        this.classes = row.findViewById(R.id.classes)
        this.totalMarks = row.findViewById(R.id.totalMarks)
        this.gender = row.findViewById(R.id.gender)
        this.linearLayout = row.findViewById(R.id.linearLayout)
    }
}
