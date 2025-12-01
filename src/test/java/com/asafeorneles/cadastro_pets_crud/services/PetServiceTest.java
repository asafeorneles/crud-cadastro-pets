package com.asafeorneles.cadastro_pets_crud.services;

import com.asafeorneles.cadastro_pets_crud.dtos.PetRecordDto;
import com.asafeorneles.cadastro_pets_crud.entities.Pet;
import com.asafeorneles.cadastro_pets_crud.enums.Sexo;
import com.asafeorneles.cadastro_pets_crud.enums.Tipo;
import com.asafeorneles.cadastro_pets_crud.repositories.PetReposirory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class PetServiceTest {
    // Quando algum metodo que é testado depende dessa instância (petRepository), o Mockito vai criar os métodos de
    // petRepository como se estivessem vazios, sem acessar banco, sem executar lógica real, sem nada pesado. Ele também permite que controlemos o que ele retorna quando a instância é chamada.

    @Mock
    private PetReposirory petReposirory;

    private PetRecordDto petRecordDto;
    private Pet pet;

    @Captor
    ArgumentCaptor<Pet> petArgumentCaptor;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        petRecordDto = new PetRecordDto(Tipo.CACHORRO, Sexo.FEMININO, "Doguin", "Tupirani", BigDecimal.valueOf(8.3), BigDecimal.valueOf(21.7), "Vira-Lata", "Rua A", "54", "Bairro B", "Belo Horizonte");
        pet = new Pet();
    }

    @Nested
    class createPet{

        @Test
        void shouldCreateUserWithSuccess() {
            when(petReposirory.save(any(Pet.class))).thenReturn(pet); // Quando o petRepository for chamado passando qualquer objeto da classe Pet, sempre retorne esse pet
            Pet savedPet = petService.createPet(petRecordDto);
            verify(petReposirory).save(petArgumentCaptor.capture());
            Pet petCapturado = petArgumentCaptor.getValue();
            assertNotNull(savedPet);
            assertThat(petCapturado).usingRecursiveComparison().ignoringFields("id").isEqualTo(petRecordDto);
        }

        @Test
        void shouldThrowExceptionWhenUserIsNotCreate(){
            when(petReposirory.save(any(Pet.class))).thenThrow(new RuntimeException());
            assertThrows(RuntimeException.class, () -> petService.createPet(petRecordDto));
        }
    }

    @Test
    void findAllPets() {
    }

    @Test
    void findById() {
    }

    @Test
    void updatePet() {
    }

    @Test
    void deletePet() {
    }

    @Test
    void findByName() {
    }
}