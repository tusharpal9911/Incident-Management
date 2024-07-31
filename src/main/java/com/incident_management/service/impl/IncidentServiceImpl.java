/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incident_management.service.impl;

import com.incident_management.entity.Incident;
import com.incident_management.exception.IncidentNotFoundException;
import com.incident_management.repository.IncidentRepository;
import com.incident_management.service.IncidentService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@SuppressWarnings("rawtypes")
@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    @Override
    public Incident createIncident(Incident incident) {
        incident.setIncidentId(generateUniqueIncidentId());
        incident.setReportedDateTime(LocalDateTime.now());
        return incidentRepository.save(incident);
    }

    @Override
    public List<Incident> getUserIncidents(Long reporterId) {
        return incidentRepository.findByReporter_Id(reporterId);
    }

    @Override
    public Incident updateIncident(String incidentId, Incident incident) throws IncidentNotFoundException {
        Incident existingIncident = getIncidentById(incidentId);
        if (!existingIncident.getStatus().equalsIgnoreCase("Closed")) {
            existingIncident.setDetails(incident.getDetails());
            existingIncident.setPriority(incident.getPriority());
            existingIncident.setStatus(incident.getStatus());
            return incidentRepository.save(existingIncident);
        } else {
            throw new IncidentNotFoundException("Cannot update closed incident");
        }
    }

    @Override
    public Incident getIncidentById(String incidentId) throws IncidentNotFoundException {
        Optional<Incident> incident = incidentRepository.findIncidentByIncidentId(incidentId);
        if (incident.isPresent()) {
            return incident.get();
        } else {
            throw new IncidentNotFoundException("Incident not found or you do not have permission to view this incident");
        }
    }

    private String generateUniqueIncidentId() {
        String incidentId;
        Random random = new Random();
        int year = LocalDateTime.now().getYear();
        do {
            incidentId = "RMG" + random.nextInt(90000) + year;
        } while (incidentRepository.existsByIncidentId(incidentId));
        return incidentId;
    }

}
