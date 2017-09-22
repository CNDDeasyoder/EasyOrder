package kesu.easyorder;

/**
 * Created by thinhle on 9/22/17.
 */

public class MonAn {

    private String ten;
    private int soLuong;
    private int donGia;

    // constructors

    public MonAn(String ten, int soLuong) {
        this.ten = ten;
        this.soLuong = soLuong;
    }
    public MonAn(String ten, int soLuong, int donGia) {
        this.ten = ten;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // Getters and Setters

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }


    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }


    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
