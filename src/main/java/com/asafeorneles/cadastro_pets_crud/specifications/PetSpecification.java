package com.asafeorneles.cadastro_pets_crud.specifications;

import com.asafeorneles.cadastro_pets_crud.entities.Pet;
import com.asafeorneles.cadastro_pets_crud.enums.Sexo;
import com.asafeorneles.cadastro_pets_crud.enums.Tipo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

public class PetSpecification {
    public static Specification<Pet> haveType(String type){
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(type)){
                return null;
            }
            Tipo tipo = Tipo.fromValue(type);
            return builder.equal(root.get("type"), tipo.getTIPO());
        };
    }

    public static Specification<Pet> haveSex(String sex){
        return (root, query, builder)->{
            if (ObjectUtils.isEmpty(sex)){
                return null;
            }
            return builder.equal(root.get("sexo"), Sexo.fromValue(sex));
        };
    }

    public static Specification<Pet> nameContains(String name) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(name)) {
                return null;
            }
            return builder.like(root.get("name"), "%" + name + "%");
        };
    }

    public static Specification<Pet> lastNameContains(String lastName) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(lastName)) {
                return null;
            }
            return builder.like(root.get("lastName"), "%" + lastName + "%");
        };
    }

    public static Specification<Pet> equalsAge(BigDecimal age) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(age)) {
                return null;
            }
            return builder.equal(root.get("age"), age);
        };
    }

    public static Specification<Pet> equalsWeight(BigDecimal weight) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(weight)) {
                return null;
            }
            return builder.equal(root.get("weight"), weight);
        };
    }

    public static Specification<Pet> weightGreaterThanOrEqualTo(BigDecimal weight) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(weight)){
                return null;
            }
            return builder.greaterThanOrEqualTo(root.get("weight"), weight);
        };
    }

    public static Specification<Pet> weightLessThanOrEqualTo(BigDecimal weight) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(weight)){
                return null;
            }
            return builder.lessThanOrEqualTo(root.get("weight"), weight);
        };
    }

    public static Specification<Pet> equalsRace(String race) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(race)) {
                return null;
            }
            return builder.equal(root.get("race"), race);
        };
    }

    public static Specification<Pet> streetContains(String street) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(street)) {
                return null;
            }
            return builder.like(root.get("street"), "%" + street + "%");
        };
    }

    public static Specification<Pet> equalsNumber(String number) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(number)) {
                return null;
            }
            return builder.equal(root.get("number"), number);
        };
    }

    public static Specification<Pet> neighborhoodContains(String neighborhood) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(neighborhood)) {
                return null;
            }
            return builder.like(root.get("neighborhood"), "%" + neighborhood + "%");
        };
    }

    public static Specification<Pet> cityContains(String city) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(city)) {
                return null;
            }
            return builder.like(root.get("city"), "%" + city + "%");
        };
    }

}
