/**
 * Copyright (C) 2023 Lokesh Bisht
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package dev.lokeshbisht.catalogservice.exceptions;

public class FeedbackNotFoundException extends RuntimeException {

    private static final Long serialVersionID = 1L;

    public FeedbackNotFoundException(String message) {
        super(message);
    }
}
