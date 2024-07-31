/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incident_management.service;

import com.incident_management.entity.Incident;
import com.incident_management.exception.IncidentNotFoundException;
import java.util.List;

/**
 *
 * @author user
 */
@SuppressWarnings("rawtypes")
public interface IncidentService {

    Incident createIncident(Incident incident);

    List<Incident> getUserIncidents(Long reporterId);

    Incident updateIncident(String incidentId, Incident incident) throws IncidentNotFoundException;

    Incident getIncidentById(String incidentId) throws IncidentNotFoundException;

}
