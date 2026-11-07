package com.klef.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContactRequest {
    @NotBlank
    private String name;
    
    @NotBlank
    private String type;
    
    @NotBlank
    private String status;
    
    @NotBlank
    private String location;
}
