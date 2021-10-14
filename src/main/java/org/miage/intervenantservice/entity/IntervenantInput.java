package org.miage.intervenantservice.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntervenantInput {

    @NotNull
    @NotBlank
    private String nom;
    @Size(min=2)
    private String prenom;
    @Size(min=3)
    private String commune;
    @Size(min=5, max=5)
    @Pattern(regexp = "[0-9]+")
    private String codepostal;
	
}
