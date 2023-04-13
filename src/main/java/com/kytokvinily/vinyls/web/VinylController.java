package com.kytokvinily.vinyls.web;

import com.kytokvinily.vinyls.config.Messages;
import com.kytokvinily.vinyls.domain.VinylService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("vinyls")
public class VinylController {

    private final VinylService vinylService;
    private final Messages messages;

    public VinylController(VinylService vinylService, Messages messages) {
        this.vinylService = vinylService;
        this.messages = messages;
    }

    @GetMapping
    public Iterable<VinylDto> getAll() {
        System.out.println(messages.getGreeting());
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

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        vinylService.removeVinyl(id);
    }

    @PutMapping("{id}")
    public VinylDto put(@PathVariable Long id, @Valid @RequestBody VinylDto vinylDto) {
        return vinylService.editVinylDetails(id, vinylDto);
    }
}
