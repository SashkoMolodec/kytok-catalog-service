package com.kytokvinily.vinyls.domain;

import com.kytokvinily.vinyls.web.VinylDto;
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
        Vinyl vinyl = vinylRepository.findByTitleAndYear(title, year).orElseThrow(
                () -> new VinylNotFoundException(title, year));
        return vinylMapper.toVinylDto(vinyl);
    }

    public VinylDto addVinyl(VinylDto vinylDto) {
        if (vinylRepository.existsByTitleAndYear(vinylDto.getTitle(), vinylDto.getYear())) {
            throw new VinylAlreadyExistsException(vinylDto.getAuthor(), vinylDto.getYear());
        }
        Vinyl newVinyl = Vinyl.of(vinylDto.getTitle(), vinylDto.getAuthor(), vinylDto.getYear());
        Vinyl saved = vinylRepository.save(newVinyl);
        return vinylMapper.toVinylDto(saved);
    }

    public VinylDto editVinylDetails(Long id, VinylDto vinylDto) {
        return vinylRepository.findById(id)
                .map(existingVinyl -> {
                    var vinylToUpdate = Vinyl.of(
                            existingVinyl.id(),
                            vinylDto.getTitle(),
                            vinylDto.getAuthor(),
                            vinylDto.getYear()
                    );
                    Vinyl saved = vinylRepository.save(vinylToUpdate);
                    return vinylMapper.toVinylDto(saved);
                }).orElseGet(() -> addVinyl(vinylDto));
    }

    public void removeVinyl(Long id) {
        vinylRepository.deleteById(id);
    }

    public VinylDto getById(Long id) {
        return vinylMapper.toVinylDto(
                vinylRepository.findById(id)
                        .orElseThrow(() -> new VinylNotFoundException(id)));
    }
}
