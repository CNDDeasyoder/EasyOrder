package kesu.easyorder;

/**
 * Created by thinhle on 9/22/17.
 */

public class MonAn {
    private int ban;
    private int gia;
    private int sl;
    private int state;
    private int stt;
    private String ten;
    private Rate rate;
    private String id;

    // constructors

    public MonAn() {
    }

    public MonAn(int ban, int gia, int sl, int state, int stt, String ten, String id) {
        this.ban = ban;
        this.gia = gia;
        this.sl = sl;
        this.state = state;
        this.stt = stt;
        this.ten = ten;
        this.id = id;
    }

    public MonAn(int ban, int gia, int sl, int state, int stt, String ten, Rate rate, String id) {
        this.ban = ban;
        this.gia = gia;
        this.sl = sl;
        this.state = state;
        this.stt = stt;
        this.ten = ten;
        this.rate = rate;
        this.id = id;
    }

    public int getBan() {
        return ban;
    }

    public void setBan(int ban) {
        this.ban = ban;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
