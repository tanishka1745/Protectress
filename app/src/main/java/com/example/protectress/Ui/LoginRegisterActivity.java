package com.example.protectress.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.protectress.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginRegisterActivity extends AppCompatActivity {

    private TextView go_to_signUp;
    private EditText email,password,phn1;
    private Button SignIn;
    private FirebaseAuth firebaseAuth;
    private TextView forgot;
    private DatabaseReference reference;

    private FirebaseUser firebaseUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        go_to_signUp=findViewById(R.id.go_to_signup);
        email=findViewById(R.id.login_email);
        password=findViewById(R.id.login_password);
        SignIn=findViewById(R.id.sign_in);
        forgot=findViewById(R.id.forgot);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        phn1=findViewById(R.id.phn_login);
        reference=FirebaseDatabase.getInstance().getReferenceFromUrl("https://protectress-23319-default-rtdb.firebaseio.com/");
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
            }
        });
        go_to_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ee=email.getText().toString();
                String pp=password.getText().toString();
                if(ee.isEmpty() || pp.isEmpty())
                {
                    Toast.makeText(LoginRegisterActivity.this, "Required field", Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.signInWithEmailAndPassword(ee,pp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                if(firebaseAuth.getCurrentUser().isEmailVerified())
                                {
                                    startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
                                }
                                else{
                                    Toast.makeText(LoginRegisterActivity.this, "Sign Up first", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(LoginRegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }

}