package com.klef.service;

import java.util.List;

import com.klef.dto.EmergencyContactRequest;
import com.klef.dto.EmergencyContactResponse;

public interface EmergencyContactService {
    EmergencyContactResponse saveEmergencyContact(EmergencyContactRequest request);
    
    EmergencyContactResponse updateEmergencyContact(Long id, EmergencyContactRequest request);
    
    String deleteEmergencyContact(Long id);
    
    List<EmergencyContactResponse> getAllEmergencyContacts();
    
    EmergencyContactResponse getEmergencyContactById(Long id);
    
    List<EmergencyContactResponse> findByName(String name);
    
    List<EmergencyContactResponse> findByType(String type);
}
