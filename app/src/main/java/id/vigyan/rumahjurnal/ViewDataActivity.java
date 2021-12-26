package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewDataActivity extends AppCompatActivity {
    private EditText viewJudul, viewNama, viewVol, viewIssn, viewDoi, viewPenulis, viewTahun, viewLink, viewKategori;
    private Button btn_view;
    private Intent intent2;
    private long id;
    DBHandler dbHandler;
    String linkdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        intent2 = getIntent();
        id = intent2.getLongExtra("ed_id",0);
        dbHandler = new DBHandler(this);
        viewJudul = findViewById(R.id.viewJudul);
        viewNama = findViewById(R.id.viewNama);
        viewVol = findViewById(R.id.viewVol);
        viewIssn = findViewById(R.id.viewIssn);
        viewDoi = findViewById(R.id.viewDoi);
        viewPenulis = findViewById(R.id.viewPenulis);
        viewLink = findViewById(R.id.viewLink);
        viewTahun = findViewById(R.id.viewTahun);
        viewKategori = findViewById(R.id.viewKategori);
        btn_view = findViewById(R.id.btn_view);
        btn_view.setOnClickListener(clickListener);
        viewJudul.setFocusable(false);
        viewNama.setFocusable(false);
        viewVol.setFocusable(false);
        viewIssn.setFocusable(false);
        viewDoi.setFocusable(false);
        viewPenulis.setFocusable(false);
        viewLink.setFocusable(false);
        viewTahun.setFocusable(false);
        viewKategori.setFocusable(false);
        getData();
    }

    private void getData(){
        Cursor cursor = dbHandler.getDataJurnal(id);
        if(cursor.moveToFirst()){
            String juduldb = cursor.getString(cursor.getColumnIndex(DBHandler.row_jurnal));
            String namadb = cursor.getString(cursor.getColumnIndex(DBHandler.row_nama_jurnal));
            String voldb = cursor.getString(cursor.getColumnIndex(DBHandler.row_volume));
            String issndb = cursor.getString(cursor.getColumnIndex(DBHandler.row_issn));
            String doidb = cursor.getString(cursor.getColumnIndex(DBHandler.row_doi));
            String penulisdb = cursor.getString(cursor.getColumnIndex(DBHandler.row_penulis));
            String tahundb = cursor.getString(cursor.getColumnIndex(DBHandler.row_tahun));
            linkdb = cursor.getString(cursor.getColumnIndex(DBHandler.row_link));
            String kategoridb = cursor.getString(cursor.getColumnIndex(DBHandler.row_kategori));
            int id_userdb = cursor.getInt(cursor.getColumnIndex(DBHandler.row_id_user_jurnal));

            viewJudul.setText(juduldb);
            viewNama.setText(namadb);
            viewVol.setText(voldb);
            viewIssn.setText(issndb);
            viewDoi.setText(doidb);
            viewPenulis.setText(penulisdb);
            viewLink.setText(linkdb);
            viewTahun.setText(tahundb);
            viewKategori.setText(kategoridb);
        }

    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openURL();
        }
    };

    private void openURL() {
        Toast.makeText(this, linkdb, Toast.LENGTH_SHORT).show();
        Uri uri = Uri.parse(linkdb);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}