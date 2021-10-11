package com.example.postrequestrev

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewUsers : AppCompatActivity() {
    private lateinit var btnSave: Button
    private lateinit var btnView: Button
    private lateinit var etName: EditText
    private lateinit var etLoction: EditText
    var name = " "
    var location = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_users)

        etName = findViewById(R.id.etName)
        etLoction = findViewById(R.id.etLocation)
        btnSave = findViewById(R.id.btnSave)
        btnView = findViewById(R.id.btnView)
        btnSave.setOnClickListener {

            name = etName.text.toString()
            location = etLoction.text.toString()
            addUser()
            Toast.makeText(this, "User added Successfully", Toast.LENGTH_SHORT).show()
            etName.setText("")
            etLoction.setText("")
        }
        btnView.setOnClickListener {
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Please Wait")
            progressDialog.show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addUser() {

        val apiInterface = APIClient().getClient()?.create(ApiInterface::class.java)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please Wait")
        progressDialog.show()

        apiInterface?.addUser(User.UserDetails(id = null, name, location))
            ?.enqueue(object : Callback<List<User.UserDetails>> {

                override fun onFailure(call: Call<List<User.UserDetails>>, t: Throwable) {
                    progressDialog.dismiss()
                    call.cancel()
                }

                override fun onResponse(
                    call: Call<List<User.UserDetails>>,
                    response: Response<List<User.UserDetails>>
                ) {

                    response.body()!!
                    progressDialog.dismiss()
                }

            }
            )

    }
}