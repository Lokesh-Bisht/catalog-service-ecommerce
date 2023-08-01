package dev.lokeshbisht.catalogservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface BulkService {

    void bulkCreateAndUpdateBrands(MultipartFile multipartFile);
}
