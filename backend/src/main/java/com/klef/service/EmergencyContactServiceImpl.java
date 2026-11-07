package com.klef.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.klef.dto.EmergencyContactRequest;
import com.klef.dto.EmergencyContactResponse;
import com.klef.entity.EmergencyContact;
import com.klef.repository.EmergencyContactRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmergencyContactServiceImpl implements EmergencyContactService {

    private final EmergencyContactRepository repo;

    @Override
    public EmergencyContactResponse saveEmergencyContact(EmergencyContactRequest request) {
        EmergencyContact contact = EmergencyContact.builder()
                .name(request.getName())
                .type(request.getType())
                .status(request.getStatus())
                .location(request.getLocation())
                .build();
        
        return mapToResponse(repo.save(contact)); 
    }

    @Override
    public EmergencyContactResponse updateEmergencyContact(Long id, EmergencyContactRequest request) {
        EmergencyContact contact = repo.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        EmergencyContact updated = EmergencyContact.builder()
        		.name(request.getName())
        		.type(request.getType())
        		.status(request.getStatus())
        		.location(request.getLocation())
        		.emergencyContactId(contact.getEmergencyContactId())
        		.build();
        
        EmergencyContact updatedUser = repo.save(updated);
        
        return mapToResponse(updatedUser); 
    }

    @Override
    public String deleteEmergencyContact(Long id) {
    	
    	if (repo.existsById(id)) {
    		repo.deleteById(id);
    		return "Emergency contact Deleted";
    	} else {
    		return "Emergency contact not found";
    	} 
    	
    }

    @Override
    public List<EmergencyContactResponse> getAllEmergencyContacts() {
        return repo.findAll()
        		.stream()
        		.map(this::mapToResponse)
        		.collect(Collectors.toList()); 
    }

    @Override
    public EmergencyContactResponse getEmergencyContactById(Long id) {
        EmergencyContact contact = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Emergency contact not found with id: " + id));

        return mapToResponse(contact);
    }

    @Override
    public List<EmergencyContactResponse> findByName(String name) {
        return repo.findByName(name)
        		.stream()
        		.map(this::mapToResponse)
        		.collect(Collectors.toList());
    }

    @Override
    public List<EmergencyContactResponse> findByType(String type) {
        return repo.findByType(type)
        		.stream()
        		.map(this::mapToResponse)
        		.collect(Collectors.toList());
    }

    private EmergencyContactResponse mapToResponse(EmergencyContact contact) {
        return EmergencyContactResponse.builder()
                .emergencyContactId(contact.getEmergencyContactId())
                .name(contact.getName())
                .type(contact.getType())
                .status(contact.getStatus())
                .location(contact.getLocation())
                .createdAt(contact.getCreatedAt())
                .updatedAt(contact.getUpdatedAt())
                .build();
    }
}
