package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private EditText regis_nama, regis_email, regis_pass;
    private Button btn_daftar;
    private TextView tv_login;
    String nama_user, email, pass;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHandler = new DBHandler(this);

        regis_nama = findViewById(R.id.regis_nama);
        regis_email = findViewById(R.id.regis_email);
        regis_pass = findViewById(R.id.regis_pass);
        btn_daftar = findViewById(R.id.btn_daftar);
        tv_login = findViewById(R.id.tv_login);

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama_user = regis_nama.getText().toString();
                email = regis_email.getText().toString();
                pass = regis_pass.getText().toString();
                ContentValues values = new ContentValues();
                values.put(DBHandler.row_nama_user, nama_user);
                values.put(DBHandler.row_email, email);
                values.put(DBHandler.row_password, pass);
                dbHandler.insertDataUser(values);
                Intent intent1 = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent1);
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent2);
            }
        });
    }
}