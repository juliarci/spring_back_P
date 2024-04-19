package monprojet.business;

import monprojet.dao.entities.City;
import monprojet.dao.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityService {

    @Autowired
    private CityRepository cityRepo;

    public List<City> findAll() {
        return cityRepo.findAll();
    }
}
