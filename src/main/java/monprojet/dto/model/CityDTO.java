package monprojet.dto.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Objet contenant des paramètres de l'entité nécessaires lors d'un transfert de donnée.
 * Il permet de limiter l'exposition de la structure de notre base de données
 */

@Getter
@Setter
public class CityDTO {
    private int id;
    private String name;
    private int population;
}
