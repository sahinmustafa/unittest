package com.ms.unittest.model.kitaplik;

import com.ms.unittest.model.kitap.Kitap;

import java.util.ArrayList;
import java.util.List;

public class KitaplikBuilder {

    private String kitaplikAdi;
    private List<Kitap> kitaplar = new ArrayList<>();

    public KitaplikBuilder(String kitaplikAdi) {
        this.kitaplikAdi = kitaplikAdi;
    }

    public KitaplikBuilder kitapEkle(Kitap kitap) {
        this.kitaplar.add(kitap);
        return this;
    }

    public Kitaplik build(){
        Kitaplik kitaplik = new Kitaplik(kitaplikAdi);
        this.kitaplar.forEach(kitaplik::kitapligaEkle);
        return kitaplik;
    }
}
