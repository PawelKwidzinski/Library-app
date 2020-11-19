package pl.kwidzinski.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private int yearWritten;

    @Formula(value = "(year(now() - year_written)")
    private int howOld;

    private int numberOfPages;
    private int numberOfAvailableCopies;

    @ManyToOne(fetch = FetchType.LAZY)
    private PublishingHouse publishingHouse;

    public Book(final String title, final int yearWritten, final int numberOfPages, final int numberOfAvailableCopies) {
        this.title = title;
        this.yearWritten = yearWritten;
        this.numberOfPages = numberOfPages;
        this.numberOfAvailableCopies = numberOfAvailableCopies;
    }
}
