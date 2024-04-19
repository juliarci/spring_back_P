package monprojet.dao.repositories;

import monprojet.dao.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    /**
     * Trouve une ville par son nom.
     * Cette méthode sera automatiquement implémentée par Spring Data JPA.
     * @param cityName
     * @return La ville correspondant au nom fourni, ou null si pas trouvé.
     */
    City findByName(String cityName);

    /**
     * Récupère la liste des villes associées au pays
     * @param country_id Id du pays concerné
     * @return List des villes associées
     */
    List<City> findAllByCountry_Id(Integer country_id);
}
