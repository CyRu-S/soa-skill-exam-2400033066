package com.klef.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContactResponse {
	
    private Long emergencyContactId;
    private String name;
    private String type;
    private String status;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
