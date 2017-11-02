package kesu.easyorder;

/**
 * Created by Tonqt Thonq on 9/22/2017.
 */

public class ThongTinMonAn {
    public String id;
    public String name;
    public String mieu_ta;
    public int gia;
    public int dang_chon;


    public ThongTinMonAn() {
    }

    public ThongTinMonAn(String id, String name, int gia, int dang_chon) {
        this.id = id;
        this.name = name;
        this.gia = gia;
        this.dang_chon = dang_chon;
    }

    public ThongTinMonAn(String hinh, String ten_mon, int gia) {
        this.id = hinh;
        this.name = ten_mon;
        this.gia = gia;
        this.dang_chon=0;
    }

    public ThongTinMonAn(String ten_mon, int gia) {
        this.name = ten_mon;
        this.gia = gia;
    }

    public String getMieu_ta() {
        return mieu_ta;
    }

    public int getDang_chon() {
        return dang_chon;
    }

    public void setDang_chon(int dang_chon) {
        this.dang_chon = dang_chon;
    }

    public String getTen_mon() {
        return name;
    }

    public int getGia() {
        return gia;
    }

    public void setTen_mon(String ten_mon) {
        this.name = ten_mon;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getHinh() {
        return id;
    }

    public void setHinh(String hinh) {
        this.id = hinh;
    }
}
