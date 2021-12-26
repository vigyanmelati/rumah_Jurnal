package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.vigyan.rumahjurnal.Auth.Auth;

public class LoginActivity extends AppCompatActivity {
    private Auth login;
    private EditText login_email, login_pass;
    private Button btn_login;
    private TextView tv_signup;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHandler = new DBHandler(this);

        login_email = findViewById(R.id.login_email);
        login_pass = findViewById(R.id.login_pass);
        btn_login = findViewById(R.id.btn_login);
        tv_signup = findViewById(R.id.tv_signup);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValidate();
            }
        });

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegister();
            }
        });
    }
    private void getValidate(){
        if(login_email.length() == 0 || Patterns.EMAIL_ADDRESS.matcher(login_email.getText().toString()).matches() == false){
            login_email.setError("Isi kolom Email dengan benar");
            login_email.requestFocus();
        }else if(login_pass.length() == 0 ){
            login_pass.setError("Mohon isi kolom Password");
            login_pass.requestFocus();
        }else {
            getLogin();
        }
    }

    private void toDashboard() {
        Intent intent= new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);
    }

    private void toRegister() {
        Intent intent2 = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent2);
    }

    public void getLogin(){
        String email = login_email.getText().toString().trim();
        String password = login_pass.getText().toString().trim();
        Auth auth_login = new Auth();
        login = auth_login.login(LoginActivity.this, email, password);

        if(login == null){
            login_email.setError("Mungkin username kamu salah");
            login_pass.setError("Atau pasword kamu salah");
        }else {
            Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
            toDashboard();
        }
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAffinity(this);
    }

}