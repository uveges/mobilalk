package com.example.sales_management2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText usernameET;
    private EditText passwordET;
    private FirebaseAuth mauth;
    private static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = findViewById(R.id.EditTextUserName);        //elkérem a view -tól az adott elemet ID alapján
        passwordET = findViewById(R.id.EditTextPassword);       //a két globális változó megkapja a referenciát

        mauth = FirebaseAuth.getInstance();

    }

    public void login(View view) {

        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();

        //Váltás az egyes "nézetek" között: intent példányosít, objektum ot elküld az androidnak; indítson egy activity-t amit ez az intent reprezentál

        mauth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Log.d(LOG_TAG, "Bejelentkezes sikeres: " + username);
                    Toast.makeText(MainActivity.this, "Sikeres belepes. ", Toast.LENGTH_LONG).show();
                    logMeIn();
                } else {
                    Toast.makeText(MainActivity.this, "Sikertelen belepes. " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    Log.d(LOG_TAG, "Bejelentkezes sikertelen: " + username);
                    finish();
                }
            }
        });

    }

    public void logMeIn(){

        //HA sikeres volt a bejelentkezés, nyissuk meg a főképernyőt
                                    //kontext           // MIT nyisson meg?
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
    }
}