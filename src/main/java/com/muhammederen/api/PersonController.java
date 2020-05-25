package com.muhammederen.api;

import com.muhammederen.entity.Person;
import com.muhammederen.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    @PostConstruct
    public void init() {
        Person person = new Person();
        person.setId("P001");
        person.setName("Muhammed Eren");
        person.setSurname("DEMİR");
        person.setAddress("Sivas");
        person.setDateOfBirth(Calendar.getInstance().getTime());
        personRepository.save(person);
    }

    @GetMapping("/{search}")
    public ResponseEntity<List<Person>> getPerson(@PathVariable String search) {
        List<Person> persons = personRepository.getByCustomQuery(search);
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/fullName/{search}")
    public ResponseEntity<List<Person>> getPersonFull(@PathVariable String search) {
        List<Person> persons = personRepository.findByNameLikeOrSurnameLike(search, search);
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/surname/{search}")
    public ResponseEntity<List<Person>> getPersonSurname(@PathVariable String search) {
        List<Person> persons = personRepository.findBySurnameLike(search);
        return ResponseEntity.ok(persons);
    }


    @GetMapping("/address/{search}")
    public ResponseEntity<List<Person>> getPersonAddress(@PathVariable String search) {
        List<Person> persons = personRepository.findByAddressLike(search);
        return ResponseEntity.ok(persons);
    }
}
