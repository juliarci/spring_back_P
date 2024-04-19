package monprojet.dao.repositories;

import java.util.List;

import lombok.NonNull;
import monprojet.dao.PopulationResultProjection;
import monprojet.dao.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring 
//

public interface CountryRepository extends JpaRepository<Country, Integer> {

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

    @Override
    @NonNull
    List<Country> findAll();
}
