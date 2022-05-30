package com.shakhee.jsonimplementexample

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.nio.charset.StandardCharsets.UTF_8



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list_item = findViewById(R.id.listView)

        gettingStudentDetails()
    }
    companion object {
        @SuppressLint("StaticFieldLeak")
        var createdDate: String? = null

        @SuppressLint("StaticFieldLeak")
        var studentJson: String? = ""

        @SuppressLint("StaticFieldLeak")
        var studentModels: ArrayList<StudentModel>? = ArrayList()

        @SuppressLint("StaticFieldLeak")
        lateinit var list_item: ListView


        private var mProgressDialog: ProgressDialog? = null

        fun removeSimpleProgressDialog() {
            try {
                if (mProgressDialog != null) {
                    if (mProgressDialog!!.isShowing) {
                        mProgressDialog!!.dismiss()
                        mProgressDialog = null
                    }
                }
            } catch (ie: IllegalArgumentException) {
                ie.printStackTrace()

            } catch (re: RuntimeException) {
                re.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        fun showSimpleProgressDialog(
            context: Context, title: String,
            msg: String, isCancelable: Boolean
        ) {
            try {
                if (mProgressDialog == null) {
                    mProgressDialog = ProgressDialog.show(context, title, msg)
                    mProgressDialog!!.setCancelable(isCancelable)
                }

                if (!mProgressDialog!!.isShowing) {
                    mProgressDialog!!.show()
                }

            } catch (ie: IllegalArgumentException) {
                ie.printStackTrace()
            } catch (re: RuntimeException) {
                re.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
    private fun gettingStudentDetails() {
        var json: String
        try {
            val inputStream: InputStream = this@MainActivity.getAssets().open("studentDetails.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json= String(buffer, UTF_8)

            try {
                val jsonObject = JSONObject(json)
                println("sOrder..............jsonObject................."+jsonObject)
                val json1: JSONObject = jsonObject.getJSONObject("data")
                val jsonArray: JSONArray = json1.getJSONArray("Students")
                println("sOrder..............jsonArray................."+jsonArray)
                studentJson = jsonArray.toString()
                callingStudentList()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "Information Getting Null", Toast.LENGTH_SHORT)
                    .show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }



    private fun callingStudentList() {

        try {
            if (studentJson == null || studentJson!!.trim { it <= ' ' }.length == 0 || studentJson.equals(
                    "null",
                    ignoreCase = true
                )
            ) {
                return
            }
            studentModels = ArrayList()
            val jsonArray = JSONArray(studentJson)
            if (jsonArray.length() > 0) {
                for (i in 0 until jsonArray.length()) {
                    val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                    println("sOrder..............jsonObject..........00......."+jsonObject)

                    (studentModels as ArrayList<StudentModel>).add(
                        StudentModel(
                            jsonObject.getString("Roll_Number"),
                            jsonObject.getString("Name"),
                            jsonObject.getString("Class"),
                            jsonObject.getString("Section"),
                            jsonObject.getString("Gender"),
                            jsonObject.getString("Total_Marks")
                        )
                    )
                }
                removeSimpleProgressDialog()
                val studentAdapter = StudentAdapter(this@MainActivity,
                    studentModels as ArrayList<StudentModel>, 0)
                list_item!!.adapter = studentAdapter
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


}


