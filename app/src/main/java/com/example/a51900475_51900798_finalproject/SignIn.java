package com.example.a51900475_51900798_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a51900475_51900798_finalproject.Users.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    EditText edtPhoneSignIn, edtPasswordSignIn;
    private Button btnLogin, btnCreateAccount, btnAdmin;
    private ProgressDialog progressDialog;
    private String dbParentName = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPhoneSignIn = findViewById(R.id.edtPhoneSignIn);
        edtPasswordSignIn = findViewById(R.id.edtPasswordSignIn);
        progressDialog = new ProgressDialog(this);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInClick();
            }
        });

        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccountClick();
            }
        });

        //Đăng nhâp tài khoản admin
        btnAdmin = findViewById(R.id.btnAdmin);
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnAdmin.getText().toString().equals("Admin?")){
                    btnLogin.setText("Admin login");
                    btnAdmin.setText("User?");
                    dbParentName = "Admins";
                }else if(btnAdmin.getText().toString().equals("User?")){
                    btnLogin.setText("Login");
                    btnAdmin.setText("Admin?");
                    dbParentName = "Users";
                }

            }
        });




    }
    //Chuyển người dùng đến trang đăng nhập
    private void createAccountClick() {
        Intent intent = new Intent(SignIn.this, SignUp.class);
        startActivity(intent);
    }

    private void SignInClick() {
        String phone = edtPhoneSignIn.getText().toString();
        String password = edtPasswordSignIn.getText().toString();

        //Kiểm tra các input đầu vào
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.setTitle("Đang kiểm tra thông tin tài khoản");
            progressDialog.setMessage("Vui lòng đợi");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            CheckCredentials(phone, password);
        }
    }

    private void CheckCredentials(String phone, String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(dbParentName).child(phone).exists()){
                    Users users = snapshot.child(dbParentName).child(phone).getValue(Users.class);
                    String phoneInput = users.getPhone();
                    String passwordInput = users.getPassword();
                    Log.d("Phones: ", users.getPhone());
                    Log.d("Password: ", users.getPassword());
                    if (phoneInput.equals(phone)){
                        if (passwordInput.equals(password)){

                            if (dbParentName.equals("Admins")){
                                Toast.makeText(SignIn.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                Intent intent = new Intent(SignIn.this, AddProductCategory.class);
                                startActivity(intent);
                            }
                            else if (dbParentName.equals("Users")){
                                Toast.makeText(SignIn.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                                Intent intent = new Intent(SignIn.this, HomePage.class);
                                LoggedUser.loggedUser = users;
                                startActivity(intent);
                            }
                        }else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(SignIn.this, "Sai thông tin tài khoản, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(SignIn.this, "Sai thông tin tài khoản, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}