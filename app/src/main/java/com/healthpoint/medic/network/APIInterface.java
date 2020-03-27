package com.healthpoint.medic.network;


import com.healthpoint.medic.model.AccessToken;
import com.healthpoint.medic.model.DaftarRumahItem;
import com.healthpoint.medic.model.DataPengguna;
import com.healthpoint.medic.model.ResponseKunjunganPengguna;
import com.healthpoint.medic.model.ResponseLapKader;
import com.healthpoint.medic.model.ResponseLapKoor;
import com.healthpoint.medic.model.ResponseRingkasanKunjungan;
import com.healthpoint.medic.model.PostKunjungan;
import com.healthpoint.medic.model.ResponseDetilKunjungan;
import com.healthpoint.medic.model.ResponseFoto;
import com.healthpoint.medic.model.PostDataIndividu;
import com.healthpoint.medic.model.PostDataJumantik;
import com.healthpoint.medic.model.PostDataKeluarga;
import com.healthpoint.medic.model.PostResponse;
import com.healthpoint.medic.model.ResponseAgama;
import com.healthpoint.medic.model.ResponseDaftarKunjungan;
import com.healthpoint.medic.model.ResponseDaftarRumah;
import com.healthpoint.medic.model.ResponseDetilKeluarga;
import com.healthpoint.medic.model.ResponseJenisRumah;
import com.healthpoint.medic.model.ResponseJkn;
import com.healthpoint.medic.model.ResponseNikah;
import com.healthpoint.medic.model.ResponsePekerjaan;
import com.healthpoint.medic.model.ResponsePendidikan;
import com.healthpoint.medic.model.ResponseRelasiKK;
import com.healthpoint.medic.model.ResponseDaftarKeluarga;
import com.healthpoint.medic.model.ResponseRokokSebulan;
import com.healthpoint.medic.model.ResponseKelurahan;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIInterface {
// Fungsi ini untuk memanggil API

    @FormUrlEncoded
    @POST("signin")
    Call<AccessToken> loginRequest(@Field("username") String username,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("signup")
    Call<AccessToken> register(@Field("username") String username,
                               @Field("password") String password);

    @GET("relasi-kk")
    Call<ResponseRelasiKK> getAPIrelasiKK();

    @GET("agama")
    Call<ResponseAgama> getAPIAgama();

    @GET("pendidikan")
    Call<ResponsePendidikan> getAPIPendidikan();

    @GET("jkn")
    Call<ResponseJkn> getAPIJkn();

    @GET("status-nikah")
    Call<ResponseNikah> getAPIstatusNikah();

    @GET("pekerjaan")
    Call<ResponsePekerjaan> getAPIPekerjaan();

    @GET("rokok-sebulan")
    Call<ResponseRokokSebulan> getAPIRokok();

    @GET("keluarga")
    Call<ResponseDaftarKeluarga> getDaftarKeluarga();

    @GET("kelurahan-by-kecamatan/{id_kec}")
    Call<ResponseKelurahan> getDaftarKeluByKeca(@Path("id_kec") String id_kec);

    @FormUrlEncoded
    @POST("keluarga")
    Call <PostDataKeluarga> createKeluarga(@Field("no_kk")String noKk,
                                           @Field("lat") String lat,
                                           @Field("lon") String lon,
                                           @Field("nama_kk") String namaKK,
                                           @Field("alamat")String alamat,
                                           @Field("id_kelurahan") String idKelurahan,
                                           @Field("n_anggota") String nAnggota
    );

    @GET("keluarga/{no_kk}")
    Call<ResponseDetilKeluarga> getDetilKeluarga(@Path("no_kk") String no_kk);

    @FormUrlEncoded
    @POST("individu")
    Call<PostDataIndividu> createIndividu (
            @Field("no_kk") String nokk,
            @Field("nik") String nik,
            @Field("id_relasi") int idrelasi,
            @Field("nama") String nama,
            @Field("tgl_lahir") String tgllahir,
            @Field("gender") String gender,
            @Field("id_agama") int idagama,
            @Field("id_pendidikan") int idpendidikan,
            @Field("id_jkn") int idjkn,
            @Field("id_status_nikah") int idnikah,
            @Field("id_pekerjaan") int idpekerjaan,
            @Field("no_hp") String no_hp
    );

    @GET("pengguna/{id_pengguna}")
    Call<DataPengguna> getPengguna (@Path("id_pengguna") int idPengguna);

    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken);

    @FormUrlEncoded
    @POST("jumantik")
    Call<PostDataJumantik> createJumantik (@Field("lat") String lat,
                                           @Field("lon") String lon,
                                           @Field("alamat")String alamat,
                                           @Field("rt") String rt,
                                           @Field("rw") String rw,
                                           @Field("id_kelurahan") String idKelurahan,
                                           @Field("rumah_umum") String rumahUmum,
                                           @Field("in_outdoor") String inOutdoor,
                                           @Field("n_wadah") String nWadah,
                                           @Field("ada_jentik") String adaJentik,
                                           @Field("tgl_visit") String tglVisit
    );

    @Multipart
    @POST("foto")
    Call<ResponseFoto> uploadPhoto (@Part("id_kunjungan") RequestBody idKunjungan,
                                    @Part MultipartBody.Part file);

    @GET("rumah")
    Call<ResponseDaftarRumah> getDaftarRumah();

    @FormUrlEncoded
    @POST("rumah")
    Call<PostResponse> createRumah (@Field("lat") String lat,
                                    @Field("lon") String lon,
                                    @Field("nama_pemilik") String nama_pemilik,
                                    @Field("alamat") String alamat,
                                    @Field("rt") String rt,
                                    @Field("rw") String rw,
                                    @Field("id_kelurahan") String idKelurahan
    );

    // dipanggil di HomepageJumantik
    @GET("kunjungan-pengguna/{id_pengguna}")
    Call<ResponseKunjunganPengguna> getKunjunganPengguna(@Path("id_pengguna") int idPengguna);

    @GET("kunjungan-periode")
    Call<ResponseDaftarKunjungan> getDaftarPeriode();

    // dipanggil di HomepageKunjungan
    @GET("kunjungan-ringkasan/{kunjungan-periode}/{id_pengisi}")
    Call<ResponseRingkasanKunjungan> getRingkasanKunjungan(@Path("kunjungan-periode") String kunjungan_periode, @Path("id_pengisi") int id_pengisi);

    @GET("rumah-kunjungan/{kunjungan-periode}/{id_pengisi}")
    Call<ResponseDaftarRumah> getDaftarRumahKunjungan(@Path("kunjungan-periode") String kunjungan_periode, @Path("id_pengisi") int id_pengisi);

    @GET("kunjungan-lap-kader/{kunjungan-periode}/{id_pengisi}")
    Call<ResponseLapKader>getKunjunganLapKader(@Path("kunjungan-periode") String kunjungan_periode, @Path("id_pengisi")int id_pengisi);

    @GET("kunjungan-lap-koor/{kunjungan-periode}/{id_pengisi}")
    Call<ResponseLapKoor>getKunjunganLapKoor(@Path("kunjungan-periode") String kunjungan_periode, @Path("id_pengisi")int id_pengisi);

    // dipanggil di TatananPeriksa
    @GET("kunjungan-foto/{id_kunjungan}")
    Call<ResponseDetilKunjungan> getDetilKunjungan(@Path("id_kunjungan") String id_kunjungan);
     //manggil summary per jenis_rumah
    // manggil daftar rt
    //@GET("")
    //Call<>getDaftarRt();
    // dipanggil di TambahTatananPeriksa
    @GET("jenis-rumah")
    Call<ResponseJenisRumah> getDaftarJenisRumah();
    @GET("kota")
    //Call<ResponseKota>getKota();

    @FormUrlEncoded
    @POST("kunjungan")
    Call<PostKunjungan> createTatananPeriksa (
            @Field("tgl_kunjungan") String tgl_kunjungan,
            @Field("n_wadah") String n_wadah,
            @Field("n_wadah_jentik") String n_wadah_jentik,
            @Field("id_rumah") int id_rumah,
            @Field("id_jenis_rumah") int id_jenis_rumah,
            @Field("id_pengguna") int id_pengguna
    );

    @Multipart
    @POST("fotodouble")
    Call<ResponseFoto> uploadDuaPhoto (@Part("id_kunjungan") RequestBody idKunjungan,
                                        @Part MultipartBody.Part filepantau,
                                        @Part MultipartBody.Part fileberantas);



}
