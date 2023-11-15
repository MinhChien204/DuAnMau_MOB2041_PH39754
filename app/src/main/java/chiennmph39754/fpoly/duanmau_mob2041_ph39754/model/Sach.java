package chiennmph39754.fpoly.duanmau_mob2041_ph39754.model;

public class Sach {
    private int masach;
    private String tensach;
    private int giathue;
    private int maloai;
private int kinhdoanh;
private int soluong;

    public Sach() {
    }

    public Sach(int masach, String tensach, int giathue, int maloai, int kinhdoanh, int soluong) {
        this.masach = masach;
        this.tensach = tensach;
        this.giathue = giathue;
        this.maloai = maloai;
        this.kinhdoanh = kinhdoanh;
        this.soluong = soluong;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getGiathue() {
        return giathue;
    }

    public void setGiathue(int giathue) {
        this.giathue = giathue;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public int getKinhdoanh() {
        return kinhdoanh;
    }

    public void setKinhdoanh(int kinhdoanh) {
        this.kinhdoanh = kinhdoanh;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}