package com.example.homeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class RegisterWin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "LoginActivity";
    private Button botao_register;
    private EditText emailTxT;
    private EditText passwordTxT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_win);

        botao_register = (Button) findViewById(R.id.registerButton);
        emailTxT = (EditText) findViewById(R.id.email);
        passwordTxT = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        botao_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTxT.getText().toString();
                String password = passwordTxT.getText().toString();
                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(RegisterWin.this, "Preencha os campos todos",
                            Toast.LENGTH_LONG).show();
                } else{
                    registerUser(email, password);
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }

    public void registerUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // updateUI(user);
                            Toast.makeText(RegisterWin.this, "Conta Criada Com " +
                                    "Sucesso!", Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "Erro de Autenticação da Conta", task.getException());
                            Toast.makeText(RegisterWin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                        // ...
                    }
                });
    }

}