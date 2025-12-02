package com.asafeorneles.cadastro_pets_crud.enums;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class SexoTest {
    @Test
    void shouldReturnTrueWhenSexoEnumFromValueIsOperatedWithSuccess(){
        assertEquals(Sexo.FEMININO, Sexo.fromValue("Femea"));
        assertEquals(Sexo.FEMININO, Sexo.fromValue("femea"));
        assertEquals(Sexo.FEMININO, Sexo.fromValue("fêmea"));

        assertEquals(Sexo.MASCULINO, Sexo.fromValue("Macho"));
        assertEquals(Sexo.MASCULINO, Sexo.fromValue("macho"));
        assertEquals(Sexo.MASCULINO, Sexo.fromValue("mácho"));
    }

    @Test
    void shouldThrowExceptionForInvalidValue(){
        assertThrows(IllegalArgumentException.class, () -> Sexo.fromValue(null));
        assertThrows(IllegalArgumentException.class, () -> Sexo.fromValue(""));
        assertThrows(IllegalArgumentException.class, () -> Sexo.fromValue("femia"));
        assertThrows(IllegalArgumentException.class, () -> Sexo.fromValue("maxo"));
    }
}