package com.kytokvinily.vinyls.mapper;

import com.kytokvinily.vinyls.dto.VinylDto;
import com.kytokvinily.vinyls.entity.Vinyl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface VinylMapper {

    @Mapping(target = "year", source = "vinylId.year")
    @Mapping(target = "title", source = "vinylId.title")
    VinylDto toVinylDto(Vinyl vinyl);
    Iterable<VinylDto> allToVinylDto(Iterable<Vinyl> vinyls);
}
