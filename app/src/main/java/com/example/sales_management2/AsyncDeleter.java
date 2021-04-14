package com.example.sales_management2;

import android.os.AsyncTask;

import com.google.firebase.firestore.DocumentReference;

import java.lang.ref.WeakReference;

public class AsyncDeleter extends AsyncTask<Void, Void, Void> {

    private WeakReference<DocumentReference> docref;

    public AsyncDeleter(DocumentReference _docref) {
        this.docref = new WeakReference<>(_docref);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        docref.get().delete();
        return null;
    }


}
