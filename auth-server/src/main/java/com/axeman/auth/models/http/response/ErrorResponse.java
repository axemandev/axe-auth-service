package com.axeman.auth.models.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    String errorMessage;
    String errorCode;
    String cause;
    String suggestedResolution;
    String status;
    Integer statusCode;
    Date exceptionDate;
}
