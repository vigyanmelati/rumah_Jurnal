package id.vigyan.rumahjurnal;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DaftarJurnalUserAdapter extends RecyclerView.Adapter <DaftarJurnalUserAdapter.DaftarJurnalUserViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    private OnClickListenerData listenerData;

    public DaftarJurnalUserAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @NonNull
    @Override
    public DaftarJurnalUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cardview_jurnal_user,parent,false);
        return new DaftarJurnalUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarJurnalUserViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }

        String mjudul_jurnal = mCursor.getString(mCursor.getColumnIndex(DBHandler.row_jurnal));
        String mnama_penulis = mCursor.getString(mCursor.getColumnIndex(DBHandler.row_penulis));
        String mdoi = mCursor.getString(mCursor.getColumnIndex(DBHandler.row_doi));
        String mtahun_terbit = mCursor.getString(mCursor.getColumnIndex(DBHandler.row_tahun));
        long id = mCursor.getLong(mCursor.getColumnIndex(DBHandler.row_id_jurnal));

        holder.itemView.setTag(id);
        holder.judul_jurnal.setText(mjudul_jurnal);
        holder.nama_penulis.setText(mnama_penulis);
        holder.doi.setText(mdoi);
        holder.tahun_terbit.setText(mtahun_terbit);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class DaftarJurnalUserViewHolder extends RecyclerView.ViewHolder{
        private TextView judul_jurnal, nama_penulis, doi, tahun_terbit;

        public DaftarJurnalUserViewHolder(@NonNull View itemView) {
            super(itemView);

            judul_jurnal = itemView.findViewById(R.id.judul_jurnal);
            nama_penulis = itemView.findViewById(R.id.nama_penulis);
            doi = itemView.findViewById(R.id.doi);
            tahun_terbit = itemView.findViewById(R.id.tahun_terbit);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long position = (long) itemView.getTag();
                    listenerData.OnItemClickData(position);
                }
            });
        }
    }

    public interface OnClickListenerData{
        void OnItemClickData(long id);
    }

    public void setOnClickListenerData(OnClickListenerData listenerData){
        this.listenerData = listenerData;
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor!=null){
            mCursor.close();
        }
        mCursor=newCursor;

        if(newCursor!=null){
            this.notifyDataSetChanged();
        }
    }

}
