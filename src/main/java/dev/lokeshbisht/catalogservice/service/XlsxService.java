package dev.lokeshbisht.catalogservice.service;

import dev.lokeshbisht.catalogservice.dto.brand.BrandSheetDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface XlsxService {

    List<BrandSheetDto> getBrands(MultipartFile file);
}
