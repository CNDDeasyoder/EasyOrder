package kesu.easyorder;

/**
 * Created by thinhle on 11/22/17.
 */

public class Rate {
    private int soNguoi;
    private Float soSao;

    public Rate() {
    }

    public Rate(int soNguoi, Float soSao) {
        this.soNguoi = soNguoi;
        this.soSao = soSao;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public Float getSoSao() {
        return soSao;
    }

    public void setSoSao(Float soSao) {
        this.soSao = soSao;
    }
}
