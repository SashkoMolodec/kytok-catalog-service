package com.kytokvinily.catalog.domain;

import com.kytokvinily.catalog.web.VinylDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface VinylMapper {

    VinylDto toVinylDto(Vinyl vinyl);

    Iterable<VinylDto> allToVinylDto(Iterable<Vinyl> vinyls);
}
