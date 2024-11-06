package com.tecsup.petclinic.services;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;

@Service
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    @Transactional
    public Owner create(Owner owner) {
        log.info("Creating owner: {}", owner);
        return ownerRepository.save(owner);
    }

    @Override
    @Transactional
    public Owner update(Owner owner) {
        log.info("Updating owner: {}", owner);
        return ownerRepository.save(owner);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws OwnerNotFoundException {
        Owner owner = findById(id); // findById already handles exception if not found
        ownerRepository.delete(owner);
        log.info("Deleted owner with id: {}", id);
    }

    @Override
    public Owner findById(Integer id) throws OwnerNotFoundException {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (!owner.isPresent()) {
            throw new OwnerNotFoundException("Owner not found with id: " + id);
        }
        log.info("Found owner with id: {}", id);
        return owner.get();
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        List<Owner> owners = ownerRepository.findByLastName(lastName);
        if (owners.isEmpty()) {
            log.warn("No owners found with last name: {}", lastName);
        } else {
            log.info("Found {} owner(s) with last name: {}", owners.size(), lastName);
        }
        return owners;
    }

    @Override
    public List<Owner> findByCity(String city) {
        List<Owner> owners = ownerRepository.findByCity(city);
        if (owners.isEmpty()) {
            log.warn("No owners found in city: {}", city);
        } else {
            log.info("Found {} owner(s) in city: {}", owners.size(), city);
        }
        return owners;
    }

    @Override
    public List<Owner> findByFirstName(String firstName) {
        List<Owner> owners = ownerRepository.findByFirstName(firstName);
        if (owners.isEmpty()) {
            log.warn("No owners found with first name: {}", firstName);
        } else {
            log.info("Found {} owner(s) with first name: {}", owners.size(), firstName);
        }
        return owners;
    }

    @Override
    public List<Owner> findAll() {
        List<Owner> owners = ownerRepository.findAll();
        log.info("Found {} owners in total.", owners.size());
        return owners;
    }
}
