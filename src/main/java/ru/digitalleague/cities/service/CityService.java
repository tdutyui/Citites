package ru.digitalleague.cities.service;

import org.springframework.stereotype.Service;
import ru.digitalleague.cities.exception.AlreadyExistingCityException;
import ru.digitalleague.cities.exception.NonExistingCountryException;
import ru.digitalleague.cities.exception.NonExistingRegionException;
import ru.digitalleague.cities.model.City;
import ru.digitalleague.cities.repository.CityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void save(City city) throws AlreadyExistingCityException {
        if (this.findByName(city.getCityName()).isEmpty()) {
            cityRepository.save(city);
        }
        throw new AlreadyExistingCityException();
    }

    public List<City> findCitiesByRegion(String region) throws NonExistingRegionException {
        if (!cityRepository.findCitiesByRegion(region).isEmpty()) {
            return cityRepository.findCitiesByRegion(region);
        }
        throw new NonExistingRegionException();
    }

    public List<City> findCitiesByCountry(String country) throws NonExistingCountryException {
        if (!cityRepository.findCitiesByCountry(country).isEmpty()) {
            return cityRepository.findCitiesByCountry(country);
        }
        throw new NonExistingCountryException();
    }

    public List<City> findCitiesWithPopulationMoreThan(long population) {
        return this.findAll().stream()
                .filter(city -> city.getPopulation() > population)
                .collect(Collectors.toList());
    }

    public List<City> findCitiesWithPopulationLessThan(long population) {
        return this.findAll().stream()
                .filter(city -> city.getPopulation() < population)
                .collect(Collectors.toList());
    }

    private Optional<City> findByName(String name) {
        return cityRepository.findByCityName(name);
    }

    private List<City> findAll() {
        return (List<City>) cityRepository.findAll();
    }
}
