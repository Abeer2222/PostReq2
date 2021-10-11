package com.example.postrequestrev

import com.google.gson.annotations.SerializedName

class User {
    var data: List<UserDetails>? = null

    class UserDetails {

        @SerializedName("name")
        var name: String? = null

        @SerializedName("location")
        var location: String? = null

        @SerializedName("id")
        var id: Int? = null

        constructor(id: Int?, name: String?, location: String?) {
            this.name = name
            this.location = location
            this.id = id
        }
    }
}