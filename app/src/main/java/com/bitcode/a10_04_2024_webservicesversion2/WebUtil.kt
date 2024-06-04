package com.bitcode.a10_04_2024_webservicesversion2

import android.util.Log
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object WebUtil {
    fun parseJSONToFetchUserData() : ArrayList<User>?{
        var users : ArrayList<User> = ArrayList<User>()
        var url = URL("https://reqres.in/api/users?page=2")
        var httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "GET"
        httpURLConnection.connect()

        Log.e("tag","${httpURLConnection.responseMessage}")
        Log.e("tag","${httpURLConnection.responseCode}")
        Log.e("tag","${httpURLConnection.contentLength}")
        Log.e("tag","${httpURLConnection.contentEncoding}")
        Log.e("tag","${httpURLConnection.contentType}")

        var inStream = httpURLConnection.inputStream
        var responseBuffer = StringBuffer()
        var data = ByteArray(1024 * 1)
        var count = 0

        count = inStream.read(data)
        while (count!=-1){
            responseBuffer.append(String(data,0,count))
            count = inStream.read(data)
        }
        inStream.close()

        var jsonResponseRoot = JSONObject(responseBuffer.toString())
        var page = jsonResponseRoot.getInt("page")
        var perPage = jsonResponseRoot.getInt("per_page")
        var total = jsonResponseRoot.getInt("total")
        var totalPages = jsonResponseRoot.getInt("total_pages")

        var jsonUsers = jsonResponseRoot.getJSONArray("data")

        for (i in 0..<jsonUsers.length()-1){
           var eachUserObject = jsonUsers.getJSONObject(i)
            val id = eachUserObject.getInt("id")
            var first_name = eachUserObject.getString("first_name")
            var last_name = eachUserObject.getString("last_name")
            var email = eachUserObject.getString("email")
            var avatar = eachUserObject.getString("avatar")

            users.add(
                User(id,first_name,last_name,email,avatar)
            )
        }

        var jsonSupportObject = jsonResponseRoot.getJSONObject("support")

        Log.e("tag","${responseBuffer.toString()}")
        Log.e("tag","${users}")
        return users
    }
}