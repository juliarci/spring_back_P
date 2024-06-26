package monprojet.dao;

import monprojet.business.CountryService;
import monprojet.dao.entities.Country;
import monprojet.dao.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryDAO;
    @Autowired
    private CountryService countryService;

    @Test
    void lesNomsDePaysSontTousDifferents() {
        log.info("On vérifie que les noms de pays sont tous différents ('unique') dans la table 'Country'");
        
        Country paysQuiExisteDeja = new Country("XX", "France");
        try {
            countryDAO.save(paysQuiExisteDeja); // On essaye d'enregistrer un pays dont le nom existe   

            fail("On doit avoir une violation de contrainte d'intégrité (unicité)");
        } catch (DataIntegrityViolationException e) {
            // Si on arrive ici c'est normal, l'exception attendue s'est produite
        }
    }

    @Test
    @Sql("test-data.sql") // On peut charger des données spécifiques pour un test
    void onSaitCompterLesEnregistrements() {
        log.info("On compte les enregistrements de la table 'Country'");
        int combienDePaysDansLeJeuDeTest = 3 + 1; // 3 dans data.sql, 1 dans test-data.sql
        long nombre = countryDAO.count();
        assertEquals(combienDePaysDansLeJeuDeTest, nombre, "On doit trouver 4 pays" );
    }

    @Test
    void calculPopulationJPQL() {
        log.info("On calcule la population totale d'un pays (JPQL)");
        int populationDuPays1 = 12; // cf. data.sql
        assertEquals(populationDuPays1, countryDAO.populationDuPaysJPQL(1));
    }

    @Test
    void calculPopulationSQL() {
        log.info("On calcule la population totale d'un pays (SQL)");
        int populationDuPays1 = 12; // cf. data.sql
        assertEquals(populationDuPays1, countryDAO.populationDuPaysSQL(1));
    }

    @Test
    void calculPopulationJava() {
        log.info("On calcule la population totale d'un pays (Java)");
        int populationDuPays1 = 12; // cf. data.sql
        assertEquals(populationDuPays1, countryService.populationDuPaysJava(1));
    }

    @Test
    void calculPopulationTotaleJPQL() {
        log.info("On calcule la population pour chaque pays (JPQL)");
        List<PopulationResultProjection> resultat = countryDAO.populationParPaysJPQL();
        assertEquals(countryDAO.count(), resultat.size(), "On doit avoir un résultat par pays");
        resultat.forEach(r -> log.info("Pays : {}  - Population totale : {}", r.getCountryName() , r.getPopulationTotale()));
    }

    @Test
    void calculPopulationTotaleSQL() {
        log.info("On calcule la population pour chaque pays (SQL)");
        List<PopulationResultProjection> resultat = countryDAO.populationParPaysSQL();
        assertEquals(countryDAO.count(), resultat.size(), "On doit avoir un résultat par pays");
        resultat.forEach(r -> log.info("Pays : {}  - Population totale : {}", r.getCountryName() , r.getPopulationTotale()));
    }

    @Test
    @Sql("test-data.sql") // Contient un pays sans ville
    void renvoieNullSiPasDeVille() {
        log.info("On vérifie que la population d'un pays sans ville est nulle");
        assertNull(countryDAO.populationDuPaysJPQL(4),
            "La requête renvoie null si le pays n'a pas de ville");
    }

}
