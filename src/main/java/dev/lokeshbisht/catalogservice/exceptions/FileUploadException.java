/**
 * Copyright (C) 2023 Lokesh Bisht
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package dev.lokeshbisht.catalogservice.exceptions;

import java.io.Serial;

public class FileUploadException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileUploadException(String message) {
        super(message);
    }
}
