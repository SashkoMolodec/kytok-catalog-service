package com.kytokvinily.vinyls.service;

import com.kytokvinily.vinyls.dto.VinylDto;
import com.kytokvinily.vinyls.entity.Vinyl;
import com.kytokvinily.vinyls.exception.VinylAlreadyExistsException;
import com.kytokvinily.vinyls.exception.VinylNotFoundException;
import com.kytokvinily.vinyls.mapper.VinylMapper;
import com.kytokvinily.vinyls.repository.VinylRepository;
import com.kytokvinily.vinyls.entity.keys.VinylId;
import org.springframework.stereotype.Service;

@Service
public class VinylService {
    private final VinylRepository vinylRepository;
    private final VinylMapper vinylMapper;

    public VinylService(VinylRepository vinylRepository, VinylMapper vinylMapper) {
        this.vinylRepository = vinylRepository;
        this.vinylMapper = vinylMapper;
    }

    public Iterable<VinylDto> viewVinyls() {
        Iterable<Vinyl> all = vinylRepository.findAll();
        return vinylMapper.allToVinylDto(all);
    }

    public VinylDto getByTitleAndYear(String title, int year) {
        VinylId vinylId = new VinylId(title, year);
        Vinyl vinyl = vinylRepository.findByVinylId(vinylId).orElseThrow(
                () -> new VinylNotFoundException(vinylId));
        return vinylMapper.toVinylDto(vinyl);
    }

    public VinylDto addVinyl(VinylDto vinylDto) {
        VinylId vinylId = new VinylId(vinylDto.getTitle(), vinylDto.getYear());
        if (vinylRepository.existsByVinylId(vinylId)) {
            throw new VinylAlreadyExistsException(vinylId);
        }
        Vinyl newVinyl = new Vinyl(vinylId, vinylDto.getAuthor());
        Vinyl saved = vinylRepository.save(newVinyl);
        return vinylMapper.toVinylDto(saved);
    }

    public VinylDto editVinylDetails(String title, int year, VinylDto vinylDto) {
        return vinylRepository.findByVinylId(new VinylId(title, year))
                .map(existingVinyl -> {
                    var vinylToUpdate = new Vinyl(
                            existingVinyl.getVinylId(),
                            vinylDto.getAuthor());
                    Vinyl saved = vinylRepository.save(vinylToUpdate);
                    return vinylMapper.toVinylDto(saved);
                }).orElseGet(() -> addVinyl(vinylDto));
    }

    public void removeVinyl(String title, int year) {
        vinylRepository.deleteByVinylId(new VinylId(title, year));
    }
}
