package kesu.easyorder;

import java.util.ArrayList;

/**
 * Created by thinhle on 10/26/17.
 */

public class KhachHang {
    private String tenKhachHang;
    private int banSo;
    private ArrayList<MonAn> danhSachMonAn = new ArrayList<>();

    public KhachHang() {
    }

    public KhachHang(String tenKhachHang, int banSo, ArrayList<MonAn> danhSachMonAn) {
        this.tenKhachHang = tenKhachHang;
        this.banSo = banSo;
        this.danhSachMonAn = danhSachMonAn;
    }

    public KhachHang(String tenKhachHang, int banSo) {
        this.tenKhachHang = tenKhachHang;
        this.banSo = banSo;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getBanSo() {
        return banSo;
    }

    public void setBanSo(int banSo) {
        this.banSo = banSo;
    }

    public ArrayList<MonAn> getDanhSachMonAn() {
        return danhSachMonAn;
    }

    public void setDanhSachMonAn(ArrayList<MonAn> danhSachMonAn) {
        this.danhSachMonAn = danhSachMonAn;
    }
}
