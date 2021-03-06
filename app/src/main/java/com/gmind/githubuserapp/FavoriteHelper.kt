package com.gmind.githubuserapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.widget.Toast
import com.gmind.githubuserapp.DatabaseContract.UserColumns.Companion.ID_COLUMN
import com.gmind.githubuserapp.DatabaseContract.UserColumns.Companion.TABLE_NAME
import com.gmind.githubuserapp.DatabaseContract.UserColumns.Companion.USERNAME_COLUMN
import kotlin.jvm.Throws

class FavoriteHelper (context: Context){



    companion object{
        private const val DATABASE_TABLE = TABLE_NAME
        private lateinit var databaseHelper: DatabaseHelper
        private var INSTANCE : FavoriteHelper? = null
        fun getInstance(context: Context): FavoriteHelper =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: FavoriteHelper(context)
            }

        private lateinit var database: SQLiteDatabase
    }

    init {
        databaseHelper = DatabaseHelper(context)
    }

    @Throws(SQLException::class)
    fun open(){
        database = databaseHelper.writableDatabase
    }

    fun close(){
        databaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$ID_COLUMN ASC"
        )
    }

    fun queryById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$ID_COLUMN = ?",
            arrayOf(id),
            null,
            null,
            null,
            null)
    }

    fun insert(values: ContentValues?, context: Context): Long {
        Toast.makeText(context, context.getString(R.string.added_to_favorite), Toast.LENGTH_SHORT).show()
        Log.d("Insert","Proses")
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun delete(id: String, context: Context): Int {
        Toast.makeText(context, context.getString(R.string.removed_from_favorite), Toast.LENGTH_SHORT).show()
        Log.d("Delete","Proses")
        return database.delete(DATABASE_TABLE, "$ID_COLUMN = '$id'", null)
    }

//    fun isFavorite(id: String): Boolean {
//        val cursor: Cursor = queryById(id)
//        if (cursor.moveToNext()) {
//            return false
//        }
//    }
}