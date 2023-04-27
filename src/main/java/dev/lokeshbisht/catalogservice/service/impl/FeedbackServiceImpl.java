package dev.lokeshbisht.catalogservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lokeshbisht.catalogservice.dto.feedback.ProductFeedbackDto;
import dev.lokeshbisht.catalogservice.entity.ProductFeedback;
import dev.lokeshbisht.catalogservice.exceptions.JsonRuntimeException;
import dev.lokeshbisht.catalogservice.repository.ProductFeedbackRepository;
import dev.lokeshbisht.catalogservice.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class FeedbackServiceImpl implements FeedbackService {

  private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

  @Autowired
  private ProductFeedbackRepository productFeedbackRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public ProductFeedback createProductFeedback(ProductFeedbackDto productFeedbackDto) {
    logger.info("Saving user feedback for product: {}", productFeedbackDto);
    ProductFeedback productFeedback;
    try {
      productFeedback = objectMapper.readValue(objectMapper.writeValueAsString(productFeedbackDto), ProductFeedback.class);
    } catch (JsonMappingException e) {
      logger.error("Error occurred during updating document: {}", productFeedbackDto);
      throw new JsonRuntimeException("Json Mapping exception encountered during object to string conversion", e);
    } catch (JsonProcessingException e) {
      logger.error("Error occurred during updating document: {}", productFeedbackDto);
      throw new JsonRuntimeException("Json Processing exception encountered during object to string conversion", e);
    }
    productFeedback.setCreatedAt(Instant.now().getEpochSecond());
    logger.info("User feedback for product {} is saved successfully.", productFeedback.getProductId());
    productFeedbackRepository.save(productFeedback);
    return productFeedback;
  }
}
