package com.example.sales_management2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class MainScreenActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();
    private FirebaseUser user;
    private RecyclerView recyclerView;
    private ArrayList<MiniSalesItem> miniSalesLeads;
    private MiniSalesLeadAdapter adapter;

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;
    private FirebaseAuth mAuth;
    private FirebaseUser User;
    String creator;

    private int gridnumber = 1;         //oszlopok száma, default 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        user = FirebaseAuth.getInstance().getCurrentUser();     //ellenőrizzük, létezik-e a user?
        if (user != null) {
            Log.d(LOG_TAG, "Authenticated user!");
        } else {
            Log.d(LOG_TAG, "Unauthenticated user!");
            finish();
        }

        recyclerView = findViewById(R.id.recyclerview);     //A keret, ami tartja majd a kissebb darabokat
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridnumber));
        miniSalesLeads = new ArrayList<>();
        adapter = new MiniSalesLeadAdapter(this, miniSalesLeads);

        recyclerView.setAdapter(adapter);

        System.out.println("elindult! :) ");

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("SalesLeads");        //Firestore tároló
        mAuth = FirebaseAuth.getInstance();
        User = mAuth.getCurrentUser();
        creator = User.getEmail();

        initializeData();

    }

    private void initializeData(){

        miniSalesLeads.clear();

        //csak az adott felhasználó által készített rekordokat hozzuk le!!!
        mItems.whereEqualTo("creator", creator).orderBy("id").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document: queryDocumentSnapshots) {
                try {
                    MiniSalesItem item = document.toObject(MiniSalesItem.class);
                    miniSalesLeads.add(item);
                } catch (Exception e){
                    System.out.println(document.getData().toString());
                }
            }
            adapter.notifyDataSetChanged();
        });
    }

    //menusav hozzaadasahoz
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);                        //keresés!!!

        getMenuInflater().inflate(R.menu.menubar, menu);
        MenuItem menuItem = menu.findItem(R.id.kereses);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("Keresés ID-re");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(LOG_TAG, newText);
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
   }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.kereses:
                Log.d(LOG_TAG, "Kereses kattintva");
                return true;
            case R.id.hozzaadas:
                //elem hozzaadasára szolgáló intent / layout
                Intent intent = new Intent(this, add_item_activity.class);
                startActivity(intent);

                Log.d(LOG_TAG, "Hozzaadas kattintva");
                return true;

            case R.id.kilepes:
                Log.d(LOG_TAG, "Kilepes kattintva");
                FirebaseAuth.getInstance().signOut();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public void reszletek(){

    }
}