package com.ms.unittest.model.kitaplik;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KitaplikRepository /*extends CrudRepository<Kitaplik, String>*/ {

    void save(Kitaplik kitaplik);
    Optional<Kitaplik> findByKitaplikAdi(String kitaplik);
}
