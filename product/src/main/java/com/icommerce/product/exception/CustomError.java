package com.icommerce.product.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomError {

    private String errorCode;
    private String errorMessage;

}
