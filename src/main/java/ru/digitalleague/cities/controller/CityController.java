package ru.digitalleague.cities.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.cities.exception.AlreadyExistingCityException;
import ru.digitalleague.cities.exception.NonExistingCountryException;
import ru.digitalleague.cities.exception.NonExistingRegionException;
import ru.digitalleague.cities.model.City;
import ru.digitalleague.cities.service.CityService;

import java.util.List;

@RestController
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/city")
    public ResponseEntity<String> addCity(@RequestBody City city) {
        try {
            cityService.save(city);
            return new ResponseEntity<>("Город успешно добавлен!", HttpStatus.OK);
        } catch (AlreadyExistingCityException e) {
            return new ResponseEntity<>("Такой город уже добавлен!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/citiesByRegion")
    public ResponseEntity<List<City>> findCitiesByRegion(String region) {
        try {
            return new ResponseEntity<>(cityService.findCitiesByRegion(region), HttpStatus.OK);
        } catch (NonExistingRegionException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/citiesByCountry")
    public ResponseEntity<List<City>> findCitiesByCountry(String country) {
        try {
            return new ResponseEntity<>(cityService.findCitiesByCountry(country), HttpStatus.OK);
        } catch (NonExistingCountryException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/morePopulation")
    public ResponseEntity<List<City>> findCitiesWithPopulationMoreThan(long population) {
        return new ResponseEntity<>(cityService.findCitiesWithPopulationMoreThan(population), HttpStatus.OK);
    }

    @GetMapping("/lessPopulation")
    public ResponseEntity<List<City>> findCitiesWithPopulationLessThan(long population) {
        return new ResponseEntity<>(cityService.findCitiesWithPopulationLessThan(population), HttpStatus.OK);
    }
}
