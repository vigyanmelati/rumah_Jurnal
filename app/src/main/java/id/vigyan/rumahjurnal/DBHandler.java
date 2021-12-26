package id.vigyan.rumahjurnal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    public static final String db_name="db_rumahjurnal";
    public static final String table_user="tb_user";
    public static final String table_jurnal="tb_jurnal";
    public static final int VER=4;

    public static final String row_id_user ="id_user";
    public static final String row_nama_user ="nama_user";
    public static final String row_email ="email";
    public static final String row_password="password";

    public static final String row_id_jurnal="id_jurnal";
    public static final String row_jurnal="jurnal";
    public static final String row_nama_jurnal="nama_jurnal";
    public static final String row_volume="volume";
    public static final String row_issn="issn";
    public static final String row_doi="doi";
    public static final String row_tahun="tahun";
    public static final String row_penulis="penulis";
    public static final String row_kategori="kategori";
    public static final String row_link="link";
    public static final String row_id_user_jurnal="id_user_jurnal";

    private SQLiteDatabase db;

    public DBHandler(Context context) {
        super(context, db_name, null, VER);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON;");
        String query ="CREATE TABLE " + table_user + "("
                + row_id_user + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_nama_user + " TEXT,"
                + row_email + " TEXT,"
                + row_password + " TEXT)";
        db.execSQL(query);

        String query2 ="CREATE TABLE " + table_jurnal + "("
                + row_id_jurnal + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_jurnal + " TEXT,"
                + row_nama_jurnal + " TEXT,"
                + row_volume + " TEXT,"
                + row_issn + " TEXT,"
                + row_doi + " TEXT,"
                + row_tahun + " TEXT,"
                + row_penulis + " TEXT,"
                + row_kategori + " TEXT,"
                + row_link + " TEXT,"
                + row_id_user_jurnal + " INTEGER,"
                + " FOREIGN KEY ("+row_id_user_jurnal+") REFERENCES "+table_user+"("+row_id_user+"));";
        db.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ table_user);
        db.execSQL("DROP TABLE IF EXISTS "+ table_jurnal);
        onCreate(db);
    }
    public void insertDataJurnal(ContentValues values){
        db.insert(table_jurnal,null,values);
    }

    public void updateDataJurnal(ContentValues values, long id){
        db.update(table_jurnal,values,row_id_jurnal + "=" + id, null);
    }

    public void deleteDataJurnal(long id){
        db.delete(table_jurnal, row_id_jurnal + "=" + id, null);
    }

    public Cursor getAllDataJurnal(){
        return db.query(table_jurnal,null,null,null,null, null,null);
    }

    public Cursor getDataJurnal(long id){
        return db.rawQuery("SELECT*FROM " + table_jurnal + " WHERE " + row_id_jurnal + "=" + id, null);
    }

    public Cursor getDataJurnalUser(long id){
        return db.rawQuery("SELECT*FROM " + table_jurnal + " WHERE " + row_id_user_jurnal + "=" + id, null);
    }

    public void insertDataUser(ContentValues values){
        db.insert(table_user,null,values);
    }

    public void updateDataUser(ContentValues values, long id){
        db.update(table_user,values,row_id_user + "=" + id, null);
    }
    
    public boolean checkUser(String email, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        String [] columns = {row_id_user};
        String selections = row_email + "=?" + " and " + row_password + "=?";
        String [] selectionArgs = {email, password};
        Cursor cursor = db.query(table_user, columns, selections, selectionArgs, null, null, null);
        int count = cursor.getCount();
        db.close();
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
}
