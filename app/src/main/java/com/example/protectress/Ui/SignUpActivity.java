package com.example.protectress.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.protectress.Modals.AuthenticateModalClass;
import com.example.protectress.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    private EditText email, password, phn, fullName;
    private Button SignUp;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.signup_mail);
        reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://protectress-23319-default-rtdb.firebaseio.com/");
        password = findViewById(R.id.signUp_password);
        phn = findViewById(R.id.phn);
        fullName = findViewById(R.id.fullname);
        SignUp = findViewById(R.id.sign_up);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ee = email.getText().toString();
                String pp = password.getText().toString();
                String phn1 = phn.getText().toString();
                String nme = fullName.getText().toString();
                reference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(phn1)) {

                        } else {
                            reference.child("Users").child(phn1).child("FullName").setValue(nme);
                            reference.child("Users").child(phn1).child("Email").setValue(ee);
                            reference.child("Users").child(phn1).child("Password").setValue(pp);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                if (ee.isEmpty() || pp.isEmpty() || nme.isEmpty() || phn1.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Required field", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(ee, pp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUpActivity.this, "Please verify email first", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), LoginRegisterActivity.class));
                                        } else {
                                            Toast.makeText(SignUpActivity.this, "Verified failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(SignUpActivity.this, "Already Signed In", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
