package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseObject
import com.parse.ParseQuery

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstObject = ParseObject("FirstClass")
        firstObject.put("message","Hey ! First message from android.Parse is now connected")
        firstObject.saveInBackground {
            if (it != null){
                it.localizedMessage?.let { message ->
                    Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show() }
            }else{
                Toast.makeText(this,"Object saved "+ firstObject.objectId, Toast.LENGTH_LONG).show()
            }
        }

        val itemList:MutableList<String> = mutableListOf()
        // Configure Query
        val query1 = ParseQuery.getQuery<ParseObject>("FirstClass")
        // Sorts the results in ascending order by the itemName field
        query1.orderByAscending("objectId")
        query1.findInBackground { objects, e ->
            if (e == null) {
                // Adding objects into the Array
                for (i in objects) {
                    i.getString("message")?.let{itemList.add(it)}
                }
            }
        }


        val query2 = ParseQuery.getQuery<ParseObject>("reminderList")
        // Query Parameters
        query2.whereEqualTo("objectId",findViewById<EditText>(R.id.etId).text.toString() )
        // How we need retrive exactly one result we can use the getFirstInBackground method
        query2.getFirstInBackground{ parseObject, parseException ->
            if (parseException == null) {
                parseObject.put("message",findViewById<EditText>(R.id.etMensaje).text.toString() )
                parseObject.saveInBackground{
                    if (it != null){
                        it.localizedMessage?.let { message ->
                            Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show() }
                    }else{
                        Toast.makeText(this,"Object saved "+ firstObject.objectId, Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this,"Get ItemById Error: " + parseException.message.toString(),Toast.LENGTH_LONG).show()
            }
        }

        val query = ParseQuery.getQuery<ParseObject>("reminderList")
        // Query Parameters
        query.whereEqualTo("objectId", findViewById<EditText>(R.id.etId).text.toString())
        // How we need retrive exactly one result we can use the getFirstInBackground method
        query.getFirstInBackground{ parseObject, parseException ->
            if (parseException == null) {
                parseObject.deleteInBackground{
                    if (it != null){
                        it.localizedMessage?.let { message ->
                            Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show() }
                    }else{
                        Toast.makeText(this,"Object deleted "+ firstObject.objectId, Toast.LENGTH_LONG).show()
                    }
                }

            } else {
                Log.d("app", "Get DeleteObject Error: " + parseException.message.toString())
            }
        }
    }
}