package kesu.easyorder;

import java.util.ArrayList;

/**
 * Created by thinhle on 10/26/17.
 */

public class KhachHang {
    private String tenKhachHang;
    private ArrayList<ThongTinMonAn> danhSachMonAn = new ArrayList<>();

    public KhachHang() {
    }

    public KhachHang(String tenKhachHang, ArrayList<ThongTinMonAn> danhSachMonAn) {
        this.tenKhachHang = tenKhachHang;
        this.danhSachMonAn = danhSachMonAn;
    }

    public KhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public ArrayList<ThongTinMonAn> getDanhSachMonAn() {
        return danhSachMonAn;
    }

    public void setDanhSachMonAn(ArrayList<ThongTinMonAn> danhSachMonAn) {
        this.danhSachMonAn = danhSachMonAn;
    }
}
