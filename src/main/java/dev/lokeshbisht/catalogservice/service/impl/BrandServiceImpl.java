package dev.lokeshbisht.catalogservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lokeshbisht.catalogservice.dto.brand.BrandDto;
import dev.lokeshbisht.catalogservice.dto.brand.BrandSearchFilterDto;
import dev.lokeshbisht.catalogservice.dto.brand.BrandSearchResponseDto;
import dev.lokeshbisht.catalogservice.entity.Brand;
import dev.lokeshbisht.catalogservice.exceptions.BrandAlreadyExistsException;
import dev.lokeshbisht.catalogservice.exceptions.BrandNotFoundException;
import dev.lokeshbisht.catalogservice.exceptions.JsonRuntimeException;
import dev.lokeshbisht.catalogservice.repository.BrandRepository;
import dev.lokeshbisht.catalogservice.repository.CustomBrandRepository;
import dev.lokeshbisht.catalogservice.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class BrandServiceImpl implements BrandService {

  @Autowired
  private BrandRepository brandRepository;

  @Autowired
  private CustomBrandRepository customBrandRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Value("${kafka.topic.brand}")
  private String brandTopic;

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

  @Override
  public Optional<Brand> getBrand(String brandId) {
    logger.info("Find brand with id: {}", brandId);
    Optional<Brand> brand = brandRepository.findByBrandId(Integer.parseInt(brandId));
    if (brand.isEmpty()) {
      logger.error("Brand not found!");
      throw new BrandNotFoundException("Brand not found!");
    }
    logger.info("Found brand: {}", brand);
    return brand;
  }

  @Override
  public Brand updateBrand(String brandId, BrandDto brandDto) {
    logger.info("Update brand: {} with id: {}", brandDto, brandId);
    Optional<Brand> brand = brandRepository.findByBrandId(Integer.parseInt(brandId));
    if (brand.isEmpty()) {
      logger.error("Brand with id {} is not found.", brandId);
      throw new BrandNotFoundException("Brand not found!");
    }
    try {
      Brand updatedBrand = objectMapper.readValue(objectMapper.writeValueAsString(brandDto), Brand.class);
      updatedBrand.setId(brand.get().getId());
      updatedBrand.setCreatedAt(brand.get().getCreatedAt());
      updatedBrand.setCreatedBy(brand.get().getCreatedBy());
      updatedBrand.setUpdatedAt(Instant.now().getEpochSecond());
      logger.info("Successfully updated brand.");
      return brandRepository.save(updatedBrand);
    } catch (JsonMappingException e) {
      logger.error("Error occurred during updating document: {}", brandDto);
      throw new JsonRuntimeException("Json Mapping exception encountered during object to string conversion", e);
    } catch (JsonProcessingException e) {
      logger.error("Error occurred during updating document: {}", brandDto);
      throw new JsonRuntimeException("Json Processing exception encountered during object to string conversion", e);
    }
  }

  @Override
  public void deleteBrand(String brandId) {
    logger.info("Delete brand with id: {}", brandId);
    if (brandRepository.findByBrandId(Integer.parseInt(brandId)).isEmpty()) {
      logger.error("Error deleting brand with id: {}", brandId);
      throw new BrandNotFoundException("Brand not found!");
    }
    brandRepository.deleteByBrandId(Integer.parseInt(brandId));
    logger.info("Brand with id {} is deleted successfully.", brandId);
  }

  @Override
  public List<Brand> getAllBrandsByCategoryId(String categoryId) {
    logger.info("Get all brands with category id: {}", categoryId);
    // Need to add category check
    return brandRepository.findAllByCategoryId(Integer.parseInt(categoryId));
  }

  @Override
  public BrandSearchResponseDto search(String searchQuery, Pageable page, BrandSearchFilterDto filter) {
    logger.info("Start brand search with query: {} and filters: {}", searchQuery, filter);
    return customBrandRepository.search(searchQuery, page, filter);
  }

  @Override
  public void bulkCreateBrand(List<BrandDto> brandDtoList) {
    logger.info("Starting bulkCreateBrand");
//    for (BrandDto brandDto : brandDtoList) {
//      CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(brandTopic, brandDto.getBrandId().toString(), brandDto);
//      future.whenComplete((result, ex) -> {
//        if (ex == null) {
//          logger.info("Successfully pushed message: {} to kafka topic: {}", brandDto, brandTopic);
//        } else {
//          logger.error("Error while pushing message: {} to kafka topic: {}", brandDto, brandTopic);
//        }
//      });
//    }
  }
}
