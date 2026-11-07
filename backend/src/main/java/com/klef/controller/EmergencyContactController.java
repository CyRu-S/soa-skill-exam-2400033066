package com.klef.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.dto.EmergencyContactRequest;
import com.klef.dto.EmergencyContactResponse;
import com.klef.service.EmergencyContactService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/emergencycontact")
@RequiredArgsConstructor
public class EmergencyContactController {

    private final EmergencyContactService service;

    @GetMapping("/")
    public String home() {
        return "Emergency Contact Service";
    }

    @PostMapping("/saveemergencycontact")
    public ResponseEntity<EmergencyContactResponse> saveEmergencyContact(
            @Valid @RequestBody EmergencyContactRequest request) {
        return ResponseEntity.ok(service.saveEmergencyContact(request));
    }

    @PutMapping("/updateemergencycontact/{id}")
    public ResponseEntity<EmergencyContactResponse> updateEmergencyContact(
            @PathVariable Long id, @Valid @RequestBody EmergencyContactRequest request) {
        return ResponseEntity.ok(service.updateEmergencyContact(id, request));
    }

    @DeleteMapping("/deleteemergencycontact/{id}")
    public ResponseEntity<String> deleteEmergencyContact(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteEmergencyContact(id));
    }

    @GetMapping("/displayall")
    public ResponseEntity<List<EmergencyContactResponse>> displayAll() {
        return ResponseEntity.ok(service.getAllEmergencyContacts());
    }

    @GetMapping("/displayemergencycontactbyid/{id}")
    public ResponseEntity<EmergencyContactResponse> displayEmergencyContactById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmergencyContactById(id));
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<EmergencyContactResponse>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    @GetMapping("/findByType")
    public ResponseEntity<List<EmergencyContactResponse>> findByType(@RequestParam String type) {
        return ResponseEntity.ok(service.findByType(type));
    }
}
