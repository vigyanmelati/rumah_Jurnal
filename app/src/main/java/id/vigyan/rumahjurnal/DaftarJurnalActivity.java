package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class DaftarJurnalActivity extends AppCompatActivity {
    private RecyclerView jurnal_list;
    private DaftarJurnalAdapter dataAdapter;
    private DBHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_jurnal);
        database = new DBHandler(this);
        jurnal_list = findViewById(R.id.jurnal_list);

        jurnal_list.setLayoutManager(new LinearLayoutManager(this));
        dataAdapter = new DaftarJurnalAdapter(this,database.getAllDataJurnal());
        jurnal_list.setAdapter(dataAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataAdapter.swapCursor(database.getAllDataJurnal());
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(DaftarJurnalActivity.this, DashboardActivity.class);
        startActivity(myIntent);
        finish();
        return;
    }
}