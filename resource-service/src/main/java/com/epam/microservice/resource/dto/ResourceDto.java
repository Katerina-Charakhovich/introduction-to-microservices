package com.epam.microservice.resource.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceDto {
    private Long id;
    private String fileName;
    private byte[] file;
}
