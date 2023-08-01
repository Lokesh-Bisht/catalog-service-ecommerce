package dev.lokeshbisht.catalogservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lokeshbisht.catalogservice.dto.brand.BrandSheetDto;
import dev.lokeshbisht.catalogservice.exceptions.FileUploadException;
import dev.lokeshbisht.catalogservice.service.XlsxService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XlsxServiceImpl implements XlsxService {

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(XlsxServiceImpl.class);

    @Override
    public List<BrandSheetDto> getBrands(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            logger.info("Reading from sheet: {}", sheet.getSheetName());
            return getBrandsFromSheet(sheet);
        } catch (IOException e) {
            throw new FileUploadException("Failed to parse xlsx file: " + e.getMessage());
        }
    }

    private List<BrandSheetDto> getBrandsFromSheet(Sheet sheet) {
        List<BrandSheetDto> brandSheetDtoList = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        Map<String, String> data = new HashMap<>();
        int maxColumns = 0;
        for (Row row : sheet) {
            // header
            if (row.getRowNum() == 0) {
                maxColumns = row.getLastCellNum();
                for (int i = 0; i < maxColumns; ++i) {
                    if (StringUtils.isEmpty(String.valueOf(row.getCell(i)))) {
                        maxColumns = i;
                        break;
                    }
                    headers.add(String.valueOf(row.getCell(i)).toLowerCase());
                }
            } else {
                if ((row.getCell(0) == null && row.getCell(1) == null) ||
                    ("".equals(String.valueOf(row.getCell(0))) && "".equals(String.valueOf(row.getCell(1))))
                ){
                    break;
                }
                for (int i = 0; i < maxColumns; ++i) {
                    data.put(headers.get(i), String.valueOf(row.getCell(i)));
                }
                BrandSheetDto brandSheetDto = objectMapper.convertValue(data, BrandSheetDto.class);
                brandSheetDtoList.add(brandSheetDto);
            }
        }
        return brandSheetDtoList;
    }
}
