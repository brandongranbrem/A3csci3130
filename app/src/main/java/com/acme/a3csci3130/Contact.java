package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format.
 * @author Brandon Bremner
 */

public class Contact implements Serializable {


    public  String uid;
    public  String businessNumber;
    public  String name;
    public  String business;
    public  String address;
    public  String province;

    /**
     * Default constructor required for calls to DataSnapshot.getValue
     */
    public Contact() {
    }

    /**
     * Constructor to build a object that represents the contact information for a business
     * @param uid  unique id generated for placement in database
     * @param businessNumber  9-digit business number
     * @param name  name of the business (Between 2 and 48 characters)
     * @param business  primary business role (Fisher, Distributor, Processor, Fish Monger)
     * @param address  address of business (less than 50 characters)
     * @param province  province or territory abbreviation
     */
    public Contact(String uid, String businessNumber, String name, String business, String address, String province){
        this.uid = uid;
        this.businessNumber = businessNumber;
        this.name = name;
        this.business = business;
        this.address = address;
        this.province = province;
    }

    /**
     * Method creates a hash map containing the data related to a business contact object
     * @return  a map of name values pairs representing business contact data
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("businessNumber", businessNumber);
        result.put("name", name);
        result.put("business", business);
        result.put("address", address);
        result.put("province", province);


        return result;
    }
}
