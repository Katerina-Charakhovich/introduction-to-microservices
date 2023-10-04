package com.epam.microservice.resource.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ResourceDeleteResponseDto {
    private List<Long> ids;
}
