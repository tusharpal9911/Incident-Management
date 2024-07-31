/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incident_management.controller;

import com.incident_management.entity.Incident;
import com.incident_management.exception.IncidentNotFoundException;
import com.incident_management.service.IncidentService;
import com.incident_management.service.helpers.IncidentDTO;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@SuppressWarnings("rawtypes")
@RequestMapping(value = "/incident")
@CrossOrigin("*")
@RestController
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @PostMapping("/create")
    public ResponseEntity<String> createIncident(@RequestBody Incident incident) {
        incidentService.createIncident(incident);
        return ResponseEntity.ok("Incident created successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateIncident(@PathVariable("id") String incidentId, @RequestBody Incident incident) {
        try {
            incidentService.updateIncident(incidentId, incident);
            return ResponseEntity.ok("Incident updated successfully");
        } catch (IncidentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incident not found or cannot be updated");
        }
    }

    @GetMapping("/user")
    public List<IncidentDTO> getUserIncidents(@RequestParam Long reporterId) {
        List<Incident> incidents = incidentService.getUserIncidents(reporterId);
        return incidents.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<IncidentDTO> getIncidentById(@PathVariable("id") String incidentId) throws IncidentNotFoundException {
        try {
            Incident incident = incidentService.getIncidentById(incidentId);
            IncidentDTO dto = convertToDTO(incident);
            return ResponseEntity.ok(dto);
        } catch (IncidentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    private IncidentDTO convertToDTO(Incident incident) {
        IncidentDTO dto = new IncidentDTO();
        dto.setId(incident.getId());
        dto.setIncidentId(incident.getIncidentId());
        dto.setDetails(incident.getDetails());
        dto.setReportedDateTime(incident.getReportedDateTime());
        dto.setPriority(incident.getPriority());
        dto.setStatus(incident.getStatus());
        dto.setReporterName(incident.getReporter().getFirstName() + incident.getReporter().getLastName());
        return dto;
    }

}
