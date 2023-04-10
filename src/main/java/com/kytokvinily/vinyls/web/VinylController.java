package com.kytokvinily.vinyls.web;

import com.kytokvinily.vinyls.entity.Vinyl;
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
    public Iterable<Vinyl> get() {
        return vinylService.viewVinyls();
    }

    @GetMapping("{title}/{year}")
    public Vinyl getByTitleAndYear(@PathVariable String title, @PathVariable int year) {
        return vinylService.getByTitleAndYear(title, year);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vinyl post(@Valid @RequestBody Vinyl vinyl) {
        return vinylService.addVinyl(vinyl);
    }

    @DeleteMapping("{title}/{year}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String title, @PathVariable int year) {
        vinylService.removeVinyl(title, year);
    }

    @PutMapping("{title}/{year}")
    public Vinyl put(@PathVariable String title, @PathVariable int year, @Valid @RequestBody Vinyl vinyl) {
        return vinylService.editVinylDetails(title, year, vinyl);
    }

}
