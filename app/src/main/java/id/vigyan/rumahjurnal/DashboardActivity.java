package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.vigyan.rumahjurnal.Auth.Auth;

public class DashboardActivity extends AppCompatActivity {
    private TextView tv_nama_user;
    private CardView daftarJurnalUmum, daftarJurnalAnda, editProfil, tentangKami;
    private Button logout;
    private long current_user;
    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dbHandler = new DBHandler(this);

        current_user = Auth.getInstance().getPreferenceCurentUser(DashboardActivity.this);

        tv_nama_user = findViewById(R.id.tv_nama_user);
        logout = findViewById(R.id.logout);
        daftarJurnalUmum = findViewById(R.id.daftarJurnalUmum);
        daftarJurnalAnda = findViewById(R.id.daftarJurnalAnda);
        editProfil = findViewById(R.id.editProfil);
        tentangKami = findViewById(R.id.tentangKami);

        daftarJurnalUmum.setOnClickListener(clickListener);
        daftarJurnalAnda.setOnClickListener(clickListener);
        editProfil.setOnClickListener(clickListener);
        tentangKami.setOnClickListener(clickListener);
        logout.setOnClickListener(clickListener);

        getUser();
    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;

            switch (v.getId()){
                case R.id.daftarJurnalUmum : intent = new Intent(DashboardActivity.this,DaftarJurnalActivity.class);startActivity(intent);break;
                case R.id.daftarJurnalAnda : intent = new Intent(DashboardActivity.this,DaftarJurnalUserActivity.class);startActivity(intent);break;
                case R.id.editProfil : intent = new Intent(DashboardActivity.this, EditUserActivity.class);startActivity(intent);break;
                case R.id.tentangKami : intent = new Intent(DashboardActivity.this,TentangKamiActivity.class);startActivity(intent);break;
                case R.id.logout : DialogFormRegist();break;
                default:break;
            }

        }
    };

    private void getUser(){
        Cursor cursor = dbHandler.getUser(current_user);
        if(cursor.moveToFirst()){
            String nama = cursor.getString((Integer) cursor.getColumnIndex(DBHandler.row_nama_user));

            tv_nama_user.setText(nama);

        }
    }
    public void getLogout(){
        Auth.getInstance().logout(getApplicationContext());
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void DialogFormRegist(){

        AlertDialog.Builder  dialog = new AlertDialog.Builder(DashboardActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialogs_logout, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getLogout();
            }

        }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}