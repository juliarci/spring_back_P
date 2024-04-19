package monprojet.dto.mappers;

import monprojet.dao.entities.City;
import monprojet.dto.model.CityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    /**
     * Méthode générée automatiquement permettant de transformer une CityEntity en CityDTO
     * @param city Ville à transformer en objet DTO
     * @return CityDTO, objet à envoyer au front
     */
    CityDTO cityToCityDTO(City city);

    /**
     * Méthode générée automatiquement permettant de transformer une list de City en CityDTO
     * @param cities Les villes à transformer en objet DTO
     * @return List d'objet à transférer au front
     */
    List<CityDTO> cityListToCityDTOList(List<City> cities);
}
