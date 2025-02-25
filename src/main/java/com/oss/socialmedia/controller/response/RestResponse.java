package com.oss.socialmedia.controller.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestResponse {
    private Integer statusCode;
    private String error;
    private String message;
}
