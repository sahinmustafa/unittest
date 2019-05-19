package com.ms.unittest.model.kitaplik;

import com.ms.unittest.model.kitap.Kitap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

@EnabledIfSystemProperty(named = "test.level", matches = "unit")
 class KitaplikTest {

    private final Kitap KITAP_1 = new Kitap("Kitap 1");
    private final Kitap KITAP_2 = new Kitap("Kitap 2");
    private Kitaplik kitaplik;

    @BeforeEach
     void setUp(TestInfo info) throws Exception {
       System.out.println("Çalıştırılan test : " + info.getTestMethod());
        kitaplik = new Kitaplik("Kitaplık 1");
    }

    @Test()
     void kitapligaBosKitapEklendigindeHataVermeli() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> kitaplik.kitapligaEkle(null));
    }

    @Test
     void kitapligaKitapEklenmeli() {
        kitaplik.kitapligaEkle(KITAP_1);
        kitaplik.kitapligaEkle(KITAP_2);
        Assertions.assertEquals(kitaplik.kitapliktakiKitapSayisi(), 2);
    }

    @Test
     void ayniKitaptanKitapligaEklendigindeHataVermeli() {
        kitaplik.kitapligaEkle(KITAP_1);
        kitaplik.kitapligaEkle(KITAP_2);
        Assertions.assertThrows(KitapliktaMevcutException.class, () -> kitaplik.kitapligaEkle(KITAP_1));
    }

    @Test
     void bosKitapCikarilmayaCalisildigindaHataVermeli() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> kitaplik.kitapliktanCikar(null));
    }

    @Test
     void kitapliktaOlmayanBirKitapCikarilmayaCalisildigindaHataVermeli() {
        kitaplik.kitapligaEkle(KITAP_1);
        Assertions.assertThrows(KitapMevcutDegilException.class, ()-> kitaplik.kitapliktanCikar(KITAP_2));
    }

    @Test
     void kitapliktaOlanMevcutKitapCikarilabilmeli() {
        kitaplik.kitapligaEkle(KITAP_1);
        kitaplik.kitapligaEkle(KITAP_2);
        kitaplik.kitapliktanCikar(KITAP_1);

        Assertions.assertEquals(kitaplik.kitapliktakiKitapSayisi(), 1);
    }

    @Test
     void kitapliktaKitapVarsaTrueCevabiVermeli() {
        kitaplik.kitapligaEkle(KITAP_1);
        kitaplik.kitapligaEkle(KITAP_2);

        Assertions.assertTrue(kitaplik.kitapliktaVarMi(KITAP_2));
    }
    @Test
     void kitapliktaKitapVarsaFalseCevabiVermeli() {
        kitaplik.kitapligaEkle(KITAP_1);

        Assertions.assertFalse(kitaplik.kitapliktaVarMi(KITAP_2));
    }

    @Test
     void kitapliktaKitapVarsaAdindanKitapGetirlebilmeli() {
        kitaplik.kitapligaEkle(KITAP_1);
        kitaplik.kitapligaEkle(KITAP_2);

        Kitap kitapliktakiKitap = kitaplik.kitapliktanKitapGetir("Kitap 1");
        Assertions.assertEquals(kitapliktakiKitap, KITAP_1);
    }

    @Test
     void kitapliktaKitapAdindanBulunamazsaHataVermeli() {
        kitaplik.kitapligaEkle(KITAP_1);
        kitaplik.kitapligaEkle(KITAP_2);
        Assertions.assertThrows(KitapMevcutDegilException.class, () ->kitaplik.kitapliktanKitapGetir("Kitap 3"));
    }
}