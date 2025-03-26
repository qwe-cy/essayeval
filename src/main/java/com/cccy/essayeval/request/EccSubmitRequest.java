package com.cccy.essayeval.request;

import lombok.Data;

@Data
public class EccSubmitRequest {

    private String title;

    private String content;

    private String grade;

    private String code;

    private String status;
}
