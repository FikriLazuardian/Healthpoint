package com.healthpoint.medic.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.healthpoint.medic.Perilaku_Higienis;
import com.healthpoint.medic.model.Anggota_Keluarga;
import com.healthpoint.medic.model.Higienis;
import com.healthpoint.medic.model.Keluarga;
import com.healthpoint.medic.model.Penyakit;
import com.healthpoint.medic.model.User;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 9/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "Healthpoint.db";

    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_PENYAKIT = "penyakit";
    private static final String TABLE_ANGGOTA_KELUARGA = "anggota_keluarga";
    private static final String TABLE_KELUARGA = "keluarga";
    private static final String TABLE_KEBERSIHAN_DIRI = "kebersihan_diri";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NIK = "nik";
    private static final String COLUMN_USER_NIP = "nip";
    private static final String COLUMN_USER_NAME = "name";
    private static final String COLUMN_USER_ALAMAT = "alamat";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_USERNAME = "username";

    //Penyakit Table Columns names
    private static final String COLUMN_ID_SAKIT = "id_sakit";
    private static final String COLUMN_KONSUMSI_ROKOK = "konsumsi_rokok";
    private static final String COLUMN_USIA_MEROKOK = "usia_pertama_merokok";
    private static final String COLUMN_BATANG_ROKOK = "berapa_batang";
    private static final String COLUMN_BATUK = "batuk";
    private static final String COLUMN_BATUK_DISERTAI = "batuk_disertai";
    private static final String COLUMN_DIAGNOSA_TB = "terdiagnosa_tb";
    private static final String COLUMN_PEMERIKSAAN_DOKTER = "diperiksa_dokter";
    private static final String COLUMN_CARA_PEMERIKSAAN = "cara_pemeriksaan";
    private static final String COLUMN_KONSUMSI_OBAT_TB = "konsumsi_obat_tb";
    private static final String COLUMN_DIAGNOSA_SEMBUH = "terdiagnosa_sembuh";

    //Anggota_Keluarga Table Columns names
    private static final String COLUMN_NIK = "nik";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_TEMPAT_LAHIR = "tempat_lahir";
    private static final String COLUMN_TANGGAL_LAHIR = "tanggal_lahir";
    private static final String COLUMN_JENIS_KELAMIN = "jenis_kelamin";
    private static final String COLUMN_UMUR = "umur";
    private static final String COLUMN_AGAMA = "agama";
    private static final String COLUMN_STATUS_KAWIN = "status_kawin";
    private static final String COLUMN_HUB_KK = "hubungan_kk";
    private static final String COLUMN_PENDIDIKAN = "pendidikan";
    private static final String COLUMN_PEKERJAAN = "pekerjaan";
    private static final String COLUMN_JKN = "jkn";
    private static final String COLUMN_HAMIL = "hamil";
    private static final String COLUMN_DISABILITAS = "disabilitas";

    //Keluarga Table Columns names
    private static final String COLUMN_NOKK = "no_kk";
    private static final String COLUMN_PROVINSI = "provinsi";
    private static final String COLUMN_KAB = "kab_kota";
    private static final String COLUMN_KEC = "kecamatan";
    private static final String COLUMN_RW = "rw";
    private static final String COLUMN_RT = "rt";
    private static final String COLUMN_NORMH = "no_rmh";
    private static final String COLUMN_KODPOS = "kode_pos";
    private static final String COLUMN_SUMBER_AIR = "sumber_air";
    private static final String COLUMN_JAMBAN = "jamban";
    private static final String COLUMN_SAL_PEMBUANGANSMPH = "saluran_pembuangansmph";
    private static final String COLUMN_SAL_PEMBUANGANAIR = "saluran_pembuanganair";

    //Kebersihan_diri Table Columns names
    private static final String COLUMN_ID_KEBERSIHAN_DIRI = "id_kebersihan_diri";
    private static final String COLUMN_CUCI_TANGAN = "mencuci_tangan";
    private static final String COLUMN_LOK_BAB = "lokasi_bab";
    private static final String COLUMN_KPNSIKATGIGI = "kpnsikat_gigi";
    private static final String COLUMN_SIKATGIGI = "sikat_gigi";


    private static DatabaseHelper sInstance;
    public static synchronized DatabaseHelper getInstance(Context context){
        if(sInstance ==null){
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create table user sql query
         String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NIP + " INTEGER NOT NULL,"
                + COLUMN_USER_NIK+ " INTEGER NOT NULL, "+ COLUMN_USER_NAME+ " TEXT NOT NULL, "+ COLUMN_USER_PASSWORD + " TEXT NOT NULL," + COLUMN_USER_ALAMAT+ " TEXT NOT NULL, "+ COLUMN_USER_USERNAME+"TEXT NOT NULL"+ ")";

        // create table penyakit sql query
         String CREATE_PENYAKIT_TABLE = "CREATE TABLE " + TABLE_PENYAKIT + "(" + COLUMN_ID_SAKIT + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_KONSUMSI_ROKOK + " TEXT NOT NULL, " + COLUMN_USIA_MEROKOK + " TEXT NOT NULL, " + COLUMN_BATANG_ROKOK + " TEXT NOT NULL, " +
                COLUMN_BATUK + " TEXT NOT NULL, " + COLUMN_BATUK_DISERTAI + " TEXT NOT NULL, " + COLUMN_DIAGNOSA_TB + " TEXT NOT NULL, " + COLUMN_PEMERIKSAAN_DOKTER +
                " TEXT NOT NULL, " + COLUMN_CARA_PEMERIKSAAN + " TEXT NOT NULL, " + COLUMN_KONSUMSI_OBAT_TB + " TEXT NOT NULL, " + COLUMN_DIAGNOSA_SEMBUH + " TEXT NOT NULL " + ")";

        //create table anggota_keluarga sql query
         String CREATE_ANGGOTA_KELUARGA_TABLE = "CREATE TABLE " + TABLE_ANGGOTA_KELUARGA + "(" +
                COLUMN_NIK + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAMA + " TEXT NOT NULL, " + COLUMN_TEMPAT_LAHIR + " TEXT NOT NULL, " + COLUMN_TANGGAL_LAHIR
                + " INTEGER NOT NULL, " + COLUMN_JENIS_KELAMIN + " TEXT NOT NULL, " + COLUMN_UMUR + " INTEGER NOT NULL, " + COLUMN_AGAMA + " TEXT NOT NULL, " + COLUMN_STATUS_KAWIN + " TEXT NOT NULL, " +
                COLUMN_HUB_KK + " TEXT NOT NULL, " + COLUMN_PENDIDIKAN + " TEXT NOT NULL, " + COLUMN_PEKERJAAN + " TEXT NOT NULL, " + COLUMN_JKN + " TEXT NOT NULL, " + COLUMN_HAMIL + " TEXT NOT NULL, " +COLUMN_DISABILITAS+ " TEXT NOT NULL "+ ")";
        //create table keluarga sql query
         String CREATE_KELUARGA_TABLE = "CREATE TABLE " + TABLE_KELUARGA + "(" +
                COLUMN_NOKK + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + COLUMN_PROVINSI + " TEXT NOT NULL, " + COLUMN_KAB + " TEXT NOT NULL, " + COLUMN_KEC + " TEXT NOT NULL, " +
                COLUMN_RW + " INTEGER NOT NULL, " + COLUMN_RT + " INTEGER NOT NULL, " + COLUMN_NORMH + " INTEGER NOT NULL, " + COLUMN_KODPOS + " INTEGER NOT NULL, " + COLUMN_SUMBER_AIR + " TEXT NOT NULL, " +
                COLUMN_JAMBAN + " TEXT NOT NULL, " + COLUMN_SAL_PEMBUANGANSMPH + " TEXT NOT NULL, " + COLUMN_SAL_PEMBUANGANAIR + " TEXT NOT NULL " + ")";
        //create table kebersihan_diri sql query
         String CREATE_KEBERSIHAN_DIRI_TABLE = "CREATE TABLE " + TABLE_KEBERSIHAN_DIRI + "(" +
                COLUMN_ID_KEBERSIHAN_DIRI + " TEXT NOT NULL, " + COLUMN_CUCI_TANGAN + " TEXT NOT NULL, " + COLUMN_LOK_BAB + " TEXT NOT NULL, " + COLUMN_SIKATGIGI + " TEXT NOT NULL, " + COLUMN_KPNSIKATGIGI + "TEXT NOT NULL " + ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PENYAKIT_TABLE);
        db.execSQL(CREATE_ANGGOTA_KELUARGA_TABLE);
        db.execSQL(CREATE_KELUARGA_TABLE);
        db.execSQL(CREATE_KEBERSIHAN_DIRI_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        //Drop Penyakit table if exist
        db.execSQL(DROP_PENYAKIT_TABLE);
        //Drop Anggota_keluarga table if exist
        db.execSQL(DROP_ANGGOTA_KELUARGA_TABLE);
        //Drop Keluarga table if exist
        db.execSQL(DROP_KELUARGA_TABLE);
        //Drop Kebersihan_diri table if exist
        db.execSQL(DROP_KEBERSIHAN_DIRI);
        // Create tables again
        onCreate(db);

    }
    // drop table sql query
    String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    String DROP_PENYAKIT_TABLE = "DROP TABLE IF EXISTS "+TABLE_PENYAKIT;
    String DROP_ANGGOTA_KELUARGA_TABLE = "DROP TABLE IF EXISTS " + TABLE_ANGGOTA_KELUARGA;
    String DROP_KELUARGA_TABLE="DROP TABLE IF EXISTS "+ TABLE_KELUARGA;
    String DROP_KEBERSIHAN_DIRI="DROP TABLE IF EXISTS "+TABLE_KEBERSIHAN_DIRI;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NIK, user.getNik());
        values.put(COLUMN_USER_NIP, user.getNip());
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_ALAMAT, user.getAlamat());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_USERNAME, user.getUsername());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }
    public void addPenyakit(Penyakit penyakit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_SAKIT, penyakit.getId_sakit());
        values.put(COLUMN_BATUK, penyakit.getBatuk());
        values.put(COLUMN_BATUK_DISERTAI, penyakit.getGejala_batuk());
        values.put(COLUMN_DIAGNOSA_TB, penyakit.getTerdiagnosa_tb());
        values.put(COLUMN_PEMERIKSAAN_DOKTER, penyakit.getPeriksa_dokter());
        values.put(COLUMN_CARA_PEMERIKSAAN, penyakit.getCara_pemeriksaan());
        values.put(COLUMN_KONSUMSI_OBAT_TB, penyakit.getKonsumsi_obat());
        values.put(COLUMN_DIAGNOSA_SEMBUH, penyakit.getTerdiagnosa_sembuh());


        // Inserting Row
        db.insert(TABLE_PENYAKIT, null, values);
        db.close();
    }
    public void addAnggota_Keluarga(Anggota_Keluarga anggota_keluarga) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NIK, anggota_keluarga.getNik());
        values.put(COLUMN_KONSUMSI_ROKOK, anggota_keluarga.getNama());
        values.put( COLUMN_TEMPAT_LAHIR, anggota_keluarga.getTempat_lahir());
        values.put(COLUMN_TANGGAL_LAHIR, anggota_keluarga.getTanggal_lahir());
        values.put(COLUMN_JENIS_KELAMIN, anggota_keluarga.getJenis_kelamin());
        values.put(COLUMN_UMUR, anggota_keluarga.getUmur());
        values.put(COLUMN_AGAMA, anggota_keluarga.getAgama());
        values.put(COLUMN_STATUS_KAWIN, anggota_keluarga.getStatus_kawin());
        values.put(COLUMN_HUB_KK, anggota_keluarga.getHubungan_kk());
        values.put(COLUMN_PENDIDIKAN, anggota_keluarga.getPendidikan());
        values.put(COLUMN_PEKERJAAN, anggota_keluarga.getPekerjaan());
        values.put(COLUMN_JKN, anggota_keluarga.getJkn());
        values.put(COLUMN_HAMIL, anggota_keluarga.getHamil());
        values.put(COLUMN_DISABILITAS,anggota_keluarga.getDisabilitas());
        // Inserting Row
        db.insert(TABLE_ANGGOTA_KELUARGA, null, values);
        db.close();
    }
    public void addKeluarga(Keluarga keluarga) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOKK, keluarga.getNo_kk());
        values.put(COLUMN_RW, keluarga.getRw());
        values.put(COLUMN_RT, keluarga.getRt());
        values.put(COLUMN_NORMH, keluarga.getNo_rmh());
        values.put(COLUMN_KODPOS, keluarga.getKode_pos());
        values.put(COLUMN_SUMBER_AIR, keluarga.getSumber_air());
        values.put(COLUMN_JAMBAN, keluarga.getJamban());
        values.put(COLUMN_SAL_PEMBUANGANSMPH, keluarga.getSaluran_pembuangansmph());
        values.put(COLUMN_SAL_PEMBUANGANAIR, keluarga.getSaluran_pembuanganair());
        // Inserting Row
        db.insert(TABLE_KELUARGA, null, values);
        db.close();
    }
    public void addKebersihan_diri(Higienis kebersihan_diri){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_KEBERSIHAN_DIRI,kebersihan_diri.getId_kebersihandiri());
        values.put(COLUMN_CUCI_TANGAN,kebersihan_diri.getCuci_tangan());
        values.put(COLUMN_LOK_BAB,kebersihan_diri.getLokbab());
        values.put(COLUMN_SIKATGIGI,kebersihan_diri.getSikat_gigi());
        values.put(COLUMN_KPNSIKATGIGI,kebersihan_diri.getKpnsikat_gigi());
        db.insert(TABLE_KEBERSIHAN_DIRI,null,values);
        db.close();

    }
    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_NIK,
                COLUMN_USER_NIP,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_ALAMAT,
                COLUMN_USER_USERNAME
        };

        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";

        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setNik(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NIK)));
                user.setNip(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NIP)));
                user.setNama(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setAlamat(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ALAMAT)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USER_USERNAME)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public List<Higienis> getAllKebersihanDiri() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_ID_KEBERSIHAN_DIRI,
                COLUMN_CUCI_TANGAN,
                COLUMN_LOK_BAB,
                COLUMN_SIKATGIGI,
                COLUMN_KPNSIKATGIGI};

        // sorting orders
        String sortOrder =
                COLUMN_ID_KEBERSIHAN_DIRI + " ASC";

        List<Higienis> kebersihandiriList = new ArrayList<Higienis>();
        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_KEBERSIHAN_DIRI, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Higienis higienis = new Higienis();
                higienis.setId_kebersihandiri(cursor.getString(cursor.getColumnIndex(COLUMN_ID_KEBERSIHAN_DIRI)));
                higienis.setCuci_tangan(cursor.getString(cursor.getColumnIndex(COLUMN_CUCI_TANGAN)));
                higienis.setSikat_gigi(cursor.getString(cursor.getColumnIndex(COLUMN_SIKATGIGI)));
                higienis.setLokbab(cursor.getString(cursor.getColumnIndex(COLUMN_LOK_BAB)));
                higienis.setKpnsikat_gigi(cursor.getString(cursor.getColumnIndex(COLUMN_KPNSIKATGIGI)));
                // Adding user record to list
                kebersihandiriList.add(higienis);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return kebersihandiriList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NIK, user.getNik());
        values.put(COLUMN_USER_NIP, user.getNip());
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_ALAMAT, user.getAlamat());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_USERNAME, user.getUsername());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void updatePenyakit(Penyakit penyakit){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_SAKIT, penyakit.getId_sakit());
        values.put(COLUMN_BATUK, penyakit.getBatuk());
        values.put(COLUMN_BATUK_DISERTAI, penyakit.getGejala_batuk());
        values.put(COLUMN_DIAGNOSA_TB, penyakit.getTerdiagnosa_tb());
        values.put(COLUMN_PEMERIKSAAN_DOKTER, penyakit.getPeriksa_dokter());
        values.put(COLUMN_CARA_PEMERIKSAAN, penyakit.getCara_pemeriksaan());
        values.put(COLUMN_KONSUMSI_OBAT_TB, penyakit.getKonsumsi_obat());
        values.put(COLUMN_DIAGNOSA_SEMBUH, penyakit.getTerdiagnosa_sembuh());

        // updating row
        db.update(TABLE_PENYAKIT, values, COLUMN_ID_SAKIT + " = ?",
                new String[]{String.valueOf(penyakit.getId_sakit())});
        db.close();
    }

    public void updateAnggotaKeluarga(Anggota_Keluarga anggota_keluarga){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NIK, anggota_keluarga.getNik());
        values.put(COLUMN_KONSUMSI_ROKOK, anggota_keluarga.getNama());
        values.put( COLUMN_TEMPAT_LAHIR, anggota_keluarga.getTempat_lahir());
        values.put(COLUMN_TANGGAL_LAHIR, anggota_keluarga.getTanggal_lahir());
        values.put(COLUMN_JENIS_KELAMIN, anggota_keluarga.getJenis_kelamin());
        values.put(COLUMN_UMUR, anggota_keluarga.getUmur());
        values.put(COLUMN_AGAMA, anggota_keluarga.getAgama());
        values.put(COLUMN_STATUS_KAWIN, anggota_keluarga.getStatus_kawin());
        values.put(COLUMN_HUB_KK, anggota_keluarga.getHubungan_kk());
        values.put(COLUMN_PENDIDIKAN, anggota_keluarga.getPendidikan());
        values.put(COLUMN_PEKERJAAN, anggota_keluarga.getPekerjaan());
        values.put(COLUMN_JKN, anggota_keluarga.getJkn());
        values.put(COLUMN_HAMIL, anggota_keluarga.getHamil());
        // updating row
        db.update(TABLE_ANGGOTA_KELUARGA, values, COLUMN_NIK + " = ?",
                new String[]{String.valueOf(anggota_keluarga.getNik())});
        db.close();
    }

    public void updateKeluarga(Keluarga keluarga){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOKK, keluarga.getNo_kk());
        values.put(COLUMN_RW, keluarga.getRw());
        values.put(COLUMN_RT, keluarga.getRt());
        values.put(COLUMN_NORMH, keluarga.getNo_rmh());
        values.put(COLUMN_KODPOS, keluarga.getKode_pos());
        values.put(COLUMN_SUMBER_AIR, keluarga.getSumber_air());
        values.put(COLUMN_JAMBAN, keluarga.getJamban());
        values.put(COLUMN_SAL_PEMBUANGANSMPH, keluarga.getSaluran_pembuangansmph());
        values.put(COLUMN_SAL_PEMBUANGANAIR, keluarga.getSaluran_pembuanganair());
        // updating row
        db.update(TABLE_KELUARGA, values, COLUMN_NOKK + " = ?",
                new String[]{String.valueOf(keluarga.getNo_kk())});
        db.close();
    }
    public void upsdateKebersihanDiri(Higienis higienis) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_KEBERSIHAN_DIRI,higienis.getId_kebersihandiri());
        values.put(COLUMN_SIKATGIGI, higienis.getSikat_gigi());
        values.put(COLUMN_CUCI_TANGAN, higienis.getCuci_tangan());
        values.put(COLUMN_LOK_BAB, higienis.getLokbab());
        values.put(COLUMN_KPNSIKATGIGI,higienis.getKpnsikat_gigi());
        // updating row
        db.update(TABLE_KEBERSIHAN_DIRI, values, COLUMN_ID_KEBERSIHAN_DIRI + " = ?",
                new String[]{String.valueOf(higienis.getId_kebersihandiri())});
        db.close();
    }
    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void deletePenyakit(Penyakit penyakit){
        SQLiteDatabase db = this.getWritableDatabase();
        //delete penyakit record by id_sakit
        db.delete(TABLE_PENYAKIT, COLUMN_ID_SAKIT+"=?",
                new String[]{String.valueOf(penyakit.getId_sakit())});
        db.close();
    }

    public void deleteAnggota_keluarga(Anggota_Keluarga anggota_keluarga){
        SQLiteDatabase db = this.getWritableDatabase();
        //delete anggota_keluarga record by nik
        db.delete(TABLE_ANGGOTA_KELUARGA, COLUMN_NIK+"=?",
                new String[]{String.valueOf(anggota_keluarga.getNik())});
    }

    public void deleteKeluarga(Keluarga keluarga){
        SQLiteDatabase db = this.getWritableDatabase();
        //delete keluarga record by no_kk
        db.delete(TABLE_KELUARGA, COLUMN_NOKK+"=?",
                new String[]{String.valueOf(keluarga.getNo_kk())});
    }
    public void deleteKebersihanDiri(Higienis higienis){
        SQLiteDatabase db = this.getWritableDatabase();
        //delete keluarga record by no_kk
        db.delete(TABLE_KEBERSIHAN_DIRI, COLUMN_ID_KEBERSIHAN_DIRI+"=?",
                new String[]{String.valueOf(higienis.getId_kebersihandiri())});
    }
    /**
     * This method to check user exist or not
     *
     * @param username
     * @return true/false
     */
    public boolean checkUser(String username) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_USERNAME + " = ?";

        // selection argument
        String[] selectionArgs = {username};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkKebersihanDri(String idkebersihandiri) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_ID_KEBERSIHAN_DIRI
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_ID_KEBERSIHAN_DIRI + " = ?";

        // selection argument
        String[] selectionArgs = {idkebersihandiri};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_KEBERSIHAN_DIRI, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param username
     * @param password
     * @return true/false
     */
    public boolean checkUser(String username, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_USERNAME + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {username, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}