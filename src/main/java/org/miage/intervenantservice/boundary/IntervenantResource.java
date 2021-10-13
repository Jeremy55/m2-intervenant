package org.miage.intervenantservice.boundary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.miage.intervenantservice.entity.Intervenant;

public interface IntervenantResource extends JpaRepository<Intervenant, String>{
}
