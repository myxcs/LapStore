package com.example.lapstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lapstore.Model.UserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginScreen extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference reference;
    public static final String SHARED_PREFS = "sharedPrefs";

    private EditText loginEmail, loginPassword;
    private CheckBox rememberMe;
    String remember;
    private Button btnLogin;
    private TextView signupRedirectText;
    boolean banStatus;
    UserModel userModel = null;

    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.ed_email);
        loginPassword = findViewById(R.id.ed_password);
       // rememberMe = findViewById(R.id.checkbox);
        btnLogin = findViewById(R.id.btn_login);
        signupRedirectText = findViewById(R.id.tv_register);


        //xem qua dữ liệu trong checkbox với SharedPreferences, oke thì cho đăng nhập luôn
//        SharedPreferences sharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
//        remember = sharedPreferences.getString("remember", "");
//        if (remember.equals("true")) {
//            checkBanStatus();
//        } else if (remember.equals("false")) {
//            Toast.makeText(this, "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
//        }

        //xử lí nút đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                       // checkBanStatus();
                                        Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(LoginScreen.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginScreen.this, "Đăng nhập thất bại, vui lòng kiểm tra lại email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    } else {
                        loginPassword.setError("Vui lòng nhập lại mật khẩu");
                    }
                } else if (email.isEmpty()) {
                    loginEmail.setError("Vui lòng nhập email");
                } else {
                    loginEmail.setError("Vui lòng điền đầy đủ email");
                }
            }

        });

        // xử lí check box remember me
//        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (compoundButton.isChecked()) {
//                    SharedPreferences sharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("remember", "true");
//                    editor.apply();
//                    Toast.makeText(LoginActivity.this, "Checked", Toast.LENGTH_SHORT).show();
//                } else if (!compoundButton.isChecked()) {
//                    SharedPreferences sharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("remember", "false");
//                    editor.apply();
//                    Toast.makeText(LoginActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        //đơn giản là chuyển qua trang đăng ký
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this, RegisterScreen.class));
            }
        });
    }

    //check ban status
//    private void checkBanStatus() {
//        //check thử có tk sẵn không
//        try {
//            final DatabaseReference getBanStatus = database.getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/banStatus");
//            getBanStatus.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    banStatus = snapshot.getValue(Boolean.class);
//
//                    if (banStatus == true) {
//                        remember = "false";
//                        Toast.makeText(LoginActivity.this, "Tài khoản đã bị khoá", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                    }
//
//                    //  Toast.makeText(LoginActivity.this, ""+banStatus, Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(LoginActivity.this, "Error to get ban status", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        catch (Exception e)
//        {
//            Toast.makeText(LoginActivity.this, "Không có tài khoản đăng nhập", Toast.LENGTH_SHORT).show();
//        }
//    }
}