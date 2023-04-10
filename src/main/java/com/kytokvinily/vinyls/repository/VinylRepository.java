package com.kytokvinily.vinyls.repository;

import com.kytokvinily.vinyls.entity.Vinyl;
import com.kytokvinily.vinyls.entity.keys.VinylId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VinylRepository extends CrudRepository<Vinyl, VinylId> {

    Optional<Vinyl> findByVinylId(VinylId vinylId);

    boolean existsByVinylId(VinylId vinylId);

    void deleteByVinylId(VinylId vinylId);
}

