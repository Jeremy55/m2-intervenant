package org.miage.intervenantservice.boundary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.miage.intervenantservice.entity.Intervenant;

@RepositoryRestResource(collectionResourceRel="intervenants")
public interface IntervenantResource extends JpaRepository<Intervenant, String>{
    // GET, POST, PUT, DELETE gérées automatiqsuement par Spring
}
