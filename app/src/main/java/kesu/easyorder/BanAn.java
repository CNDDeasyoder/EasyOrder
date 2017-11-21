package kesu.easyorder;

/**
 * Created by thinhle on 10/28/17.
 */

//lớp chứa thông tin bàn ăn

public class BanAn {
    private int state;
    private KhachHang khachHang;
    private int banSo;

    public BanAn() {
    }
    public BanAn(int banSo, int state){
        this.banSo=banSo;
        this.state=state;
    }

    public BanAn(int banSo, int state, KhachHang khachHang) {
        this.state = state;
        this.khachHang = khachHang;
        this.banSo = banSo;
    }

    public int getBanSo() {
        return banSo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
}
