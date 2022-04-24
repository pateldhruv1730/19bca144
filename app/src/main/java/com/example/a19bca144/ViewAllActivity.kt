package com.example.a19bca144

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_all.*
import kotlinx.android.synthetic.main.custom_dialog.*

class ViewAllActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)

        refreshRecycler()

    }
    private fun refreshRecycler() {
        var db=DBHelper(this)
        var arr=db.retriveAll()
        var personadapter = PersonAdapter(this,arr)
        myrecycle.adapter = personadapter
    }
    fun delete(position:Int)
    {
        var db=DBHelper(this)
        var arr:ArrayList<Person> = db.retriveAll()
        var person=arr.get(position)
        db.delete(person.pr_id)
        refreshRecycler()
    }
    fun update(position: Int)
    {
        var db=DBHelper(this)
        var arr:ArrayList<Person> = db.retriveAll()
        var person = arr.get(position)
        var dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.txtUpadateId.setText(person.pr_id.toString())
        dialog.edtUpdateFname.setText(person.pr_fname)
        dialog.edtUpdateLname.setText(person.pr_lname)
        dialog.edtUpdateAge.setText(person.pr_age.toString())

        dialog.btnUpdate.setOnClickListener {
            var id = dialog.txtUpadateId.text.toString().toInt()
            var fname = dialog.edtUpdateFname.text.toString()
            var lname = dialog.edtUpdateLname.text.toString()
            var age = dialog.edtUpdateAge.text.toString().toInt()
            var person = Person(id,fname,lname,age)
            db.update(person)
            dialog.dismiss()
            refreshRecycler()
        }
        dialog.show()
    }
}