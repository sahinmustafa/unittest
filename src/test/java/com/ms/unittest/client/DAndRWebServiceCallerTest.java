package com.ms.unittest.client;

import com.ms.unittest.model.kitap.Kitap;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

@EnabledIfSystemProperty(named = "test.level", matches = "full")
class DAndRWebServiceCallerTest {
    private final String KITAP_ADI = "Kitap";

    private MockRestServiceServer mockRestServiceServer;

    @BeforeEach
    void setUp(TestInfo info) {
        System.out.println("Çalıştırılan test : " + info.getTestMethod());
        mockRestServiceServer = MockRestServiceServer.createServer(DAndRWebServiceCaller.restTemplate);
    }

    @Test
    void restCagirisiSonucuKarsiSistemdenKitabinTutariGelmeli() {
        mockRestServiceServer
                .expect(MockRestRequestMatchers.requestTo((DAndRWebServiceCaller.URI + "/" + KITAP_ADI)))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("10"));

        double fiyat = DAndRWebServiceCaller.kitabinFiyatiniSorgula(new Kitap(KITAP_ADI));
        Assertions.assertEquals(fiyat, 10);
    }
}
