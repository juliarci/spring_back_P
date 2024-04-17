package monprojet.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Country {
    // Identifiant technique
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    // Identifiant métier (code ISO)
    @Column(unique=true)
    @NonNull
    private String code;
    
    @Column(unique=true)
    @NonNull
    private String name;

    @OneToMany(mappedBy="country")
    // Essayer sans "mappedBy" pour voir le schéma relationnel généré
    // @OneToMany
    // Lombok https://www.projectlombok.org/features/ToString
    @ToString.Exclude // On ne veut pas inclure la liste des villes dans le toString
    // Sinon récursivité infinie    
    private List<City> cities = new ArrayList<>();
}
