package com.rshdevelopment.rsh.servertest;

import java.io.Serializable;

/**
 * Created by ScottHanlon on 7/24/15.
 */
public class CustomClass implements Serializable{

    String FirstName;
    String LastName;
    String Hobby;
    String PhoneNumber;


    public CustomClass(String firstName, String lastName, String hobby, String phoneNumber) {
        FirstName = firstName;
        LastName = lastName;
        Hobby = hobby;
        PhoneNumber = phoneNumber;
    }


}
