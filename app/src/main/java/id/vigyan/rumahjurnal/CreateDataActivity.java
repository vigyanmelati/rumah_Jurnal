package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateDataActivity extends AppCompatActivity {
    private EditText createJudul, createNama, createVol, createIssn, createDoi, createPenulis, createIdUser;
    private Spinner createTahun, createKategori;
    private Button btn_simpan, btn_batal;
    String judul, nama, vol, issn, doi,penulis, tahun, kategori;
    int id_user;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data);

        createJudul = findViewById(R.id.createJudul);
        createNama = findViewById(R.id.createNama);
        createVol = findViewById(R.id.createVol);
        createIssn = findViewById(R.id.createIssn);
        createDoi = findViewById(R.id.createDoi);
        createPenulis = findViewById(R.id.createPenulis);
        createIdUser = findViewById(R.id.createIdUser);
        createTahun = findViewById(R.id.createTahun);
        createKategori = findViewById(R.id.createKategori);
        btn_simpan = findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(clickListener);
        btn_batal = findViewById(R.id.btn_batal);
        btn_batal.setOnClickListener(clickListener);
        dbHandler = new DBHandler(this);
    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_batal :
                    Intent intentbatal = new Intent(getApplicationContext(), DaftarJurnalUserActivity.class);
                    startActivity(intentbatal);
                    break;
                case R.id.btn_simpan :
                    judul = createJudul.getText().toString();
                    nama = createNama.getText().toString();
                    vol = createVol.getText().toString();
                    issn = createIssn.getText().toString();
                    doi = createDoi.getText().toString();
                    penulis = createPenulis.getText().toString();
                    id_user  = Integer.parseInt(String.valueOf(createIdUser.getText()));
                    tahun = createTahun.getSelectedItem().toString();
                    kategori = createKategori.getSelectedItem().toString();

                    ContentValues values = new ContentValues();
                    values.put(DBHandler.row_jurnal, judul);
                    values.put(DBHandler.row_nama_jurnal, nama);
                    values.put(DBHandler.row_volume, vol);
                    values.put(DBHandler.row_issn, issn);
                    values.put(DBHandler.row_doi, doi);
                    values.put(DBHandler.row_penulis, penulis);
                    values.put(DBHandler.row_id_user, id_user);
                    values.put(DBHandler.row_tahun, tahun);
                    values.put(DBHandler.row_kategori, kategori);
                    dbHandler.insertDataJurnal(values);
                    break;
            }
        }
    };
}