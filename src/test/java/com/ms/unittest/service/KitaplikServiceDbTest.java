package com.ms.unittest.service;

import com.ms.unittest.model.kitaplik.Kitaplik;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


//@ExtendWith(SpringExtension.class)
//@SpringBootTest
@EnabledIfSystemProperty(named = "test.level", matches = "full")
public class KitaplikServiceDbTest {

    //@Autowired
    private KitaplikService kitaplikService;

    @BeforeEach
    void setUp(TestInfo info) {
        System.out.println("Çalıştırılan test : " + info.getTestMethod());
        kitaplikService.kitaplikKaydet(new Kitaplik("Kitap"));
    }

    @Test
    public void kitaplikaBulunanButunKitaplarinToplamTutariniGetir() {
        Kitaplik kitaplik = kitaplikService.kitaplikAdindanGetir("Kitap");
    }
}
