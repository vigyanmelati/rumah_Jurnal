package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
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
                String email = login_email.getText().toString().trim();
                String password = login_pass.getText().toString().trim();
                boolean result = dbHandler.checkUser(email, password);
                if(result == true){
                    Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent1);
                }else{
                    Toast.makeText(LoginActivity.this, "Login Gagal, Mohon Coba Kembali", Toast.LENGTH_SHORT).show();
                }

            }
        });

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent2);
            }
        });
    }
}