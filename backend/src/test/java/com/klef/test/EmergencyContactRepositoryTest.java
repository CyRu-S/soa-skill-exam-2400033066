package com.klef.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.entity.EmergencyContact;
import com.klef.repository.EmergencyContactRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmergencyContactRepositoryTest {

    @Autowired
    private EmergencyContactRepository repository;

    private Long emergencyContactId;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        EmergencyContact hospital = repository.saveAndFlush(createContact("City Hospital", "Medical"));
        emergencyContactId = hospital.getEmergencyContactId();
        repository.saveAndFlush(createContact("Ambulance Service", "Medical"));
        repository.saveAndFlush(createContact("Fire Station", "Fire"));
    }

    @Test
    void testFindAllEmergencyContacts() {
        List<EmergencyContact> contacts = repository.findAll();
        assertNotNull(contacts);
        assertFalse(contacts.isEmpty());
        assertEquals(3, contacts.size());
        System.out.println("Total Emergency Contacts: " + contacts.size());
    }

    @Test
    void testFindEmergencyContactById() {
        Optional<EmergencyContact> contact = repository.findById(emergencyContactId);
        assertTrue(contact.isPresent());
        assertEquals(emergencyContactId, contact.get().getEmergencyContactId());
        System.out.println("Emergency Contact ID: " + contact.get().getEmergencyContactId());
        System.out.println("Name: " + contact.get().getName());
    }

    @Test
    void testFindEmergencyContactByName() {
        List<EmergencyContact> contacts = repository.findByName("City Hospital");
        assertNotNull(contacts);
        assertEquals(1, contacts.size());
        assertEquals("City Hospital", contacts.getFirst().getName());
        System.out.println("Name: " + contacts.getFirst().getName());
        System.out.println("Type: " + contacts.getFirst().getType());
    }

    @Test
    void testFindEmergencyContactByInvalidName() {
        List<EmergencyContact> contacts = repository.findByName("Not Found Hospital");
        assertTrue(contacts.isEmpty());
    }

    @Test
    void testDatabaseConnection() {
        long count = repository.count();
        assertEquals(3L, count);
        System.out.println("Total Emergency Contacts in Database: " + count);
    }

    @Test
    void testEmergencyContactDetails() {
        Optional<EmergencyContact> contact = repository.findById(emergencyContactId);
        assertTrue(contact.isPresent());
        EmergencyContact foundContact = contact.get();
        assertNotNull(foundContact.getEmergencyContactId());
        assertNotNull(foundContact.getName());
        assertNotNull(foundContact.getType());
        assertNotNull(foundContact.getStatus());
        assertNotNull(foundContact.getLocation());
        assertNotNull(foundContact.getCreatedAt());
        assertNotNull(foundContact.getUpdatedAt());
        System.out.println("Name: " + foundContact.getName());
        System.out.println("Type: " + foundContact.getType());
        System.out.println("Status: " + foundContact.getStatus());
        System.out.println("Location: " + foundContact.getLocation());
    }

    @Test
    void testEmergencyContactType() {
        Optional<EmergencyContact> contact = repository.findById(emergencyContactId);
        assertTrue(contact.isPresent());
        assertEquals("Medical", contact.get().getType());
        System.out.println("Type: " + contact.get().getType());
    }

    @Test
    void testTotalEmergencyContactCount() {
        long count = repository.count();
        assertEquals(3L, count);
    }

    @Test
    void testEmergencyContactTypes() {
        List<EmergencyContact> contacts = repository.findByType("Medical");
        assertNotNull(contacts);
        assertEquals(2, contacts.size());
        assertTrue(contacts.stream().allMatch(contact -> "Medical".equals(contact.getType())));
        assertTrue(repository.findByType("Unknown Type").isEmpty());
    }

    private EmergencyContact createContact(String name, String type) {
        return EmergencyContact.builder()
                .name(name)
                .type(type)
                .status("Active")
                .location("Hyderabad")
                .build();
    }

    @Test
    void testFindEmergencyContactByInvalidId() {
        Optional<EmergencyContact> contact = repository.findById(-1L);
        assertTrue(contact.isEmpty());
    }
}
