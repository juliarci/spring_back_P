package monprojet.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**     
 * Configuration de l'API REST
 * Expose les IDs des entités dans l'API REST. Par défaut, Spring Data REST ne renvoie pas les identifiants des entités dans les réponses.
 * Autorise les requêtes CORS. Par défaut, Spring Data REST ne permet pas les requêtes CORS.
 */
@Component
public class SpringDataRestConfig
        implements RepositoryRestConfigurer {
  @Autowired
  private EntityManager entityManager;

  @Override
  public void configureRepositoryRestConfiguration(
          RepositoryRestConfiguration config, CorsRegistry cors) {
    // Expose les id de toutes les entités dans l'API REST
    config
            .exposeIdsFor(entityManager
                    .getMetamodel()
                    .getEntities()
                    .stream()
                    .map(Type::getJavaType)
                    .toArray(Class[]::new)
            );
    // Autorise les requêtes CORS
    cors.addMapping("/**") // Toutes les adresses sont autorisées
            .allowedOrigins("*") // Toutes les origines sont autorisées
            .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE") // Toutes les méthodes http sont autorisées
            .allowCredentials(false) // Pas de cookies
            .maxAge(3600); // Durée de la réponse en secondes
  }
}