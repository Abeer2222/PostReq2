package com.example.postrequestrev

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var addUser: Button
    private lateinit var rvMain: RecyclerView
    private var UsersList = arrayListOf<User.UserDetails>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addUser = findViewById(R.id.addUser)
        rvMain = findViewById(R.id.rvMain)

        rvMain.adapter = UsersAdapter(UsersList)
        rvMain.layoutManager = LinearLayoutManager(this)

        getUsers()

        var updateDeleteBtn = findViewById<Button>(R.id.updateDelete)
        updateDeleteBtn.setOnClickListener {
            val intent = Intent(this, UpdateDelete::class.java)
            startActivity(intent)
        }

        addUser.setOnClickListener {
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Please wait")
            progressDialog.show()
            val intent = Intent(this, NewUsers::class.java)
            startActivity(intent)
        }
    }

    private fun getUsers() {
        val apiInterface = APIClient().getClient()?.create(ApiInterface::class.java)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please Wait")
        progressDialog.show()

        apiInterface?.getUser()?.enqueue(object : Callback<List<User.UserDetails>> {
            override fun onResponse(
                call: Call<List<User.UserDetails>>,
                response: Response<List<User.UserDetails>>
            ) {

                val resource = response.body()!!
                progressDialog.dismiss()
                for (i in resource) {
                    var userName = i.name.toString()
                    var userLocation = i.location.toString()
                    var Id = i.id
                    UsersList.add(User.UserDetails(Id, userLocation, userName))
                }
                rvMain.adapter?.notifyDataSetChanged()
                rvMain.scrollToPosition(UsersList.size - 1)

            }

            override fun onFailure(call: Call<List<User.UserDetails>>, t: Throwable) {
                progressDialog.dismiss()
                call.cancel()
            }
        })

    }

}