package org.miage.intervenantservice.boundary;

import org.springframework.util.ReflectionUtils;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.miage.intervenantservice.entity.Intervenant;

@RestController
@RequestMapping(value="/intervenants", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Intervenant.class)
public class IntervenantRepresentation {

    private final IntervenantResource ir;

    public IntervenantRepresentation(IntervenantResource ir) {
        this.ir = ir;
    }

    // GET all
    @GetMapping
    public ResponseEntity<?> getAllIntervenants() {
        Iterable<Intervenant> allIntervenants = ir.findAll();
        return ResponseEntity.ok(allIntervenants);
    }

    // GET one
    @GetMapping(value="/{intervenantId}")
    public ResponseEntity<?> getOneIntervenant(@PathVariable("intervenantId") String id) {
        return Optional.ofNullable(ir.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(i.get()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveIntervenant(@RequestBody Intervenant intervenant)  {
        Intervenant intervenant2Save = new Intervenant(
            UUID.randomUUID().toString(),
            intervenant.getNom(),
            intervenant.getPrenom(),
            intervenant.getCommune(),
            intervenant.getCodepostal()
        );
        Intervenant saved = ir.save(intervenant2Save);
        URI location = linkTo(IntervenantRepresentation.class).slash(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // DELETE
    @DeleteMapping(value = "/{intervenantId}")
    @Transactional
    public ResponseEntity<?> deleteIntervenant(@PathVariable("intervenantId") String intervenantId) {
        Optional<Intervenant> intervenant = ir.findById(intervenantId);
        if (intervenant.isPresent()) {
            ir.delete(intervenant.get());
        }
        return ResponseEntity.noContent().build();
    }

    // PUT
    @PutMapping(value = "/{intervenantId}")
    @Transactional
    public ResponseEntity<?> updateIntervenant(@RequestBody Intervenant intervenant,
            @PathVariable("intervenantId") String intervenantId) {
        Optional<Intervenant> body = Optional.ofNullable(intervenant);
        if (!body.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        if (!ir.existsById(intervenantId)) {
            return ResponseEntity.notFound().build();
        }
        intervenant.setId(intervenantId);
        Intervenant result = ir.save(intervenant);
        return ResponseEntity.ok().build();
    }

    // PATCH
    @PatchMapping(value = "/{intervenantId}")
    @Transactional
    public ResponseEntity<?> updateIntervenantPartiel(@PathVariable("intervenantId") String intervenantId,
            @RequestBody Map<Object, Object> fields) {
        Optional<Intervenant> body = ir.findById(intervenantId);
        if (body.isPresent()) {
            Intervenant intervenant = body.get();
            fields.forEach((f, v) -> {
                Field field = ReflectionUtils.findField(Intervenant.class, f.toString());
                field.setAccessible(true);
                ReflectionUtils.setField(field, intervenant, v);
            });
            intervenant.setId(intervenantId);
            ir.save(intervenant);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }















}
