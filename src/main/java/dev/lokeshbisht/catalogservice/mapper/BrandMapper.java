package dev.lokeshbisht.catalogservice.mapper;

import dev.lokeshbisht.catalogservice.dto.brand.BrandDto;
import dev.lokeshbisht.catalogservice.dto.brand.BrandSheetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    @Mapping(target = "brandId", expression = "java((int) (Double.parseDouble(brandSheetDto.getBrandId())))")
    @Mapping(target = "categoryId", expression = "java((int) (Double.parseDouble(brandSheetDto.getCategoryId())))")
    BrandDto toBrandDto(BrandSheetDto brandSheetDto);
}
