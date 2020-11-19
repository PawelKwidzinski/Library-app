package pl.kwidzinski.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishingHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "publishingHouse", fetch = FetchType.LAZY)
    private Set<Book> books;

    public PublishingHouse(String name) {
        this.name = name;
    }
}
