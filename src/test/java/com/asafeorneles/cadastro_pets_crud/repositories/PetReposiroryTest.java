package com.asafeorneles.cadastro_pets_crud.repositories;

import com.asafeorneles.cadastro_pets_crud.dtos.PetRecordDto;
import com.asafeorneles.cadastro_pets_crud.entities.Pet;
import com.asafeorneles.cadastro_pets_crud.enums.Sexo;
import com.asafeorneles.cadastro_pets_crud.enums.Tipo;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // Mostra pro Spring que este é um teste de Jpa
@ActiveProfiles("test")
class PetReposiroryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    PetReposirory petReposirory;

    @Test
    void findByNameContainingIgnoreCase_returnFalse_WhenPetIsGotSuccessfully() {
        String name = "Bolinha";
        PetRecordDto petRecordDto = new PetRecordDto(Tipo.CACHORRO, Sexo.FEMININO, name, "Tupirani", BigDecimal.valueOf(8.3), BigDecimal.valueOf(21.7), "Vira-Lata", "Rua A", "54", "Bairro B", "Belo Horizonte");
        this.createPet(petRecordDto);
        List<Pet> pets = petReposirory.findByNameContainingIgnoreCase(name);
        assertTrue(!pets.isEmpty());

    }

    @Test
    void findByNameContainingIgnoreCase_returnTrue_WhenPetIsNotGotSuccessfully() {
        String name = "Bolinha";
        List<Pet> pets = petReposirory.findByNameContainingIgnoreCase(name);
        assertTrue(pets.isEmpty());
    }

    private Pet createPet(PetRecordDto petRecordDto){
        var pet = new Pet();
        BeanUtils.copyProperties(petRecordDto, pet);
        this.entityManager.persist(pet); // Colocou o pet no banco de dados
        return pet;
    }

}