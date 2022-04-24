package com.example.a19bca144

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(var context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION)
{
    companion object {
        private var DB_NAME="PracticeDB"
        private var TB_USER="UserDetail"
        private var TB_PERSON="PersonDetail"
        private var DB_VERSION=1

        private val USER_ID = "ur_id"
        private val USER_EMAIL = "ur_email"
        private val USER_CITY = "ur_city"
        private val USER_PASS = "ur_password"


        private var PR_ID="pr_id"
        private var PR_FNAME="pr_fname"
        private var PR_LNAME="pr_lname"
        private var PR_AGE="pr_age"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        var sql1 = "CREATE TABLE $TB_USER ($USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, $USER_EMAIL TEXT,  $USER_CITY TEXT, $USER_PASS TEXT)"
        var sql2="CREATE TABLE $TB_PERSON($PR_ID INTEGER PRIMARY KEY AUTOINCREMENT, $PR_FNAME TEXT, $PR_LNAME TEXT, $PR_AGE INTEGER)";
        p0?.execSQL(sql1);
        p0?.execSQL(sql2);
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertUser(user : User): Long
    {
        var db = writableDatabase
        var cn = ContentValues()
        cn.put(USER_EMAIL,user.ur_email)
        cn.put(USER_CITY,user.ur_city)
        cn.put(USER_PASS,user.ur_password)

        var res = db.insert(TB_USER,null,cn)
        return res
        db.close()
    }

    fun getUser(uname:String): ArrayList<User>
    {
        var db = readableDatabase
        var sql = "Select * from $TB_USER where $USER_EMAIL = '$uname'"
        var arr = ArrayList<User>()
        var cursor = db.rawQuery(sql,null)
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(0)
            var email = cursor.getString(1)
            var city = cursor.getString(2)
            var password = cursor.getString(3)
            var user = User(id,email,city,password)

            arr.add(user)
        }
        return arr
        db.close()
    }

    fun insert(Person:Person):Boolean
    {
        var db=writableDatabase
        var cv= ContentValues()
        cv.put(PR_FNAME,Person.pr_fname)
        cv.put(PR_LNAME,Person.pr_lname)
        cv.put(PR_AGE,Person.pr_age)

        var flag=db.insert(TB_PERSON,null,cv)
        if (flag>0)
        {
            return true
        }
        else {
            return false
        }
    }

    fun retriveAll():ArrayList<Person>
    {
        var db = readableDatabase
        var cursor = db.query(TB_PERSON,null,null,null,null,null,null)
        var arr:ArrayList<Person> = ArrayList()
        while (cursor.moveToNext())
        {
            var id = cursor.getInt(0)
            var fname = cursor.getString(1)
            var lname = cursor.getString(2)
            var age = cursor.getInt(3)
            var person = Person(id,fname,lname,age)
            arr.add(person)
        }
        return arr
    }

    fun delete(id:Int)
    {
        var db=writableDatabase
        db.delete(TB_PERSON,"$PR_ID=$id",null)
        db.close()
    }

    fun update(Person:Person)
    {
        var db=writableDatabase
        var cv=ContentValues()
        cv.put(PR_FNAME,Person.pr_fname)
        cv.put(PR_LNAME,Person.pr_lname)
        cv.put(PR_AGE,Person.pr_age)
        db.update(TB_PERSON,cv,"$PR_ID=${Person.pr_id}", null)
        db.close()
    }
}