package com.example.contactstest

import android.content.ContentProvider
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class MyProvider: ContentProvider()
{
    private val table1Dir = 0
    private val table1Item = 1
    private val table2Dir = 2
    private val table2Item = 3
    private val uriMatcher= UriMatcher(UriMatcher.NO_MATCH)
    init
    {
        uriMatcher.addURI("com.example.contactstest","table1",table1Dir)
        uriMatcher.addURI("com.example.contactstest","table1/#",table1Item)
        uriMatcher.addURI("com.example.contactstest","table2",table2Dir)
        uriMatcher.addURI("com.example.contactstest","table2/#",table2Item)
    }

    override fun query(
        uri: Uri,
        projection: Array<out String?>?,
        selection: String?,
        selectionArgs: Array<out String?>?,
        sortOrder: String?
    ): Cursor?
    {
        when(uriMatcher.match(uri))
        {
            table1Dir -> {
                // 查询table1表中的所有数据
            }
            table1Item -> {
                // 查询table1表中的单条数据
            }
            table2Dir -> {
                // 查询table2表中的所有数据
            }
            table2Item -> {
                // 查询table2表中的单条数据
            }
        }
        return null
    }

    override fun getType(uri: Uri): String?
    {
        when(uriMatcher.match(uri))
        {
            table1Dir->"vnd.android.cursor.dir/vnd.com.example.app.provider.table1"
            table1Item -> "vnd.android.cursor.item/vnd.com.example.app.provider.table1"
            table2Dir -> "vnd.android.cursor.dir/vnd.com.example.app.provider.table2"
            table2Item -> "vnd.android.cursor.item/vnd.com.example.app.provider.table2"
            else -> null
        }
    }
}