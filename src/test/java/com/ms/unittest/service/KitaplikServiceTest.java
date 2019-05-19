package com.ms.unittest.service;
import static org.mockito.Mockito.*;

import com.ms.unittest.model.kitap.Kitap;
import com.ms.unittest.model.kitaplik.KitaplikBuilder;
import com.ms.unittest.model.kitaplik.KitaplikRepository;
import com.ms.unittest.model.kitaplik.KitaplikYokException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

@EnabledIfSystemProperty(named = "test.level", matches = "unit")
class KitaplikServiceTest {

    private final String OLAMAYAN_KITAPLIK = "OLMAYAN_KITAPLIK";
    private final String KAYITLI_KITAPLIK = "KAYITLI_KITAPLIK";


    @Mock
    private KitaplikRepository kitaplikRepository;

    private KitaplikService kitaplikService;

    @BeforeEach
    void setUp(TestInfo info) {
        System.out.println("Çalıştırılan test : " + info.getTestMethod());
        MockitoAnnotations.initMocks(this);
        kitaplikService = new TestableKitaplikService(kitaplikRepository);
        when(kitaplikRepository.findByKitaplikAdi(OLAMAYAN_KITAPLIK)).thenReturn(Optional.empty());
        when(kitaplikRepository.findByKitaplikAdi(KAYITLI_KITAPLIK))
                .thenReturn(Optional.of(
                        new KitaplikBuilder(KAYITLI_KITAPLIK)
                                .kitapEkle(new Kitap("Kitap 1"))
                                .kitapEkle(new Kitap("Kitap 2"))
                                .kitapEkle(new Kitap("Kitap 3"))
                                .build()));

    }

    @Test
    void kitaplikAdiBosOldugundaHataVermeli() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> kitaplikService.kitapliktakiKitaplarinToplamFiyatiniGetir(null));
    }

    @Test
    void mevcuttaOlmayanBirKitaplikAdiIleSorgulandigindaHataVermeli() {
        Assertions.assertThrows(KitaplikYokException.class, () -> kitaplikService.kitapliktakiKitaplarinToplamFiyatiniGetir(OLAMAYAN_KITAPLIK));
    }

    @Test
    void kitapliktakiButunKitaplarinToplamFiyatiniDandRdanHesaplamali() {
        double toplamTutar = kitaplikService.kitapliktakiKitaplarinToplamFiyatiniGetir(KAYITLI_KITAPLIK);
        Assertions.assertEquals(toplamTutar, 30D);
    }

    private class TestableKitaplikService extends KitaplikService {

        private TestableKitaplikService(KitaplikRepository kitaplikRepository) {
            super(kitaplikRepository);
        }

        @Override
        protected Double webServiceUzerindenKitabinFiyatiniSorgula(Kitap kitap) {
            return 10D;
        }
    }
}
