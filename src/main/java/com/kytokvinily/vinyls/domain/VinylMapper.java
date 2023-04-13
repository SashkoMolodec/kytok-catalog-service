package com.kytokvinily.vinyls.domain;

import com.kytokvinily.vinyls.web.VinylDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface VinylMapper {

    VinylDto toVinylDto(Vinyl vinyl);

    Iterable<VinylDto> allToVinylDto(Iterable<Vinyl> vinyls);
}
