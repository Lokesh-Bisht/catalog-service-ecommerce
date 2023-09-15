package dev.lokeshbisht.catalogservice.service;

import dev.lokeshbisht.catalogservice.dto.feedback.ProductFeedbackDto;
import dev.lokeshbisht.catalogservice.entity.ProductFeedback;

public interface FeedbackService {

    ProductFeedback createProductFeedback(ProductFeedbackDto productFeedbackDto);
    void deleteProductFeedback(Integer productId, Integer userId);
    ProductFeedback upsertProductFeedback(Integer productId, ProductFeedbackDto productFeedbackDto);
}
