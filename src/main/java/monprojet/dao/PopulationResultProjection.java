package monprojet.dao;

import monprojet.dao.repositories.CountryRepository;

/**
 * Une "projection"" pour représenter 
 * le résultat d'une requête SQL.
 * Cette interface sera "automatiquement" implémentée par Spring.
 * @see CountryRepository
 */
public interface PopulationResultProjection {
    String getCountryName();
    Integer getPopulationTotale();
}
