package com.example.endtoend.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
    String name;
    String gender;
    double genderProbability;
    int age;
    int ageCount;

    String country;
    int countryProbability;


    public ResponseDTO(Gender gender, Age age){
        this.name = gender.getName();
        this.gender = gender.getGender();
        this.genderProbability = gender.getProbability();
        this.age = age.getAge();
        this.ageCount = age.getAgeCount();

    }
}
