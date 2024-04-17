package monprojet.dao;

/**
 * Une "projection"" pour représenter 
 * le résultat d'une requête SQL.
 * Cette interface sera "automatiquement" implémentée par Spring.
 * @see monprojet.dao.CountryRepository
 */
public interface PopulationResultProjection {
    String getCountryName();
    Integer getPopulationTotale();
}
