package kesu.easyorder;

/**
 * Created by Tonqt Thonq on 9/22/2017.
 */

public class ThongTinMonAn {
    public String hinh;
    public String ten_mon;
    public int gia;
    public int dang_chon;
    public static int tong_tien=0;

    public ThongTinMonAn() {
    }

    public ThongTinMonAn(String hinh, String ten_mon, int gia) {
        this.hinh = hinh;
        this.ten_mon = ten_mon;
        this.gia = gia;
        this.dang_chon=0;
    }

    public ThongTinMonAn(String ten_mon, int gia) {
        this.ten_mon = ten_mon;
        this.gia = gia;
    }

    public int getDang_chon() {
        return dang_chon;
    }

    public void setDang_chon(int dang_chon) {
        this.dang_chon = dang_chon;
    }

    public String getTen_mon() {
        return ten_mon;
    }

    public int getGia() {
        return gia;
    }

    public void setTen_mon(String ten_mon) {
        this.ten_mon = ten_mon;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
}
