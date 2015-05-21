package id.co.technomotion.booklogger;

/**
 * Created by Adi Prasetyo on 5/21/2015.
 */
public class Buku {

    private String judulBuku, namaPengarang, jumlahHalaman;

    public Buku(String judulBuku, String namaPengarang, String jumlahHalaman){
        this.judulBuku = judulBuku;
        this.namaPengarang = namaPengarang;
        this.jumlahHalaman = jumlahHalaman;
    }

    public String getJudulBuku(){
        return judulBuku;
    }

    public String getNamaPengarang(){
        return namaPengarang;
    }

    public String getJumlahHalaman(){
        return jumlahHalaman;
    }
}
