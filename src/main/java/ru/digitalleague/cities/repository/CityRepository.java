package ru.digitalleague.cities.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.digitalleague.cities.model.City;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    @Override
    City save(City city);
    @Override
    Iterable<City> findAll();
    Optional<City> findByCityName(String name);
    List<City> findCitiesByRegion(String region);
    List<City> findCitiesByCountry(String country);
}
