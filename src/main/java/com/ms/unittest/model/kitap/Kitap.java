package com.ms.unittest.model.kitap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Kitap {

    private final String adi;
    private String yazar;
    private String yayinEvi;

    public Kitap(String adi) {
        this.adi = adi;
    }


    @Override
    public boolean equals(Object obj) {
        if ( !(obj instanceof Kitap) ) return false;

        if (getAdi() == null ) return false;

        Kitap k = (Kitap) obj;
        return getAdi().equals(k.getAdi());
    }
}
