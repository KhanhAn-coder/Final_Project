package com.example.a51900475_51900798_finalproject;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {
    private Button btnSignUp;
    private ProgressDialog progressDialog;
    private EditText edtEmailSignUp, edtUserNameSignUp, edtPhoneNumberSignUp, edtPasswordSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Ánh xạ các button và input field
        btnSignUp = findViewById(R.id.btnSignUp);
        edtEmailSignUp = findViewById(R.id.edtEmailSignUp);
        edtUserNameSignUp = findViewById(R.id.edtUserNameSignUp);
        edtPhoneNumberSignUp = findViewById(R.id.edtPhoneNumberSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        progressDialog = new ProgressDialog(this);

        // Tạo onClick listener cho button đăng kí
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatAccount();
            }
        });
    }

    private void CreatAccount() {

        String email = edtEmailSignUp.getText().toString();
        String name = edtUserNameSignUp.getText().toString();
        String phone = edtPhoneNumberSignUp.getText().toString();
        String password = edtPasswordSignUp.getText().toString();

        //Kiểm tra các input đầu vào
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Vui lòng nhập Email", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Vui lòng nhập Username", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Vui lòng nhập password", Toast.LENGTH_SHORT).show();
        }
        else{

            progressDialog.setTitle("Đang kiểm tra thông tin tài khoản");
            progressDialog.setMessage("Vui lòng đợi");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();


            //Hàm này dùng để kiểm tra xem email hoặc số điện thoại đã được đăng kí chưa
            ValidateExist(email, name, phone, password);
        }

    }

    private void ValidateExist(final String email,final String name, final String phone, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Kiểm tra nếu thông tin hợp lệ và trên database chưa có thì thêm vào database
                //Thông tin đã có trong database sẽ hiển thị thông báo Toast
                if (!(snapshot.child("Users").child(phone).exists())){

                        HashMap<String, Object> userData = new HashMap<>();
                        userData.put("Email", email);
                        userData.put("Username", name);
                        userData.put("Phone", phone);
                        userData.put("Password", password);

                        RootRef.child("Users").child(phone).updateChildren(userData)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(SignUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                            Intent intent = new Intent(SignUp.this, SignIn.class);
                                            startActivity(intent);
                                        }
                                        else {
                                            progressDialog.dismiss();
                                            Toast.makeText(SignUp.this, "Đã xảy ra lỗi khi đăng ký, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(SignUp.this, "Số điện thoại đã được đăng ký, vui lòng chọn số khác",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}