package com.asafeorneles.cadastro_pets_crud.repositories;

import com.asafeorneles.cadastro_pets_crud.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PetReposirory extends JpaRepository<Pet, UUID> {

}
