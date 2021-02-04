package pl.kwidzinski.library.model;

import lombok.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Long id;

    private String title;

    private int yearWritten;

    @Formula(value = "(year(now()) - year_written)")
    private int howOld;

    private int numberOfPages;
    private int numberOfAvailableCopies;

    @ManyToOne(fetch = FetchType.LAZY)
    private PublishingHouse publishingHouse;

    public Book(String title, int yearWritten, int numberOfPages, int numberOfAvailableCopies) {
        this.title = title;
        this.yearWritten = yearWritten;
        this.numberOfPages = numberOfPages;
        this.numberOfAvailableCopies = numberOfAvailableCopies;
    }
}
