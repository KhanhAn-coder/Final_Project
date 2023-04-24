package com.example.a51900475_51900798_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    EditText edtUserNameSignIn, edtPasswordSignIn;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtUserNameSignIn = findViewById(R.id.edtUserNameSignIn);
        edtPasswordSignIn = findViewById(R.id.edtPasswordSignIn);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUserNameSignIn.getText().toString().equals("")){
                    Toast.makeText(SignIn.this, "Vui lòng nhập username", Toast.LENGTH_SHORT).show();
                }else if (edtPasswordSignIn.getText().toString().equals("")){
                    Toast.makeText(SignIn.this, "Vui lòng nhập password", Toast.LENGTH_SHORT).show();
                }else if (!edtUserNameSignIn.getText().toString().equals("KhanhAn7501") || !edtPasswordSignIn.getText().toString().equals("12345")){
                    Toast.makeText(SignIn.this, "Sai username hoặc password", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(SignIn.this, HomePage.class);
                    intent.putExtra("code","Success");
                    startActivity(intent);
                }
            }
        });
    }
}