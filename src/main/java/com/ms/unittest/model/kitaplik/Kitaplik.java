package com.ms.unittest.model.kitaplik;

import com.ms.unittest.model.kitap.Kitap;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Kitaplik {

    private final String kitaplikAdi;
    private Set<Kitap> kitaplar = new HashSet<>();

    public Kitaplik(String kitaplikAdi) {
        this.kitaplikAdi = kitaplikAdi;
    }


    public void kitapligaEkle(Kitap kitap){
        if (kitap == null) throw new IllegalArgumentException();
        if (kitapliktaVarMi(kitap)) throw new KitapliktaMevcutException();
        kitaplar.add(kitap);
    }

    public void kitapliktanCikar(Kitap kitap) {
        if (kitap == null) throw new IllegalArgumentException();
        if (!kitapliktaVarMi(kitap)) throw new KitapMevcutDegilException();
        kitaplar.remove(kitap);
     }

     public int kitapliktakiKitapSayisi(){
        return kitaplar.size();
     }

    public boolean kitapliktaVarMi(Kitap kitap) {
        return kitaplar.contains(kitap);
    }

    public Kitap kitapliktanKitapGetir(String kitapAdi) {
        return kitaplar
                    .stream()
                    .filter(kitap -> kitap.getAdi().equals(kitapAdi))
                    .findFirst()
                    .orElseThrow(() -> new KitapMevcutDegilException());
    }

    public Set<Kitap> kitaplar() {
        return Collections.unmodifiableSet(kitaplar);
    }
}
