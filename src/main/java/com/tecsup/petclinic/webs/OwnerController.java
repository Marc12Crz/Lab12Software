package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.mapper.OwnerMapper;
import com.tecsup.petclinic.domain.OwnerTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar las operaciones CRUD de los propietarios (Owners)
 *
 * @author jgomezm
 */
@RestController
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    public OwnerController(OwnerService ownerService, OwnerMapper ownerMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    @GetMapping(value = "/owners")
    public ResponseEntity<List<OwnerTO>> findAllOwners() {
        List<Owner> owners = ownerService.findAll();
        log.info("Owners: {}", owners);
        List<OwnerTO> ownersTO = this.ownerMapper.toOwnerTOList(owners);
        return ResponseEntity.ok(ownersTO);
    }

    @PostMapping(value = "/owners")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OwnerTO> create(@RequestBody OwnerTO ownerTO) {
        Owner newOwner = this.ownerMapper.toOwner(ownerTO);
        OwnerTO newOwnerTO = this.ownerMapper.toOwnerTO(ownerService.create(newOwner));
        return ResponseEntity.status(HttpStatus.CREATED).body(newOwnerTO);
    }

    @GetMapping(value = "/owners/{id}")
    public ResponseEntity<OwnerTO> findById(@PathVariable Integer id) {
        try {
            Owner owner = ownerService.findById(id);
            OwnerTO ownerTO = this.ownerMapper.toOwnerTO(owner);
            return ResponseEntity.ok(ownerTO);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/owners/{id}")
    public ResponseEntity<OwnerTO> update(@RequestBody OwnerTO ownerTO, @PathVariable Integer id) {
        try {
            Owner existingOwner = ownerService.findById(id);
            existingOwner.setFirstName(ownerTO.getFirst_name());
            existingOwner.setLastName(ownerTO.getLast_name());
            existingOwner.setAddress(ownerTO.getAddress());
            existingOwner.setCity(ownerTO.getCity());
            existingOwner.setTelephone(ownerTO.getTelephone());
            ownerService.update(existingOwner);
            OwnerTO updatedOwnerTO = this.ownerMapper.toOwnerTO(existingOwner);
            return ResponseEntity.ok(updatedOwnerTO);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/owners/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            ownerService.delete(id);
            return ResponseEntity.ok("Deleted Owner with ID: " + id);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
