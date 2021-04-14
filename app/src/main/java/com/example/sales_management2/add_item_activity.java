package com.example.sales_management2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class add_item_activity extends AppCompatActivity {

    EditText hrefET;
    EditText descriptionET;
    EditText nameET;
    EditText ratingET;
    EditText referredDateET;
    EditText typeET;
    EditText estimatedRevenueET;

    private FirebaseFirestore mFirestore;       //firestore referencia
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    String creator;
    private CollectionReference mItems;         //firestore egy kollekciója
    private int ID;

    private static final String LOG_TAG = MainActivity.class.getName();
    private NotificationHandler notificationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        hrefET = findViewById(R.id.href_create);
        descriptionET = findViewById(R.id.description_create);
        nameET = findViewById(R.id.name_create);
        ratingET = findViewById(R.id.rating_create);
        referredDateET = findViewById(R.id.referredDate_create);
        typeET = findViewById(R.id.type_create);
        estimatedRevenueET = findViewById(R.id.estimatedRevenue_create);

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("SalesLeads");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        creator = user.getEmail();

        notificationHandler = new NotificationHandler(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void rogzit(View view) {

        String hrefStr = hrefET.getText().toString();
        String descriptionStr = descriptionET.getText().toString();
        String nameStr = nameET.getText().toString();
        String ratingStr = ratingET.getText().toString();
        String referredDateStr = referredDateET.getText().toString();
        String typeStr = typeET.getText().toString();
        int estimatedRevenue = Integer.parseInt(estimatedRevenueET.getText().toString());

        Random random = new Random();
        ID = random.nextInt(100000);

        SalesLead salesLead = new SalesLead(
                creator,
                String.valueOf(ID),
                hrefStr,
                descriptionStr,
                nameStr,
                ratingStr,
                referredDateStr,
                typeStr,
                estimatedRevenue
        );

        //keletkezes ideje:
        salesLead.setCreationDate(referredDateStr);

        //utolsó módosítás
        salesLead.setStatusChangedDate(referredDateStr);

        //érvényesség:
        String[] tmp = referredDateStr.split(" ");
        String validityStr = "";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tmp[0]);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 3);
            validityStr = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()) + " " + tmp[1];

        } catch (ParseException e) {
            e.printStackTrace();
        }
        salesLead.setValidFor(validityStr);

        new AsyncCreator(mItems, salesLead).execute();
        notificationHandler.send("Sikeres.");

//        mItems.add(salesLead).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(add_item_activity.this, "Added sucessfully! ", Toast.LENGTH_SHORT).show();
//                Log.d(this.getClass().toString(), "Sikeres!");
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(add_item_activity.this, "ERROR" + e.toString(), Toast.LENGTH_SHORT).show();
//                Log.d("TAG", e.toString());
//            }
//        });

        this.vissza_mainActivityre();

    }

    public void vissza_mainActivityre() {

        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);

    }
}