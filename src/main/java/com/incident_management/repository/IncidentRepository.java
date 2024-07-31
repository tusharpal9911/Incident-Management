/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incident_management.repository;

import com.incident_management.entity.Incident;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface IncidentRepository extends JpaRepository<Incident, String> {

    List<Incident> findByReporter_Id(Long reporterId);

    Optional<Incident> findIncidentByIncidentId(String incidentId);

    boolean existsByIncidentId(String incidentId);
}
