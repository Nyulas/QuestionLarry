package com.example.questionlarry;

import android.location.Location;
import android.media.Image;

import java.util.Date;

public class Person {

    private String name;
    private String location;
   // private Image profile_picture;
    private String date_of_birth;
    private String gender;
    private String hobbies;
    private String department;
    private String year_of_study;
    private String expectations;

    public Person(String name, String location, String date_of_birth,
                  String gender, String hobbies, String department, String year_of_study,
                  String expectations) {
        this.name = name;
        this.location = location;
       // this.profile_picture = profile_picture;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.hobbies = hobbies;
        this.department = department;
        this.year_of_study = year_of_study;
        this.expectations = expectations;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    /*public Image getProfile_picture() {
        return profile_picture;
    }*/

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public String getHobbies() {
        return hobbies;
    }

    public String getDepartment() {
        return department;
    }

    public String getYear_of_study() {
        return year_of_study;
    }

    public String getExpectations() {
        return expectations;
    }
}
