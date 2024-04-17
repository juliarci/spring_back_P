package monprojet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 
//

public interface CountryRepository extends JpaRepository<Country, Integer> {
    /**
     * Calculer la population totale d'un pays.
     * @param idDuPays l'identifiant du pays
     * @return sa population totale (en millions d'habitants)
     */
    // On peut mettre des "méthodes par défaut" dans les interfaces.
    default Integer populationDuPaysJava(int idDuPays) {
        int resultat = 0;
        Country country = findById(idDuPays).orElseThrow();
        for (City c : country.getCities()) {
            resultat += c.getPopulation();
        }
        return resultat;
        // Ou alors, en une seule ligne :
        // return country.getCities().stream().mapToInt(City::getPopulation).sum();
    }

    // JPQL : formulée sur le modèle conceptuel de données
    @Query("SELECT SUM(c.population) FROM City c WHERE c.country.id = :idDuPays")
    Integer populationDuPaysJPQL(int idDuPays);

    // SQL : formulée sur le modèle logique de données, il faut connaître la clé étrangère
    @Query(value = "SELECT SUM(c.population) FROM City c WHERE c.country_id = :idDuPays",
    nativeQuery = true)
    Integer populationDuPaysSQL(int idDuPays);

    // JPQL : formulée sur le modèle conceptuel de données, la jointure est implicite
    // Chaîne de caractères multi-ligne : on peut sauter des lignes et utiliser des espaces
    // https://www.geeksforgeeks.org/text-blocks-in-java-15/
    @Query("""
        SELECT c.country.name AS countryName, SUM(c.population) AS populationTotale
        FROM City c
        GROUP BY countryName
        """)
    List<PopulationResultProjection> populationParPaysJPQL();

    // SQL : formulée sur le modèle logique de données, il faut expliciter la jointure
    String POPULATION_PAR_PAYS_SQL = """
        SELECT Country.name AS countryName, SUM(population) AS populationTotale
        FROM Country
        INNER JOIN City ON country_id = Country.id
        GROUP BY countryName
        """;
    @Query(value = POPULATION_PAR_PAYS_SQL, nativeQuery = true)
    List<PopulationResultProjection> populationParPaysSQL();

}
