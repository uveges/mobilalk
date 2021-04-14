package com.example.sales_management2;

import android.os.AsyncTask;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;

import java.lang.ref.WeakReference;

public class AsyncUpdater extends AsyncTask<Void, Void, Boolean> {

    private WeakReference<TextView> updatedUIelement;
    private String newvalue;
    private WeakReference<DocumentReference> docref;
    private String fieldname;

    //      RandomAsyncTask(TextView tv) {              //referencia arra, amit változtatni szeretnék
    //        mTextView = new WeakReference<>(tv);
    //    }

    public AsyncUpdater(String fieldname, TextView _updatedUIelement, String newvalue, DocumentReference _docref) {
        this.fieldname = fieldname;
        updatedUIelement = new WeakReference<>(_updatedUIelement);
        this.newvalue = newvalue;
        this.docref = new WeakReference<>(_docref);
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        //referenciából elkérjük a tényleges objektumot, és frissítjük!
        docref.get().update(fieldname, newvalue);

        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        updatedUIelement.get().setText(newvalue);

    }
}
