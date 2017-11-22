package kesu.easyorder;

import java.util.ArrayList;

/**
 * Created by thinhle on 10/26/17.
 */

public class KhachHang {
    private String tenKhachHang;
    private ArrayList<MonAn> danhSachMonAn = new ArrayList<>();

    public KhachHang() {
    }

    public KhachHang(String tenKhachHang, ArrayList<MonAn> danhSachMonAn) {
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

    public ArrayList<MonAn> getDanhSachMonAn() {
        return danhSachMonAn;
    }

    public void setDanhSachMonAn(ArrayList<MonAn> danhSachMonAn) {
        this.danhSachMonAn = danhSachMonAn;
    }
}
