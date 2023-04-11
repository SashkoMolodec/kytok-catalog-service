package com.kytokvinily.vinyls.web;

import com.kytokvinily.vinyls.dto.VinylDto;
import com.kytokvinily.vinyls.service.VinylService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("vinyls")
public class VinylController {

    private final VinylService vinylService;

    public VinylController(VinylService vinylService) {
        this.vinylService = vinylService;
    }

    @GetMapping
    public Iterable<VinylDto> get() {
        return vinylService.viewVinyls();
    }

    @GetMapping("{title}/{year}")
    public VinylDto getByTitleAndYear(@PathVariable String title, @PathVariable int year) {
        return vinylService.getByTitleAndYear(title, year);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VinylDto post(@Valid @RequestBody VinylDto vinylDto) {
        return vinylService.addVinyl(vinylDto);
    }

    @DeleteMapping("{title}/{year}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String title, @PathVariable int year) {
        vinylService.removeVinyl(title, year);
    }

    @PutMapping("{title}/{year}")
    public VinylDto put(@PathVariable String title, @PathVariable int year, @Valid @RequestBody VinylDto vinylDto) {
        return vinylService.editVinylDetails(title, year, vinylDto);
    }

}
