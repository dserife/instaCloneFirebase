package com.example.instaclonefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    //değişken tanımlama
    private FirebaseAuth firebaseAuth;
    EditText etEmail;
    EditText etSifre;
    Button signUp;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //tanımlanan değişkenlere değer atanması
        firebaseAuth = FirebaseAuth.getInstance();
        etEmail=findViewById(R.id.etEmail);
        etSifre=findViewById(R.id.etSifre);

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        //uygulamaya giriş yapmış bir kullanıcı var ise FeedAcvitiy sınıfı açılacaktır.
        if (firebaseUser != null){
            Intent intent=new Intent(SignUpActivity.this,FeedActivity.class);
            startActivity(intent);
            finish();
        }
    }

    //sign in(giriş yap) butonuna ait onclick metodu
    public void signInClick (View view){

        //email ve şifre fonksiyonlarına değer ataması yaptık
        String email=etEmail.getText().toString();
        String sifre=etSifre.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(email,sifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                //giriş başarılı ise FeedActivity sınıfına geç.(bir sonraki sayfaya)
                Intent intent=new Intent(SignUpActivity.this,FeedActivity.class);
                startActivity(intent);
                finish();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                //giriş başarısız ise ekrana gelecek hata mesajı
                Toast.makeText(SignUpActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();

            }
        });

    }

    //sign up (üye ol) butonuna ait onclick metodu
    public void signUpClick (View view){

        //email ve şifre fonksiyonlarına değer ataması yaptık
        String email=etEmail.getText().toString();
        String sifre=etSifre.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(email,sifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            //kayıt başarılı olursa ekrana ne mesaj gelmesi gerektiğiyle ilgili method
            @Override
            public void onSuccess(AuthResult authResult) {

                Toast.makeText(SignUpActivity.this,"Kayıt Başarılı!",Toast.LENGTH_LONG).show();

                //kayıt başarılı ise FeedActivity sınıfına geç.(bir sonraki sayfaya)
                Intent intent=new Intent(SignUpActivity.this,FeedActivity.class);
                startActivity(intent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            //kayıt başarısız olursa ekrana ne mesaj gelmesi gerektiğiyle ilgili method
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(SignUpActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });



    }
}