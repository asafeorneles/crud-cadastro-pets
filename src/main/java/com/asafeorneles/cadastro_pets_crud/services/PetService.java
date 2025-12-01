package com.asafeorneles.cadastro_pets_crud.services;

import com.asafeorneles.cadastro_pets_crud.dtos.PetRecordDto;
import com.asafeorneles.cadastro_pets_crud.entities.Pet;
import com.asafeorneles.cadastro_pets_crud.exceptions.PetNotFoundException;
import com.asafeorneles.cadastro_pets_crud.repositories.PetReposirory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PetService {

    @Autowired
    private PetReposirory petReposirory;

    public Pet createPet(PetRecordDto petRecordDto){
            var pet = new Pet();
            BeanUtils.copyProperties(petRecordDto, pet);
            return petReposirory.save(pet);
    }

    public List<Pet> findAllPets(Specification<Pet> specification){
        List<Pet> pets = petReposirory.findAll(specification);
        if (pets.isEmpty()){
            throw new PetNotFoundException("Pets not found"); // Talvez falar que nenhum pet foi cadastrado no sistema
        }
        return pets;
    }

    public Pet findById (UUID id){
        return petReposirory.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found"));
    }
    public Pet updatePet (UUID id, PetRecordDto petRecordDto){
        Pet petFound = petReposirory.findById(id).orElseThrow(() -> new PetNotFoundException("Pet not found"));
        BeanUtils.copyProperties(petRecordDto, petFound);
        return petReposirory.save(petFound);
    }

    public void deletePet (UUID id){
        Pet petFound = petReposirory.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found"));
        petReposirory.delete(petFound);
    }

    public List<Pet> findByName(String name){
        List<Pet> petsFound = petReposirory.findByNameContainingIgnoreCase(name);
        if (petsFound.isEmpty()){
            throw new PetNotFoundException("Pet not found");
        }
        return petsFound;
    }
}
