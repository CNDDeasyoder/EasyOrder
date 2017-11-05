package kesu.easyorder;

/**
 * Created by thinhle on 10/28/17.
 */

//lớp chứa thông tin bàn ăn

public class BanAn {
    private Boolean state;
    private KhachHang khachHang;
    private int banSo;

    public BanAn() {
    }
    public BanAn(int banSo, Boolean state){
        this.banSo=banSo;
        this.state=state;
    }

    public BanAn(int banSo, Boolean state, KhachHang khachHang) {
        this.state = state;
        this.khachHang = khachHang;
        this.banSo = banSo;
    }

    public int getBanSo() {
        return banSo;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
}
