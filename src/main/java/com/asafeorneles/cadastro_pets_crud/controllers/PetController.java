package com.asafeorneles.cadastro_pets_crud.controllers;

import com.asafeorneles.cadastro_pets_crud.dtos.PetRecordDto;
import com.asafeorneles.cadastro_pets_crud.entities.Pet;
import com.asafeorneles.cadastro_pets_crud.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping ("/pets")
    public ResponseEntity<Pet> createPet (@RequestBody @Valid PetRecordDto petRecordDto){
        Pet pet = petService.createPet(petRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> findAllPets(){
        List<Pet> petsFound = petService.findAllPets();
        return ResponseEntity.status(HttpStatus.OK).body(petsFound);
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<?> findPetById(@PathVariable(value = "id") UUID id){
        Pet pet = petService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pet);
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable(value = "id") UUID id, @RequestBody @Valid PetRecordDto petRecordDto){
        Pet pet = petService.updatePet(id, petRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(pet);
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<?> deletePet(@PathVariable(value = "id") UUID id){
        petService.deletePet(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pet deleted successfully");
    }
}
