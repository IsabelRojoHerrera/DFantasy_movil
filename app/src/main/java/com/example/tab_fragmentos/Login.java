package com.example.tab_fragmentos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username;    EditText password;
    Button loginButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // El administrador debe crear los usuarios transportistas desde el sitio web

                if (username.getText().toString().equals("Isabel") && password.getText().toString().equals("1234")) {
                    Toast.makeText(Login.this, "Inicia sesión", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class ));
                    finish();

                } else {
                    Toast.makeText(Login.this, "El usuario o la contraseña ingresados son incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}