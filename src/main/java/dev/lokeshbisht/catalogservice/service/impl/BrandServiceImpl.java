package dev.lokeshbisht.catalogservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lokeshbisht.catalogservice.dto.brand.BrandDto;
import dev.lokeshbisht.catalogservice.entity.Brand;
import dev.lokeshbisht.catalogservice.exceptions.BrandAlreadyExistsException;
import dev.lokeshbisht.catalogservice.exceptions.JsonRuntimeException;
import dev.lokeshbisht.catalogservice.repository.BrandRepository;
import dev.lokeshbisht.catalogservice.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class BrandServiceImpl implements BrandService {

  @Autowired
  private BrandRepository brandRepository;

  @Autowired
  private ObjectMapper objectMapper;

  private static final Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);

  @Override
  public Brand createBrand(BrandDto brandDto) {
    logger.info("Saving brand: {}", brandDto);
    if (!brandRepository.findByBrandId(brandDto.getBrandId()).isEmpty()) {
      logger.error("Error occurred while saving brand. Brand already exists!");
      throw new BrandAlreadyExistsException("Brand already exists!");
    }
    try {
      Brand brand = objectMapper.readValue(objectMapper.writeValueAsString(brandDto), Brand.class);
      brand.setCreatedAt(Instant.now().getEpochSecond());
      logger.info("Successfully added brand: {}", brand);
      return brandRepository.save(brand);
    } catch (JsonMappingException e) {
      logger.error("Error occurred during saving document: {}", brandDto);
      throw new JsonRuntimeException("Json Mapping exception encountered during object to string conversion", e);
    } catch (JsonProcessingException e) {
      logger.error("Error occurred during saving document: {}", brandDto);
      throw new JsonRuntimeException("Json Processing exception encountered during object to string conversion.", e);
    }
  }
}
