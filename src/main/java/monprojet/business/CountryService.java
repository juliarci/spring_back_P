package monprojet.business;

import monprojet.dao.entities.City;
import monprojet.dao.entities.Country;
import monprojet.dao.repositories.CityRepository;
import monprojet.dao.repositories.CountryRepository;
import monprojet.dto.mappers.CityMapper;
import monprojet.dto.mappers.CountryMapper;
import monprojet.dto.model.CityDTO;
import monprojet.dto.model.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryService {

    // Deux options pour importer un Repository soit on le declare et on lui déclare un constructeur soit on utilise
    // l'annotation @Autowired
    // Option 1
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // Option 2
    // @Autowired
    // private final CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private CityMapper cityMapper;

    /**
     * Calculer la population totale d'un pays.
     *
     * @param idDuPays l'identifiant du pays
     * @return sa population totale (en millions d'habitants)
     */
    // On peut mettre des "méthodes par défaut" dans les interfaces.
    // Il vaut mieux créer les méthodes dans le service plutôt que dans le Repository qui lui intérroge plus
    // particulièrement la base de données
    public Integer populationDuPaysJava(int idDuPays) {
        int resultat = 0;
        Country country = countryRepository.findById(idDuPays).orElseThrow();
        for (City c : country.getCities()) {
            resultat += c.getPopulation();
        }
        return resultat;
        // Ou alors, en une seule ligne :
        // return country.getCities().stream().mapToInt(City::getPopulation).sum();
    }

    /**
     * Methode récupérant l'ensemble des pays présents en base de donnée en les transformant en objet DTO
     * @return Liste de pays en DTO à transférer au front
     */
    public List<CountryDTO> getAllCountries() {
        // On récupère l'ensemble des pays de la base de donnée
        List<Country> countries = countryRepository.findAll();
        // Transforme les pays en DTO puis récupères les villes associées au pays, les tranforme également en DTO et
        // les ajoute au pays
        return countries.stream()
                .map(country -> {
                    CountryDTO countryDTO = countryMapper.toCountryDTO(country);
                    List<CityDTO> cityDTOs = cityMapper.cityListToCityDTOList(cityRepository.findAllByCountry_Id(country.getId()));
                    countryDTO.setCities(cityDTOs);
                    return countryDTO;
                })
                .toList();
    }
}
