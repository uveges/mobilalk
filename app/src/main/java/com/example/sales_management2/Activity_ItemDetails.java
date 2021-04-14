package com.example.sales_management2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class Activity_ItemDetails extends AppCompatActivity {

    private String ID_toQuery;
    private SalesLead salesLead;
    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;
    ArrayList<SalesLead> salesLeadtoDisplay;
    private String actual_ID;

    private TextView id_text;
    private TextView href_text;
    private TextView creationDate_text;
    private TextView description_text;
    private TextView name_text;
    private TextView rating_text;
    private TextView referredDate_text;
    private TextView estimatedRevenue_text;

    private EditText href_new ;
    private EditText description_new;
    private EditText name_new;
    private EditText rating_new;
    private EditText referredDate_new;
    private EditText estimatedRevenue_new;

    private DocumentReference docref;

    private NotificationHandler notificationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__item_details);

        //Az intent, ami ide hozott
        Intent intent = getIntent();
        ID_toQuery = intent.getStringExtra("ID_toOpen");

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("SalesLeads");        //Firestore tároló
        salesLeadtoDisplay = new ArrayList<SalesLead>();

        id_text = findViewById(R.id.id_text);
        href_text = findViewById(R.id.href_text);
        creationDate_text = findViewById(R.id.creationDate_text);
        description_text = findViewById(R.id.description_text);
        name_text = findViewById(R.id.name_text);
        rating_text = findViewById(R.id.rating_text);
        referredDate_text = findViewById(R.id.referredDate_text);
        estimatedRevenue_text = findViewById(R.id.estimatedRevenue_text);

        href_new = findViewById(R.id.href_new);
        description_new = findViewById(R.id.description_new);
        name_new = findViewById(R.id.name_new);
        rating_new = findViewById(R.id.rating_new);
        referredDate_new = findViewById(R.id.referredDate_new);
        estimatedRevenue_new = findViewById(R.id.estimatedRevenue_new);

        querydata(mItems, ID_toQuery, "id");

        notificationHandler = new NotificationHandler(this);        //értesítés küldése
    }

    public void querydata(CollectionReference collection, String searchfor, String indatamember){

        //https://medium.com/@scarygami/cloud-firestore-quicktip-documentsnapshot-vs-querysnapshot-70aef6d57ab3
        mItems.whereEqualTo(indatamember, searchfor).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){

                salesLead = documentSnapshot.toObject(SalesLead.class);

                id_text.setText(salesLead.getId());
                actual_ID = salesLead.getId();
                href_text.setText(salesLead.getHref());
                creationDate_text.setText(salesLead.getCreationDate());
                description_text.setText(salesLead.getDescription());
                name_text.setText(salesLead.getName());
                rating_text.setText(salesLead.getRating());
                referredDate_text.setText(salesLead.getReferredDate());
                estimatedRevenue_text.setText(String.valueOf(salesLead.getEstimatedRevenue()));

                docref = documentSnapshot.getReference();
            }
        });
    }

    public void modosit_href(View view) {

        // AsyncUpdater(String fieldname, WeakReference<TextView> updatedUIelement, String newvalue, WeakReference<DocumentReference> docref)
        new AsyncUpdater("href", href_text, href_new.getText().toString(), docref).execute();

        notificationHandler.send(actual_ID + " sikeresen módosítva! :) ");
        href_new.setText("");

//        docref.update("href", href_new.getText().toString());
//        href_text.setText(href_new.getText().toString());
//        href_new.setText("");

    }

    public void modosit_description(View view) {

        new AsyncUpdater("description", description_text, description_new.getText().toString(), docref).execute();

//        docref.update("description", description_new.getText().toString());
//        description_text.setText(description_new.getText().toString());

        description_new.setText("");
        notificationHandler.send(actual_ID + " sikeresen módosítva! :) ");
    }

    public void modosit_name(View view) {

        new AsyncUpdater("name", name_text, name_new.getText().toString(), docref).execute();

//        docref.update("name", name_new.getText().toString());
//        name_text.setText(name_new.getText().toString());

        name_new.setText("");
        notificationHandler.send(actual_ID + " sikeresen módosítva! :) ");
    }

    public void modosit_rating(View view) {

        new AsyncUpdater("rating", rating_text, rating_new.getText().toString(), docref).execute();

//        docref.update("rating", rating_new.getText().toString());
//        rating_text.setText(rating_new.getText().toString());

        rating_new.setText("");
        notificationHandler.send(actual_ID + " sikeresen módosítva! :) ");
    }

    public void modosit_referredDate(View view) {

        new AsyncUpdater("referredDate", referredDate_text, referredDate_new.getText().toString(), docref).execute();

//        docref.update("referredDate", referredDate_new.getText().toString());
//        referredDate_text.setText(referredDate_new.getText().toString());

        referredDate_new.setText("");
        notificationHandler.send(actual_ID + " sikeresen módosítva! :) ");
    }

    public void modosit_estimatedRevenue(View view) {

        new AsyncUpdater("estimatedRevenue", estimatedRevenue_text, estimatedRevenue_new.getText().toString(), docref).execute();

//        docref.update("estimatedRevenue", estimatedRevenue_new.getText().toString());
//        estimatedRevenue_text.setText(estimatedRevenue_new.getText().toString());

        estimatedRevenue_new.setText("");
        notificationHandler.send(actual_ID + " sikeresen módosítva! :) ");
    }

    public void lead_torlese(View view) {

        //docref.delete();
        new AsyncDeleter(docref);

        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);

        notificationHandler.send(actual_ID + " sikeresen törölve! :) ");
    }

    public void ugras_mainactre(View view) {
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);

    }

}