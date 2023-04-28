package dev.lokeshbisht.catalogservice.repository;

import dev.lokeshbisht.catalogservice.entity.ProductFeedback;

public interface CustomProductFeedbackRepository {

  ProductFeedback findProductFeedbackByUser(Integer productId, Integer userId);
  void deleteProductFeedbackByUserAndProductId(Integer productId, Integer userId);
}
