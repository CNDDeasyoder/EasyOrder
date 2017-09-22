package kesu.easyorder;

/**
 * Created by thinhle on 9/22/17.
 */

public class DrawerItem {
    private String ten;
    private int img;

    // constructor
    public DrawerItem(String ten, int img) {
        this.ten = ten;
        this.img = img;

    }

    // Getters and Setters
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

}
