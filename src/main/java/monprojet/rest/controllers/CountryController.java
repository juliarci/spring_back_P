package monprojet.rest.controllers;

import monprojet.business.CountryService;
import monprojet.dto.model.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    //Adresse du front
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value="/country/getPopulation/{idPays}")
    @ResponseStatus(HttpStatus.OK)
    //Path Variable correspond au nom du paramètre ajouté au chemin
    public Integer getCountryPopulationNumber(@PathVariable(value = "idPays") int idDuPays) {
        return countryService.populationDuPaysJava(idDuPays);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value="/country/getAllCountries")
    @ResponseStatus(HttpStatus.OK)
    public List<CountryDTO> getAllCountries(){
        return countryService.getAllCountries();
    }
}
