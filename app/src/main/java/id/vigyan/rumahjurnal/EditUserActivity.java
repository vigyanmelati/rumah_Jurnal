package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.vigyan.rumahjurnal.Auth.Auth;

public class EditUserActivity extends AppCompatActivity {
    private EditText edt_nama, edt_email, edt_password, edt_confirm_password;
    private Button btn_simpan;
    private long current_user;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        dbHandler = new DBHandler(this);

        current_user = Auth.getInstance().getPreferenceCurentUser(EditUserActivity.this);

        edt_nama = findViewById(R.id.edt_nama);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_pass);
        edt_confirm_password = findViewById(R.id.edt_confirm_pass);

        btn_simpan = findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        getUser();
    }

    private void getUser(){
        Cursor cursor = dbHandler.getUser(current_user);
        if(cursor.moveToFirst()){
            String email = cursor.getString((Integer) cursor.getColumnIndex(DBHandler.row_email));
            String nama = cursor.getString((Integer) cursor.getColumnIndex(DBHandler.row_nama_user));
            String password = cursor.getString((Integer) cursor.getColumnIndex(DBHandler.row_password));

            edt_nama.setText(nama);
            edt_email.setText(email);
            edt_password.setText(password);
            edt_confirm_password.setText(password);

        }
    }

    public void sendEdit(){
        String nama = edt_nama.getText().toString();
        String email = edt_email.getText().toString();
        String password = edt_password.getText().toString();
        ContentValues values = new ContentValues();
        values.put(DBHandler.row_nama_user, nama);
        values.put(DBHandler.row_email, email);
        values.put(DBHandler.row_password, password);
        dbHandler.updateDataUser(values, current_user);
    }
}