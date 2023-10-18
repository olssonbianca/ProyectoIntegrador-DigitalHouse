package com.finalprojectc7t3.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ImageDTO {

    private Integer imageId;

    private String urlImage;

    private String keyImage;

    private Boolean isEnabled;

}
