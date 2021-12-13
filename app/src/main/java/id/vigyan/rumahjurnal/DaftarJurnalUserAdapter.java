package id.vigyan.rumahjurnal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DaftarJurnalUserAdapter extends RecyclerView.Adapter <DaftarJurnalUserAdapter.DaftarJurnalUserViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public DaftarJurnalUserAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @NonNull
    @Override
    public DaftarJurnalUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarJurnalUserViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DaftarJurnalUserViewHolder extends RecyclerView.ViewHolder{
        private TextView judul_jurnal, nama_penulis, doi, tahun_terbit;

        public DaftarJurnalUserViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
