package edu.hanu.cinematicket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;
    TextInputLayout tlLogin, tlPass, tlConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tlLogin = findViewById(R.id.txtInputLayoutCreateUsername);
        tlPass = findViewById(R.id.txtInputLayoutCreatePassword);
        tlConfirmPass = findViewById(R.id.txtInputLayoutConfirmPassword);
        auth = FirebaseAuth.getInstance();

        ImageButton imgbtnBackToLogin = findViewById(R.id.imgbtnBackToLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
//                auth.crea
                String user = tlLogin.getEditText().getText().toString().trim();
                String pass = tlPass.getEditText().getText().toString().trim();
                String confirmPass = tlConfirmPass.getEditText().getText().toString().trim();
                if (user.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "This field should not be empty", Toast.LENGTH_SHORT).show();
                }
                if (pass.length() > 20 || confirmPass.length() > 20) {
                    Toast.makeText(RegisterActivity.this, "The password has exceed the length", Toast.LENGTH_SHORT).show();
                }
                if (!pass.equals(confirmPass)) {
                    Toast.makeText(RegisterActivity.this, "Please enter again", Toast.LENGTH_SHORT).show();
                }
                auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Added successful", Toast.LENGTH_SHORT).show();
                            Intent intentSussess = new Intent(getApplicationContext(), SuccessActivity.class);
                            startActivity(intentSussess);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Please check again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        imgbtnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBackLogin = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentBackLogin);
            }
        });
    }
}