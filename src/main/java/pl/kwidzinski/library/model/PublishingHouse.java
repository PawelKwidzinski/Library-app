package pl.kwidzinski.library.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishingHouse {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
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
