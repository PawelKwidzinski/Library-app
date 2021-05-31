package pl.kwidzinski.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.kwidzinski.library.model.Author;
import pl.kwidzinski.library.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
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
}
