package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DaftarJurnalUserActivity extends AppCompatActivity {
    private RecyclerView jurnal_list;
    private DaftarJurnalUserAdapter dataAdapter;
    private DBHandler database;
    private FloatingActionButton tambah_jurnal;
    private LinearLayout linearEditData, linearHapusData;
    private Button btn_batalPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_jurnal_user);
        tambah_jurnal = findViewById(R.id.tambah_jurnal);
        database = new DBHandler(this);

        tambah_jurnal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), CreateDataActivity.class);
                startActivity(intent1);
            }
        });

        jurnal_list = findViewById(R.id.jurnal_list);

        jurnal_list.setLayoutManager(new LinearLayoutManager(this));
        dataAdapter = new DaftarJurnalUserAdapter(this,database.getAllDataJurnal());
        jurnal_list.setAdapter(dataAdapter);

        dataAdapter.setOnClickListenerData(new DaftarJurnalUserAdapter.OnClickListenerData() {
            @Override
            public void OnItemClickData(long id) {
                LayoutInflater inflater = LayoutInflater.from(DaftarJurnalUserActivity.this);
                View view = inflater.inflate(R.layout.popup_data,null);
                linearEditData = view.findViewById(R.id.linearEditData);
                linearHapusData = view.findViewById(R.id.linearHapusData);
                btn_batalPopup = view.findViewById(R.id.btn_batalPopup);
                Dialog popupData = new Dialog(DaftarJurnalUserActivity.this);
                popupData.setContentView(view);
                popupData.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupData.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        linearEditData.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent editData = new Intent(DaftarJurnalUserActivity.this, MainActivity.class);
                                editData.putExtra(DBHandler.row_id_jurnal, id);
                                startActivity(editData);
                                popupData.dismiss();
                            }
                        });

                        linearHapusData.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(DaftarJurnalUserActivity.this);
                                builder.setTitle("Konfirmasi");
                                builder.setMessage("Apakah Anda yakin ingin menghapus data ?");
                                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        database.deleteDataJurnal(id);
                                        popupData.dismiss();
                                        dataAdapter.swapCursor(database.getAllDataJurnal());
                                    }
                                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        popupData.dismiss();
                                    }
                                });
                                AlertDialog popupKonfirmasi = builder.create();
                                popupKonfirmasi.show();
                            }
                        });

                        btn_batalPopup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupData.dismiss();
                            }
                        });

                    }
                });

            }
        });


    }
}