package com.example.mynote

object DB {
    const val TABLE_TITLE = "NOTES"
    const val ID = "id"
    const val TITLE = "title"
    const val BODY = "body"
    const val DATE = "data"
}

data class DataBase(var ID: Int, var TITLE:String, var BODY:String, var DATE:String )

//class DB {
//     val TABLE_TITLE = "NOTES"
//     val ID = "id"
//     val TITLE = "title"
//     val BODY = "body"
//}