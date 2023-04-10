package com.kytokvinily.vinyls.service;

import com.kytokvinily.vinyls.entity.Vinyl;
import com.kytokvinily.vinyls.exception.VinylAlreadyExistsException;
import com.kytokvinily.vinyls.exception.VinylNotFoundException;
import com.kytokvinily.vinyls.repository.VinylRepository;
import com.kytokvinily.vinyls.entity.keys.VinylId;
import org.springframework.stereotype.Service;

@Service
public class VinylService {
    private final VinylRepository vinylRepository;

    public VinylService(VinylRepository vinylRepository) {
        this.vinylRepository = vinylRepository;
    }

    public Iterable<Vinyl> viewVinyls() {
        return vinylRepository.findAll();
    }

    public Vinyl getByTitleAndYear(String title, int year) {
        VinylId vinylId = new VinylId(title, year);
        return vinylRepository.findByVinylId(vinylId).orElseThrow(
                () -> new VinylNotFoundException(vinylId));
    }

    public Vinyl addVinyl(Vinyl vinyl) {
        if (vinylRepository.existsByVinylId(vinyl.getVinylId())) {
            throw new VinylAlreadyExistsException(vinyl.getVinylId());
        }
        return vinylRepository.save(vinyl);
    }

    public Vinyl editVinylDetails(String title, int year, Vinyl vinyl) {
        return vinylRepository.findByVinylId(new VinylId(title, year))
                .map(existingVinyl -> {
                    var vinylToUpdate = new Vinyl(
                            existingVinyl.getVinylId(),
                            vinyl.getAuthor());
                    return vinylRepository.save(vinylToUpdate);
                })
                .orElseGet(() -> addVinyl(vinyl));
    }

    public void removeVinyl(String title, int year) {
        vinylRepository.deleteByVinylId(new VinylId(title, year));
    }
}
