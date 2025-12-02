package com.asafeorneles.cadastro_pets_crud.services;

import com.asafeorneles.cadastro_pets_crud.dtos.PetRecordDto;
import com.asafeorneles.cadastro_pets_crud.entities.Pet;
import com.asafeorneles.cadastro_pets_crud.enums.Sexo;
import com.asafeorneles.cadastro_pets_crud.enums.Tipo;
import com.asafeorneles.cadastro_pets_crud.exceptions.PetNotFoundException;
import com.asafeorneles.cadastro_pets_crud.repositories.PetReposirory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PetServiceTest {
    @Mock
    private PetReposirory petReposirory;

    private PetRecordDto petRecordDto;
    private Pet pet;
    private Pet petBuild;

    @Captor
    ArgumentCaptor<Pet> petArgumentCaptor;

    @Captor
    ArgumentCaptor<UUID> idArgumentCaptor;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        petRecordDto = new PetRecordDto(Tipo.CACHORRO, Sexo.FEMININO, "DoguinDto", "TupiraniDto", BigDecimal.valueOf(8.3), BigDecimal.valueOf(21.7), "Vira-Lata", "Rua A", "54", "Bairro B", "Belo Horizonte");
        pet = new Pet();
        petBuild = Pet.builder()
                .id(UUID.randomUUID())
                .type(Tipo.CACHORRO)
                .sexo(Sexo.FEMININO)
                .name("Doguin")
                .lastName("Tupirani")
                .age(BigDecimal.valueOf(8.3))
                .weight(BigDecimal.valueOf(21.7))
                .race("Vira-Lata")
                .street("Rua A")
                .number("54")
                .neighborhood("Bairro B")
                .city("Belo Horizonte")
                .build();
    }

    @Nested
    class createPet {

        @Test
        void shouldCreateUserWithSuccess() {
            // ARRANGE (Configuração):
            when(petReposirory.save(any(Pet.class))).thenReturn(pet); // Quando o petRepository for chamado passando qualquer objeto da classe Pet, sempre retorne esse pet
            // ACT (A ação feita)
            Pet savedPet = petService.createPet(petRecordDto);
            // ASSERT (Verificação do que foi feito):
            verify(petReposirory).save(petArgumentCaptor.capture());
            Pet petCaptured = petArgumentCaptor.getValue();
            assertNotNull(savedPet);
            assertThat(petCaptured).usingRecursiveComparison().ignoringFields("id").isEqualTo(petRecordDto);
        }

        @Test
        void shouldThrowExceptionWhenUserIsNotCreate() {
            //ARRANGE
            when(petReposirory.save(any(Pet.class))).thenThrow(new RuntimeException());

            //ASSERT
            assertThrows(RuntimeException.class, () -> petService.createPet(petRecordDto));
            verify(petReposirory, times(1)).save(any(Pet.class));
        }
    }

    @Nested
    class findAllPets {
        @Test
        void shouldReturnAListOfPetsWithSuccess() {
            //ARRANGE
            when(petReposirory.findAll(any(Specification.class))).thenReturn(List.of(petBuild));

            //ACT
            List<Pet> petsFound = petService.findAllPets(Specification.unrestricted());

            //ASSERTS
            assertFalse(petsFound.isEmpty());
            assertEquals(1, petsFound.size());
            assertEquals(petsFound.get(0), petBuild);
        }

        @Test
        void shouldThrowExceptionWhenNoPetsAreFound() {
            //ARRANGE
            when(petReposirory.findAll(any(Specification.class))).thenReturn(List.of());

            //ASSERT
            assertThrows(PetNotFoundException.class, () -> petService.findAllPets(Specification.unrestricted()));
            verify(petReposirory, times(1)).findAll(any(Specification.class));
        }

    }

    @Nested
    class findById {

        @Test
        void shoudReturnAPetByIdWithSuccess() {
            // ARRANGE
            when(petReposirory.findById(petBuild.getId())).thenReturn(Optional.of(petBuild));

            // ACT
            Pet petFound = petService.findById(petBuild.getId());

            // ASSERT
            verify(petReposirory).findById(idArgumentCaptor.capture());
            UUID idCaptured = idArgumentCaptor.getValue();
            assertNotNull(petFound);
            assertEquals(petBuild.getId(), idCaptured);
        }

        @Test
        void shouldThrowExceptionWhenPetIsNotFound() {
            UUID falseId = UUID.randomUUID();
            // ARRANGE
            when(petReposirory.findById(falseId)).thenReturn(Optional.empty());

            // ASSERT
            assertThrows(PetNotFoundException.class, () -> petService.findById(falseId));
            verify(petReposirory, times(1)).findById(falseId);
        }

    }

    @Nested
    class findByName {
        @Test
        void shouldReturnOnePetWhenWeSearchByName() {
            //ARRANGE
            when(petReposirory.findByNameContainingIgnoreCase(petBuild.getName())).thenReturn(List.of(petBuild));

            //ACT
            List<Pet> petsFound = petService.findByName(petBuild.getName());

            //ASSERT
            verify(petReposirory).findByNameContainingIgnoreCase(petBuild.getName());
            assertEquals(petsFound.get(0), petBuild);
            assertEquals(1, petsFound.size());
        }

        @Test
        void shouldThrowExceptionWhenPetIsNotFoundSearchingByName() {
            //ARRANGE
            String nonExistName = "non-existent name";
            when(petReposirory.findByNameContainingIgnoreCase(nonExistName)).thenReturn(List.of());

            //ASSERT
            assertThrows(PetNotFoundException.class, () -> petService.findByName(nonExistName));
            verify(petReposirory, times(1)).findByNameContainingIgnoreCase(nonExistName);
        }
    }

    @Nested
    class updatePet {
        @Test
        void shouldUpdatePetWithSuccess() {
            //ARRANGE
            when(petReposirory.findById(petBuild.getId())).thenReturn(Optional.of(petBuild));
            when(petReposirory.save(any(Pet.class))).thenReturn(pet);

            //ACT
            Pet petUpdated = petService.updatePet(petBuild.getId(), petRecordDto);

            //ASSERT
            verify(petReposirory).findById(idArgumentCaptor.capture());
            UUID idCaptured = idArgumentCaptor.getValue();

            verify(petReposirory).save(petArgumentCaptor.capture());
            Pet petCaptured = petArgumentCaptor.getValue();

            assertNotNull(petUpdated);
            assertEquals(petBuild.getId(), idCaptured);
            assertThat(petCaptured)
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(petRecordDto);
            assertEquals(idCaptured, petCaptured.getId());
        }

        @Test
        void shouldThrowExceptionAndNotUpdateAPetWhenPetDoesNotExist(){
            //ARRANGE
            UUID idNonExistent = UUID.randomUUID();
            when(petReposirory.findById(idNonExistent)).thenReturn(Optional.empty());

            //ASSERT
            assertThrows(PetNotFoundException.class, ()-> petService.updatePet(idNonExistent, petRecordDto));
            verify(petReposirory, times(1)).findById(idNonExistent);
            verify(petReposirory, never()).save(any(Pet.class));

        }
    }

    @Nested
    class deletePet {
        @Test
        void shouldDeleteAPetWhitSuccess() {
            //ARRANGE
            when(petReposirory.findById(petBuild.getId())).thenReturn(Optional.of(petBuild));
            doNothing().when(petReposirory).delete(petBuild);

            //ACT
            petService.deletePet(petBuild.getId());


            //ASSERTS
            verify(petReposirory, times(1)).findById(idArgumentCaptor.capture());
            verify(petReposirory, times(1)).delete(petArgumentCaptor.capture());

            UUID idCaptures = idArgumentCaptor.getValue();
            Pet petCaptured = petArgumentCaptor.getValue();

            assertEquals(idCaptures, petBuild.getId());
            assertEquals(petBuild, petCaptured);

        }

        @Test
        void shouldThrowExceptionAndNotDeleteOnePetWhenPetDoesNotExist(){
            // ARRANGE
            UUID idNonExistent = UUID.randomUUID();
            when(petReposirory.findById(idNonExistent)).thenReturn(Optional.empty());

            // ASSERT
            assertThrows(PetNotFoundException.class, () -> petService.deletePet(idNonExistent));

            verify(petReposirory, times(1)).findById(idNonExistent);
            verify(petReposirory, never()).delete(any(Pet.class));
        }
    }

}