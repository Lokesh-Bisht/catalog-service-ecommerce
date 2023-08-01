package dev.lokeshbisht.catalogservice.service;

import dev.lokeshbisht.catalogservice.dto.brand.BrandDto;

public interface KafkaService {

    void sendBrandForCreate(BrandDto brandDto);
    void sendBrandForUpdate(BrandDto brandDto);
}
