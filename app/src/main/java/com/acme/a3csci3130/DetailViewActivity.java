package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private EditText businessNumberField, nameField, primaryBusinessField, addressField, provinceField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

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

    public void updateContact(View v){
        //Retrieve the key of the business being updated
        String personID = receivedPersonInfo.uid;
        //Retrieve the updated business information
        String businessNumber = businessNumberField.getText().toString();
        String name = nameField.getText().toString();
        String business = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        //Create a new business contact
        Contact person = new Contact(personID, businessNumber, name, business, address, province);
        //Push the updated business contact to the database and list
        appState.firebaseReference.child(personID).setValue(person);
        finish();
    }

    public void eraseContact(View v){
        //Remove the contact from the database and list
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        finish();
    }
}
