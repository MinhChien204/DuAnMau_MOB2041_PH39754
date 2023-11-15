package chiennmph39754.fpoly.duanmau_mob2041_ph39754.model;

public class ThanhVien {
    public int matv;
    public String hoten;
    public String namsinh;
    public ThanhVien() {
    }

    public ThanhVien(int matv, String hoten, String namsinh) {
        this.matv = matv;
        this.hoten = hoten;
        this.namsinh = namsinh;
    }


    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }
}
