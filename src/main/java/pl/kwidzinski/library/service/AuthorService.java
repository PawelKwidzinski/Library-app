package pl.kwidzinski.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.kwidzinski.library.model.Author;
import pl.kwidzinski.library.model.Book;
import pl.kwidzinski.library.repository.AuthorRepository;
import pl.kwidzinski.library.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public AuthorService(final AuthorRepository authorRepository, final BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    public Page<Author> getPage(final PageRequest request) {
        return authorRepository.findAll(request);
    }

    public Optional<Author> getAuthor(Long authorId) {
        return authorRepository.findById(authorId);
    }

    public void addBookToAuthor(final Long authorId, final Long bookId) {
        if (!authorRepository.existsById(authorId)) {
            return;
        }
        // getOne nie pobiera podelemnetów autora, ustawia relacje
        Author author = authorRepository.getOne(authorId);
        if (!bookRepository.existsById(bookId)) {
            return;
        }
        Book book = bookRepository.getOne(bookId);

        //po stronie book jest "mappedBy" books - takie jest wskazanie relacji ManyToMany, więc autorowi trzeba dodać ksiażkę
        author.getBooks().add(book);
        authorRepository.save(author);
    }

    public void removeFromAuthor(final Long bookId, final Long authorId) {
        if (!authorRepository.existsById(authorId)) {
            return;
        }
        Author author = authorRepository.getOne(authorId);
        if (!bookRepository.existsById(bookId)) {
            return;
        }
        Book book = bookRepository.getOne(bookId);
        author.getBooks().remove(book);
        authorRepository.save(author);
    }
}
