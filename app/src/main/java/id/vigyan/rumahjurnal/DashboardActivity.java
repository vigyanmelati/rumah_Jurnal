package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                case R.id.editProfil : intent = new Intent(DashboardActivity.this,MainActivity.class);startActivity(intent);break;
                case R.id.tentangKami : intent = new Intent(DashboardActivity.this,TentangKamiActivity.class);startActivity(intent);break;
                case R.id.logout : intent = new Intent(DashboardActivity.this,LoginActivity.class);startActivity(intent);break;
                default:break;
            }

        }
    };

}