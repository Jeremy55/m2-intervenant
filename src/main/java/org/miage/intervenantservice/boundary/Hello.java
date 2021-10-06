package org.miage.intervenantservice.boundary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    
    @GetMapping("/hello")
    public String coucou() {
        return "Duke, Tim, Anna";
    }
}
