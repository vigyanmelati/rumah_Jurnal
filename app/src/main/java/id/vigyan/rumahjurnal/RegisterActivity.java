package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.zip.Inflater;

public class RegisterActivity extends AppCompatActivity {
    private EditText regis_nama, regis_email, regis_pass, confirm_pass;
    private Button btn_daftar;
    private TextView tv_login;
    String nama_user, email, pass;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHandler = new DBHandler(this);

        confirm_pass = findViewById(R.id.confirm_pass);
        regis_nama = findViewById(R.id.regis_nama);
        regis_email = findViewById(R.id.regis_email);
        regis_pass = findViewById(R.id.regis_pass);
        btn_daftar = findViewById(R.id.btn_daftar);
        tv_login = findViewById(R.id.tv_login);

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValidate();
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

    public void getValidate(){
        if(regis_nama.length() == 0 ) {
            regis_nama.setError("Mohon isi kolom Username");
            regis_nama.requestFocus();
        }else if(regis_email.length() == 0 || Patterns.EMAIL_ADDRESS.matcher(regis_email.getText().toString()).matches() == false){
            regis_email.setError("Isi kolom Email dengan benar");
            regis_email.requestFocus();
        }else if(regis_pass.length() == 0 ){
            regis_pass.setError("Mohon isi kolom Password");
            regis_pass.requestFocus();
        }else if(confirm_pass.length() == 0 ){
            confirm_pass.setError("Mohon isi kolom Konfirmasi Password");
            confirm_pass.requestFocus();
        }else {
            String check_pass = confirm_pass.getText().toString();
            if (regis_pass.getText().toString().equals(check_pass)) {
                sendRegist();
            } else {
                confirm_pass.setError("Konfirmasi password salah, harap gunakan password yang sama");
                confirm_pass.requestFocus();
            }
        }
    }

    public void sendRegist(){
        nama_user = regis_nama.getText().toString();
        email = regis_email.getText().toString();
        pass = regis_pass.getText().toString();
        ContentValues values = new ContentValues();
        values.put(DBHandler.row_nama_user, nama_user);
        values.put(DBHandler.row_email, email);
        values.put(DBHandler.row_password, pass);
        dbHandler.insertDataUser(values);
        DialogFormRegist();
    }

    private void DialogFormRegist(){

        AlertDialog.Builder  dialog = new AlertDialog.Builder(RegisterActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialogs_regist, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent1);
            }

        });
        dialog.show();

    }
}