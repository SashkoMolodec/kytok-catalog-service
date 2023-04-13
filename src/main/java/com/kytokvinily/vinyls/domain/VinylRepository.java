package com.kytokvinily.vinyls.domain;

import com.kytokvinily.vinyls.domain.Vinyl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface VinylRepository extends CrudRepository<Vinyl, Long> {

    Optional<Vinyl> findByTitleAndYear(String title, int year);

    boolean existsByTitleAndYear(String title, int year);

    @Transactional
    void deleteByTitleAndYear(String title, int year);
}

