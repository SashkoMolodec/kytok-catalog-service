package com.kytokvinily.vinyls.demo;

import com.kytokvinily.vinyls.domain.Vinyl;
import com.kytokvinily.vinyls.domain.VinylRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "vinyl.testdata.enabled", havingValue = "true")
public class VinylDataLoader {

    private final VinylRepository vinylRepository;

    public VinylDataLoader(VinylRepository vinylRepository) {
        this.vinylRepository = vinylRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadVinylTestData() {
        var vin1 = Vinyl.of("title2", "author2", 1989);
        var vin2 = Vinyl.of("title3", "author3", 1990);
        vinylRepository.save(vin1);
        vinylRepository.save(vin2);
    }

}
