package com.ms.unittest.client;

import com.ms.unittest.model.kitap.Kitap;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


public class DAndRWebServiceCaller {

    public static RestTemplate restTemplate = new RestTemplate();
    static final String URI = "http://localhost:8080/d-and-r/kitap";


    public static Double kitabinFiyatiniSorgula(Kitap kitap) {

        return restTemplate.getForObject(URI + "/" + kitap.getAdi(), Double.class);
    }
}
