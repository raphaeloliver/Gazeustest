package com.example.gazeuslibrary

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log

open class Init : ContentProvider(){

    companion object {
        lateinit var appContext: Application
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("not necessary") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(): Boolean {
        context?.let {
            appContext = it.applicationContext as Application
            Log.wtf("SuccessContext", "Success")
        } ?: Log.wtf("FailContext", "Fail")
        startKoin()
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("not necessary") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(uri: Uri): String? {
        TODO("not necessary") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not necessary") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("not necessary") //To change body of created functions use File | Settings | File Templates.
    }

    fun getAppContext() = appContext
}