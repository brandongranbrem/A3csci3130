package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Activity class to update the contact details of a business
 * @author Brandon Bremner
 */
public class DetailViewActivity extends Activity {

    private EditText businessNumberField, nameField, primaryBusinessField, addressField, provinceField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    /**
     * Method to initialize the GUI elements and pull the business info from the database
     * to populate the fields of the business contact to display
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());

        businessNumberField = (EditText) findViewById(R.id.businessNumber);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            businessNumberField.setText(receivedPersonInfo.businessNumber);
            nameField.setText(receivedPersonInfo.name);
            primaryBusinessField.setText(receivedPersonInfo.business);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);
        }
    }

    /**
     * Method to update the contact information of a business
     * Called when the update button in the GUI is pressed
     * @param v  the current view in the app
     */
    public void updateContact(View v){
        //Retrieve the key of the business being updated
        String businessID = receivedPersonInfo.uid;
        //Retrieve the updated business information
        String businessNumber = businessNumberField.getText().toString();
        String name = nameField.getText().toString();
        String business = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        //Create a new business contact
        Contact businessInfo = new Contact(businessID, businessNumber, name, business, address, province);
        //Push the updated business contact to the database for display in the list
        appState.firebaseReference.child(businessID).setValue(businessInfo);
        finish();
    }

    /**
     * Method to delete the contact information of a business
     * Called when the erase button in the GUI is pressed
     * @param v  the current view in the app
     */
    public void eraseContact(View v){
        //Remove the contact from the database and list
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        finish();
    }
}
