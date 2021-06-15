package com.example.myapplication

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.parse.Parse
import com.parse.ParseObject
import com.parse.ParseQuery

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
                Parse.Configuration.Builder(this)
                        .applicationId(getString(R.string.back4app_app_id))
                        .clientKey(getString(R.string.back4app_client_key))
                        .server(getString(R.string.back4app_server_url))
                        .build())

        /*val installation = ParseInstallation.getCurrentInstallation()
        installation.put("GCMSenderId", "184279246803")
        installation.saveInBackground()*/


    }
}