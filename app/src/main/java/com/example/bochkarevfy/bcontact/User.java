package com.example.bochkarevfy.bcontact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    public static final String URL = "http://nbics.net/VSM.Web.Plugins.Contacts/ContactsHome/GetContacts?email=tonitonytoni11@gmail.com&PasswordHash=5fa285e1bebe0a6623e33afc04a1fbd";

    @SerializedName("FirstName")
    @Expose
    private String firstName;

    @SerializedName("SurName")
    @Expose
    private String surName;

    @SerializedName("PhotoUrl")
    @Expose
    private String image;

    public User(String firstName, String surName, String image) {
        this.firstName = firstName;
        this.surName = surName;
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
