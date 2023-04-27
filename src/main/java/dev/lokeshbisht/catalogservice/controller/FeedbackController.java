package dev.lokeshbisht.catalogservice.controller;

import dev.lokeshbisht.catalogservice.dto.feedback.ProductFeedbackDto;
import dev.lokeshbisht.catalogservice.entity.ProductFeedback;
import dev.lokeshbisht.catalogservice.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/feedback")
@Tag(name = "FeedbackController", description = "Feedback Controller")
public class FeedbackController {

  @Autowired
  private FeedbackService feedbackService;

  @Operation(summary = "createProductFeedback")
  @PostMapping("/product/{productId}")
  public ProductFeedback createProductFeedback(@PathVariable() Integer productId, @Valid @RequestBody ProductFeedbackDto productFeedbackDto) {
    return feedbackService.createProductFeedback(productFeedbackDto);
  }
}
