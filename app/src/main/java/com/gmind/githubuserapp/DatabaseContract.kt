package com.gmind.githubuserapp

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class UserColumns:BaseColumns{
        companion object{
            const val TABLE_NAME = "favorite_user"
            const val ID_COLUMN = "id"
            const val USERNAME_COLUMN = "username"
            const val AVATAR_URL_COLUMN = "avatar_url"
        }
    }
}