package com.example.postrequestrev

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateDelete : AppCompatActivity() {
    private lateinit var ID_et: EditText
    private lateinit var name_et: EditText
    private lateinit var location_et: EditText
    var id: Int = 0
    var name = ""
    var location = ""
    private lateinit var deleteButton: Button
    private lateinit var updateButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)

        ID_et = findViewById(R.id.etId)
        name_et = findViewById(R.id.et_name)
        location_et = findViewById(R.id.et_location)

        deleteButton = findViewById(R.id.delete)
        deleteButton.setOnClickListener {
            id = ID_et.text.toString().toInt()
            deleteUser()
            clear()
        }

        updateButton = findViewById(R.id.update)
        updateButton.setOnClickListener{
            id = ID_et.text.toString().toInt()
            name = name_et.text.toString()
            location = location_et.text.toString()
            updateUser()
            clear()
        }
    }

    private fun updateUser() {

        val apiInterface = APIClient().getClient()?.create(ApiInterface::class.java)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        apiInterface?.updateUser(id, User.UserDetails(id, name, location))?.enqueue(object :
            Callback<List<User.UserDetails>> {
            override fun onResponse(
                call: Call<List<User.UserDetails>>,
                response: Response<List<User.UserDetails>>,
            ) {
                Toast.makeText(
                    applicationContext, "User Updated Successfully!",
                    Toast.LENGTH_SHORT
                )
                    .show()
                response.body()!!
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<List<User.UserDetails>>, t: Throwable) {
                progressDialog.dismiss()
                call.cancel()
            }

        })


    }

    private fun deleteUser() {
        val apiInterface = APIClient().getClient()?.create(ApiInterface::class.java)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        apiInterface?.deleteUser(id)?.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(
                    applicationContext, "User Deleted Successfully!",
                    Toast.LENGTH_SHORT
                ).show()
                response.body()!!
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                progressDialog.dismiss()
                call.cancel()

            }
        })
    }

    private fun clear() {
        ID_et.setText("")
        name_et.setText("")
        location_et.setText("")
    }

}
