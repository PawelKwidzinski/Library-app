package pl.kwidzinski.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kwidzinski.library.model.Book;
import pl.kwidzinski.library.model.PublishingHouse;
import pl.kwidzinski.library.repository.BookRepository;
import pl.kwidzinski.library.repository.PublishingHouseRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final PublishingHouseRepository publishingHouseRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public void saveBook(Book book, Long publishingHouseId) {
        if (publishingHouseRepository.existsById(publishingHouseId)) {
            PublishingHouse ph = publishingHouseRepository.getOne(publishingHouseId);
            book.setPublishingHouse(ph);

            bookRepository.save(book);
        } else {
            throw new EntityNotFoundException("Publishing house not found.");
        }
    }

    public  Optional<Book> getById(final Long id) {
        return bookRepository.findById(id);
    }

    public void remove(final Long id) {
       bookRepository.deleteById(id);
    }
}
