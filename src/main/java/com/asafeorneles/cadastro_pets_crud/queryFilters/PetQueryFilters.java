package com.asafeorneles.cadastro_pets_crud.queryFilters;

import com.asafeorneles.cadastro_pets_crud.entities.Pet;

import static com.asafeorneles.cadastro_pets_crud.specifications.PetSpecification.*;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

@Data
public class PetQueryFilters {
    private String name;
    private String type;
    private String sex;
    private String lastName;
    private BigDecimal age;
    private BigDecimal weight;
    private BigDecimal weightGte;
    private BigDecimal weightLte;
    private String race;
    private String street;
    private String number;
    private String neighborhood;
    private String city;

    public Specification<Pet> toSpecification() {
        return nameContains(name)
                .and(haveType(type))
                .and(haveSex(sex))
                .and(lastNameContains(lastName))
                .and(equalsAge(age))
                .and(equalsWeight(weight))
                .and(weightGreaterThanOrEqualTo(weightGte))
                .and(weightLessThanOrEqualTo(weightLte))
                .and(equalsRace(race))
                .and(streetContains(street))
                .and(equalsNumber(number))
                .and(neighborhoodContains(neighborhood))
                .and(cityContains(city));
    }
}
