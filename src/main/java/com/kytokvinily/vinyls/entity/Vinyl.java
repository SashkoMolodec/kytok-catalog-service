package com.kytokvinily.vinyls.entity;

import com.kytokvinily.vinyls.entity.keys.VinylId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Vinyl {
    @EmbeddedId
    VinylId vinylId;

    private String author;

}
