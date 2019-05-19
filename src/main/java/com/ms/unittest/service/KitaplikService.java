package com.ms.unittest.service;

import com.ms.unittest.client.DAndRWebServiceCaller;
import com.ms.unittest.model.kitap.Kitap;
import com.ms.unittest.model.kitaplik.Kitaplik;
import com.ms.unittest.model.kitaplik.KitaplikRepository;
import com.ms.unittest.model.kitaplik.KitaplikYokException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

//@Service
public class KitaplikService {

    private final KitaplikRepository kitaplikRepository;

    //@Autowired
    public KitaplikService(KitaplikRepository kitaplikRepository) {
        this.kitaplikRepository = kitaplikRepository;
    }

    public Kitaplik kitaplikAdindanGetir(String kitaplikAdi){
        return kitaplikRepository.findByKitaplikAdi(kitaplikAdi).orElseThrow(() -> new KitaplikYokException());
    }

    public Double kitapliktakiKitaplarinToplamFiyatiniGetir(String kitaplikAdi) {
        if (StringUtils.isEmpty(kitaplikAdi)) throw new IllegalArgumentException("Kitaplık adı boş olamaz!");
        Kitaplik kitaplik = kitaplikAdindanGetir(kitaplikAdi);
        return kitaplik.kitaplar()
                .stream()
                .map(this::webServiceUzerindenKitabinFiyatiniSorgula)
                .mapToDouble(Double::doubleValue)
                .sum();

    }

    protected Double webServiceUzerindenKitabinFiyatiniSorgula(Kitap kitap) {
        return DAndRWebServiceCaller.kitabinFiyatiniSorgula(kitap);
    }

    public void kitaplikKaydet(Kitaplik kitaplik) {
        kitaplikRepository.save(kitaplik);
    }
}
