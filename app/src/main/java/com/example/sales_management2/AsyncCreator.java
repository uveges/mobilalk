package com.example.sales_management2;

import android.os.AsyncTask;

import com.google.firebase.firestore.CollectionReference;

import java.lang.ref.WeakReference;

public class AsyncCreator extends AsyncTask<Void, Void, Void> {

    private WeakReference<CollectionReference> collref;
    private WeakReference<SalesLead> salesLead;

    public AsyncCreator(CollectionReference _collref, SalesLead _salesLead) {
        this.collref = new WeakReference<>(_collref);
        this.salesLead = new WeakReference<>(_salesLead);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        collref.get().add(salesLead.get());

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
