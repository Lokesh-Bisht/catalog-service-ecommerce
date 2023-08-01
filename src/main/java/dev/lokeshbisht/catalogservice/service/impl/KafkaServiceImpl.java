package dev.lokeshbisht.catalogservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lokeshbisht.catalogservice.dto.brand.BrandDto;
import dev.lokeshbisht.catalogservice.dto.brand.BulkCreateOrUpdateDto;
import dev.lokeshbisht.catalogservice.dto.brand.BulkCreateOrUpdateMetadataDto;
import dev.lokeshbisht.catalogservice.exceptions.JsonRuntimeException;
import dev.lokeshbisht.catalogservice.service.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${kafka.topic.brand}")
    private String brandTopic;

    private static final Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);

    @Override
    public void sendBrandForCreate(BrandDto brandDto) {
        BulkCreateOrUpdateDto bulkCreateOrUpdateDto = BulkCreateOrUpdateDto.builder()
            .brandDto(brandDto)
            .operation("CREATE_BRAND")
            .build();
        pushEventToBulkCatalogTopic(bulkCreateOrUpdateDto);
    }

    @Override
    public void sendBrandForUpdate(BrandDto brandDto) {
        BulkCreateOrUpdateMetadataDto metadata = BulkCreateOrUpdateMetadataDto.builder()
            .brandId(brandDto.getBrandId())
            .build();
        BulkCreateOrUpdateDto bulkCreateOrUpdateDto = BulkCreateOrUpdateDto.builder()
            .brandDto(brandDto)
            .operation("UPDATE_BRAND")
            .bulkCreateOrUpdateMetadataDto(metadata)
            .build();
        pushEventToBulkCatalogTopic(bulkCreateOrUpdateDto);
    }

    private void pushEventToBulkCatalogTopic(BulkCreateOrUpdateDto bulkCreateOrUpdateDto) {
        logger.info("Push BulkCreateOrUpdate dto to topic: {}, data: {}", brandTopic, bulkCreateOrUpdateDto);
        try {
            String data = getObjectAsJsonString(bulkCreateOrUpdateDto);
            kafkaTemplate.send(brandTopic, data);
            logger.info("Successfully pushed BulkCreateOrUpdate dto: {} to kafka topic: {}", bulkCreateOrUpdateDto, brandTopic);
        } catch (Exception ex) {
            logger.error("An error occurred while pushing data to kafka topic. Error: {}", ex.getMessage());
        }
    }

    private String getObjectAsJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonRuntimeException("Json Processing Exception during object to string conversion", e);
        }
    }
}
