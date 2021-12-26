package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import id.vigyan.rumahjurnal.Auth.Auth;

public class DashboardActivity extends AppCompatActivity {
    private CardView daftarJurnalUmum, daftarJurnalAnda, editProfil, tentangKami;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


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