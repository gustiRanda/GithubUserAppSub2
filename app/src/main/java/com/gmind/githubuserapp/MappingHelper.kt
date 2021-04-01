package com.gmind.githubuserapp

import android.database.Cursor
import com.gmind.githubuserapp.model.User

object MappingHelper {

    fun mapCursorToArrayList(favoriteCursor: Cursor?): ArrayList<User> {
        val favoriteList = ArrayList<User>()
        favoriteCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.UserColumns.ID_COLUMN))
                val username = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.USERNAME_COLUMN))
                val avatar_url = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.AVATAR_URL_COLUMN))
                favoriteList.add(User(id, username, avatar_url))
            }
        }
        return favoriteList
    }
}