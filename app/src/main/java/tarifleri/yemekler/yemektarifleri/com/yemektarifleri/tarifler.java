package tarifleri.yemekler.yemektarifleri.com.yemektarifleri;

import java.io.Serializable;

public class tarifler implements Serializable {
    private int id;
    private String yemekad;
    private String hazirlamasuresi;
    private int kisisayisi;
    private String resim;
    private String malzeme;
    private String yemekTarifi;
    private int sure;
    public int getSure() {
        return sure;
    }

    public void setSure(int sure) {
        this.sure = sure;
    }



    public String getMalzeme() {
        return malzeme;
    }

    public void setMalzemeler(String malzeme) {
        this.malzeme = malzeme;
    }

    public String getYemekTarifi() {
        return yemekTarifi;
    }

    public void setYemekTarifi(String yemekTarifi) {
        this.yemekTarifi = yemekTarifi;
    }

    public tarifler() {
    }

    public tarifler(int id, String yemekad, String hazirlamasuresi, int kisisayisi,String resim,String malzemeler,String yemekTarifi,int sure) {
        this.id = id;
        this.yemekad = yemekad;
        this.hazirlamasuresi = hazirlamasuresi;
        this.kisisayisi = kisisayisi;
        this.resim=resim;
        this.sure=sure;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYemekad() {
        return yemekad;
    }

    public void setYemekad(String yemekad) {
        this.yemekad = yemekad;
    }

    public String getHazirlamasuresi() {
        return hazirlamasuresi;
    }

    public void setHazirlamasuresi(String hazirlamasuresi) {
        this.hazirlamasuresi = hazirlamasuresi;
    }

    public int getKisisayisi() {
        return kisisayisi;
    }

    public void setKisisayisi(int kisisayisi) {
        this.kisisayisi = kisisayisi;
    }
}
