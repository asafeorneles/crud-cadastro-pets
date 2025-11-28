package com.asafeorneles.cadastro_pets_crud.dtos;

import com.asafeorneles.cadastro_pets_crud.enums.Sexo;
import com.asafeorneles.cadastro_pets_crud.enums.Tipo;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record PetRecordDto(@NotNull (message = "O tipo do animal precisa ser inserido!")
                           Tipo type,
                           @NotNull (message = "O sexo do animal precisa ser inserido!")
                           Sexo sexo,
                           @NotBlank(message = "O nome não pode estar vazio")
                           @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "O nome deve conter apenas letras")
                           String name,
                           @NotBlank(message = "O sobrenome não pode estar vazio")
                           @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "O sobrenome deve conter apenas letras")
                           String lastName,
                           @DecimalMin(value = "0.1", message = "A idade mínima é 0,1 anos!")
                           @DecimalMax(value = "20.0", message = "A idade máxima é 20 anos! ")
                           BigDecimal age,
                           @DecimalMin(value = "0.5", message = "O peso mínima é 0,5kg!")
                           @DecimalMax(value = "60.0", message = "O peso máxima é 20kg! ")
                           BigDecimal weight,
                           @Pattern(regexp = "^[A-Za-zÀ-ÿ]+(?:[\\s-][A-Za-zÀ-ÿ]+)*$", message = "A raça deve conter apenas letras, espaços ou hífen")
                           String race,
                           @Pattern(regexp = "^[A-Za-zÀ-ÿ0-9\\s]+$", message = "A rua deve conter apenas letras, números e espaços")
                           String street,
                           @Pattern(regexp = "^[0-9]+[A-Za-z0-9\\-]*$", message = "O número deve conter apenas números e letras opcionais (ex: 120, 120A, 45-B)")
                           String number,
                           @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+(?:-[A-Za-zÀ-ÿ ]+)*$", message = "O bairro deve conter apenas letras e hífen")
                           String neighborhood,
                           @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+(?:-[A-Za-zÀ-ÿ ]+)*$", message = "O bairro deve conter apenas letras e hífen")
                           String city){

}
