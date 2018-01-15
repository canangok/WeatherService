package com.example.canan.havadurumu;

import android.graphics.Bitmap;

/**
 * Created by canan on 14.01.2018.
 */

public class Weather {
    String weather,sehir,aciklama;
    int sicaklik;

    Bitmap image;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String hava) {
        this.weather = weather;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public int getSicaklik() {
        return sicaklik;
    }

    public void setSicaklik(int sicaklik) {
        this.sicaklik = sicaklik;
    }



}
