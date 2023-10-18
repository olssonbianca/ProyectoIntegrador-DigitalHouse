package com.finalprojectc7t3.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampusDTO {

    private Integer campusId;
    private String campus;
    private String address;
    private String latitude;
    private String length;
    private Boolean isEnabled;
    private Integer provinceId;
    private Integer cityId;

   }
