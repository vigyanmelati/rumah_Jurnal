package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DaftarJurnalUserActivity extends AppCompatActivity {
    private FloatingActionButton tambah_jurnal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_jurnal_user);
        tambah_jurnal = findViewById(R.id.tambah_jurnal);

        tambah_jurnal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), CreateDataActivity.class);
                startActivity(intent1);
            }
        });
    }
}