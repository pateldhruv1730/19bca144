package com.example.a19bca144

data class Person (var pr_fname:String,var pr_lname:String,var pr_age:Int)
{
    var pr_id:Int=0
    constructor(pr_id:Int,pr_fname:String,pr_lname: String,pr_age: Int):this(pr_fname, pr_lname, pr_age)
    {
        this.pr_id = pr_id
    }
}