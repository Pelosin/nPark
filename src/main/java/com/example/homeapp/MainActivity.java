package com.example.homeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "LoginActivity";
    private EditText emailTxT;
    private EditText passwordTxT;
    private Button botao_login;
    private Button botao_registar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        botao_login = (Button) findViewById(R.id.botao_login);
        botao_registar = (Button) findViewById(R.id.botao_registar);
        emailTxT = (EditText) findViewById(R.id.email) ;
        passwordTxT = (EditText) findViewById(R.id.password);

        botao_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTxT.getText().toString();
                String password = passwordTxT.getText().toString();
                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Preencha os campos todos",
                            Toast.LENGTH_LONG).show();
                } else{
                    loginUser(email, password);
                }
            }
        });

        botao_registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_win = new Intent(MainActivity.this, RegisterWin.class);
                startActivity(register_win);
            }
        });
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }
    public void loginUser(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // updateUI(user);
                            Toast.makeText(MainActivity.this, "Bem-vindo de volta " + email, Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Erro de Autenticação da Conta",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }


}
