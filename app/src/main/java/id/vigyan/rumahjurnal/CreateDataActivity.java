package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateDataActivity extends AppCompatActivity {
    private EditText createJudul, createNama, createVol, createIssn, createDoi, createPenulis, createTahun, createLink;
    private Spinner createKategori;
    final Calendar calendar = Calendar.getInstance();
    private Button btn_simpan, btn_batal;
    private TextView create_data;
    String judul, nama, vol, issn, doi,penulis, tahun, kategori, link;
    int id_user;
    DBHandler dbHandler;
    private long id;
    private Intent intent2;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data);

        intent2 = getIntent();
        id = intent2.getLongExtra(DBHandler.row_id_jurnal,0);

        if(intent2.hasExtra(DBHandler.row_id_jurnal)){
//            create_data.setText("Edit Data Jurnal");
            setTitle("Edit Data");
        }else{
//            create_data.setText("Buat Data Jurnal Baru");
            setTitle("Tambah Data");
        }

        create_data = findViewById(R.id.createData);
        createJudul = findViewById(R.id.createJudul);
        createNama = findViewById(R.id.createNama);
        createVol = findViewById(R.id.createVol);
        createIssn = findViewById(R.id.createIssn);
        createDoi = findViewById(R.id.createDoi);
        createPenulis = findViewById(R.id.createPenulis);
        createLink = findViewById(R.id.createLink);
        createTahun = findViewById(R.id.createTahun);
        createKategori = findViewById(R.id.createKategori);
        btn_simpan = findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(clickListener);
        btn_batal = findViewById(R.id.btn_batal);
        btn_batal.setOnClickListener(clickListener);
        dbHandler = new DBHandler(this);
        createTahun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateDataActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR,year);
                        SimpleDateFormat simpleDateFormatTanggal=new SimpleDateFormat("yyyy");
                        createTahun.setText(simpleDateFormatTanggal.format(calendar.getTime()));
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
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
            String kategoridb = cursor.getString(cursor.getColumnIndex(DBHandler.row_kategori));
            String linkdb = cursor.getString(cursor.getColumnIndex(DBHandler.row_penulis));
            int id_userdb = cursor.getInt(cursor.getColumnIndex(DBHandler.row_id_user_jurnal));

//            createKategori.setSelection(getPosition(kategoridb));
            createJudul.setText(juduldb);
            createNama.setText(namadb);
            createVol.setText(voldb);
            createIssn.setText(issndb);
            createDoi.setText(doidb);
            createPenulis.setText(penulisdb);
            createTahun.setText(tahundb);
            createLink.setText(linkdb);
        }

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
                    Toast.makeText(CreateDataActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    DialogForm();
                    judul = createJudul.getText().toString();
                    nama = createNama.getText().toString();
                    vol = createVol.getText().toString();
                    issn = createIssn.getText().toString();
                    doi = createDoi.getText().toString();
                    penulis = createPenulis.getText().toString();
//                    id_user  = Integer.parseInt(String.valueOf(createIdUser.getText()));
                    tahun = createTahun.getText().toString();
                    link = createLink.getText().toString();
                    kategori = createKategori.getSelectedItem().toString();

                    ContentValues values = new ContentValues();
                    values.put(DBHandler.row_jurnal, judul);
                    values.put(DBHandler.row_nama_jurnal, nama);
                    values.put(DBHandler.row_volume, vol);
                    values.put(DBHandler.row_issn, issn);
                    values.put(DBHandler.row_doi, doi);
                    values.put(DBHandler.row_penulis, penulis);
                    values.put(DBHandler.row_id_user_jurnal, id_user);
                    values.put(DBHandler.row_tahun, tahun);
                    values.put(DBHandler.row_link, link);
                    values.put(DBHandler.row_kategori, kategori);
                    if(intent2.hasExtra(DBHandler.row_id_jurnal)){
                        dbHandler.updateDataJurnal(values,id);
                    }else {
                        dbHandler.insertDataJurnal(values);
                    }
                    break;
            }
        }
    };

    private void DialogForm(){
        dialog = new AlertDialog.Builder(CreateDataActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.alert_dialogs, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                Intent intent1 = new Intent(CreateDataActivity.this, DaftarJurnalUserActivity.class);
                startActivity(intent1);
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