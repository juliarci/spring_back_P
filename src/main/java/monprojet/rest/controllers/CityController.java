package monprojet.rest.controllers;

import monprojet.business.CityService;
import monprojet.dao.entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value="/city/findAll")
    @ResponseStatus(HttpStatus.OK)
    public List<City> findAll() {
        return cityService.findAll();
    }
}
