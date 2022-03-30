package edu.hanu.cinematicket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
//    String user1 = "user1";
//    String pass1 = "123456";

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputLayout txtUsername = findViewById(R.id.txtInputLayoutUsername);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        TextInputLayout txtPassword = findViewById(R.id.txtInputLayoutPassword);

        Button btnLogin = findViewById(R.id.btnLogin);
        TextView txtCreate = findViewById(R.id.txtCreate);

        txtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(createIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = txtUsername.getEditText().getText().toString().trim();
                String pass = txtPassword.getEditText().getText().toString().trim();

                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "These shouldn't be left empty", Toast.LENGTH_SHORT).show();
                }
                if (pass.length() > 20) {
                    Toast.makeText(MainActivity.this, "The password has exceed the length", Toast.LENGTH_SHORT).show();
                }
                auth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Sign In Complete", Toast.LENGTH_SHORT).show();
                            Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(homeIntent);
                        } else {
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                String username1 = txtUsername.getEditText().getText().toString().trim();
//                String password1 = txtPassword.getEditText().getText().toString().trim();
//            if(username1.equals(user1) && password1.equals(pass1)){
//                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
//            }
//            else{
//                Toast.makeText(getApplicationContext(),"Login Fail", Toast.LENGTH_LONG).show();
//            }

            }
        });
    }


}