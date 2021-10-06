package org.miage.intervenantservice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity     // ORM: mapping des instances de la classe comme nuplet dans H2
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Intervenant implements Serializable {
    
    private static final long serialVersionUID = 765432234567L;
     
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String commune;
    private String codepostal;
}
