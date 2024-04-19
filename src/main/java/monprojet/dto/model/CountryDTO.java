package monprojet.dto.model;

import lombok.Getter;
import lombok.Setter;
import monprojet.dao.entities.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Objet contenant des paramètres de l'entité nécessaires lors d'un transfert de donnée.
 * Il permet de limiter l'exposition de la structure de notre base de données
 */

@Getter
@Setter
public class CountryDTO {
    private int id;
    private String code;
    private String name;
    private String url;
    private List<CityDTO> cities;


}
