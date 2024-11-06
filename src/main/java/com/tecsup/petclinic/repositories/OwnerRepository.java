package com.tecsup.petclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tecsup.petclinic.entities.Owner;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {


    List<Owner> findByLastName(String lastName);

    List<Owner> findByCity(String city);

    List<Owner> findByFirstName(String firstName);

    @Override
    List<Owner> findAll();
}
