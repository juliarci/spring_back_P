package monprojet.dto.mappers;

import monprojet.dao.entities.Country;
import monprojet.dto.model.CountryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses ={CityMapper.class})
public interface CountryMapper {

    /**
     * Méthode générée automatiquement permettant de transformer une CountryEntity en CountryDTO
     * @param country Ville à transformer en objet DTO
     * @return CountryDTO, objet à envoyer au front
     */
    //Mapping ignore car on définira manuellement l'assocaition entre Countryet cities
    @Mapping(source = "url", target = "url")
    @Mapping(target = "cities", ignore = true)
    CountryDTO toCountryDTO(Country country);
}
