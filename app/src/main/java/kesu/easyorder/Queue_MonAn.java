package kesu.easyorder;

/**
 * Created by Tonqt Thonq on 11/16/2017.
 */

public class Queue_MonAn {
    int ban, sl, gia;
    String ten;

    public Queue_MonAn(int id, int sl, int gia, String ten) {
        this.ban = id;
        this.sl = sl;
        this.gia = gia;
        this.ten = ten;
    }

    public int getBan() {
        return ban;
    }

    public void setBan(int id) {
        this.ban = id;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
