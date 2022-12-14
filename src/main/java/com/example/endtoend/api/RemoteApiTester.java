package com.example.endtoend.api;

import com.example.endtoend.dto.Age;
import com.example.endtoend.dto.Gender;
import com.example.endtoend.dto.Nation;
import com.example.endtoend.dto.ResponseDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/name-info")
public class RemoteApiTester implements CommandLineRunner {


    @GetMapping()
    public ResponseDTO getInfo(@RequestParam String name){
        Age age = getAgeFromName(name).block();
        Gender gender = getGenderForName(name).block();
        //Mono<Nation> nation = getNationFromName(name);
        ResponseDTO res = new ResponseDTO(gender, age);
        return res;
    }


    Mono<Gender> getGenderForName(String name) {
        WebClient client = WebClient.create();
        Mono<Gender> gender = client.get()
                .uri("https://api.genderize.io?name="+name)
                .retrieve()
                .bodyToMono(Gender.class);
        return gender;
    }

    Mono<Age> getAgeFromName(String name) {
        WebClient client = WebClient.create();
        Mono<Age> age = client.get()
                .uri("https://api.agify.io?name="+name)
                .retrieve()
                .bodyToMono(Age.class);
        return age;
    }

   Mono<Nation> getNationFromName(String name) {
        WebClient client = WebClient.create();
        Mono<Nation> nation = client.get()
                .uri("https://https://api.nationalize.io?name="+name)
                .retrieve()
                .bodyToMono(Nation.class);
        return nation;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println(getGenderForName("Mark").block().getGender());
        System.out.println(getAgeFromName("Mark").block().getAge());
        //System.out.println(getNationFromName("Mark").block().getName());

    }


}

