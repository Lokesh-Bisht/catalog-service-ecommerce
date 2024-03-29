/**
 * Copyright (C) 2023 Lokesh Bisht
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

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

    @Operation(summary = "deleteProductFeedback")
    @DeleteMapping("/product/{productId}")
    public void deleteProductFeedback(@PathVariable Integer productId, @RequestParam(name = "user_id") Integer userId) {
        feedbackService.deleteProductFeedback(productId, userId);
    }

    @Operation(summary = "upsertProductFeedback")
    @PutMapping("/product/{productId}")
    public ProductFeedback upsertProductFeedback(@PathVariable Integer productId, @Valid @RequestBody ProductFeedbackDto productFeedbackDto) {
        return feedbackService.upsertProductFeedback(productId, productFeedbackDto);
    }
}
