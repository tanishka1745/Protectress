package com.example.protectress.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.protectress.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText forgot_email;
    private Button reset;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        forgot_email=findViewById(R.id.reset_email);
        reset=findViewById(R.id.reset_password);
        firebaseAuth=FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e=forgot_email.getText().toString();
                if(e.isEmpty())
                {
                    Toast.makeText(ForgotPassword.this, "Enter email", Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(e).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ForgotPassword.this, "Email sent", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(ForgotPassword.this, "Failed to sent ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}