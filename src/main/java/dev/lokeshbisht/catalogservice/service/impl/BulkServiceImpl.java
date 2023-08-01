package dev.lokeshbisht.catalogservice.service.impl;

import dev.lokeshbisht.catalogservice.dto.brand.BrandDto;
import dev.lokeshbisht.catalogservice.dto.brand.BrandSheetDto;
import dev.lokeshbisht.catalogservice.exceptions.FileUploadException;
import dev.lokeshbisht.catalogservice.mapper.BrandMapper;
import dev.lokeshbisht.catalogservice.service.BulkService;
import dev.lokeshbisht.catalogservice.service.KafkaService;
import dev.lokeshbisht.catalogservice.service.XlsxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BulkServiceImpl implements BulkService {

    @Autowired
    private XlsxService xlsxService;

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private BrandMapper brandMapper;

    private static final Logger logger = LoggerFactory.getLogger(BulkServiceImpl.class);

    private static final String TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Override
    public void bulkCreateAndUpdateBrands(MultipartFile file) {
        logger.info("START bulkCreateAndUpdateBrands");
        if (file.isEmpty()) {
            throw new FileUploadException("No file uploaded!");
        } else if (!TYPE_XLSX.equals(file.getContentType())) {
            throw new FileUploadException("Invalid file format. Only xlsx file is supported.");
        }
        List<BrandSheetDto> brandSheetDtoList = xlsxService.getBrands(file);
        sendBrandsForCreateOrUpdate(brandSheetDtoList);
    }

    private void sendBrandsForCreateOrUpdate(List<BrandSheetDto> brandSheetDtoList) {
        for (BrandSheetDto brandSheetDto : brandSheetDtoList) {
            BrandDto brandDto = brandMapper.toBrandDto(brandSheetDto);
            if (brandSheetDto.getBrandId().isEmpty()) {
                kafkaService.sendBrandForCreate(brandDto);
            } else {
                kafkaService.sendBrandForUpdate(brandDto);
            }
        }
    }
}
