package pl.kwidzinski.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kwidzinski.library.model.Author;
import pl.kwidzinski.library.model.Book;
import pl.kwidzinski.library.service.AuthorService;
import pl.kwidzinski.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/author/")
public class AuthorController {

    private AuthorService authorService;
    private BookService bookService;

    @Autowired
    public AuthorController(final AuthorService authorService, final BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String listAuthors(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Author> authorPage = authorService.getPage(PageRequest.of(page, size));
        model.addAttribute("authors", authorPage);
        return "author-list";
    }

    @GetMapping("/add")
    public String add(Model model, Author author) {
        model.addAttribute("author", author);
        return "author-form";
    }

    @PostMapping("/add")
    public String add(Author author) {
        authorService.saveAuthor(author);
        return "redirect:/author/list";
    }

    @GetMapping("/books/{id}")
    public String addAuthorsToBooks(Model model, @PathVariable("id") Long authorId) {
        Optional<Author> authorOptional = authorService.getAuthor(authorId);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            List<Book> books = bookService.getAllBooks();
            model.addAttribute("author", author);
            model.addAttribute("books", books);
            return "author-bookform";
        }
        return "redirect:/author/list";
    }

    @PostMapping("/addBook")
    public String addBookToAuthor(Long authorId, Long bookId, HttpServletRequest request) {
        authorService.addBookToAuthor(authorId, bookId);
        return "redirect:" + request.getHeader("referer");
    }

    @GetMapping("/book/remove/{bookId}/{authorId}")
    public String removeBookFromAuthor(
            @PathVariable("bookId") Long bookId,
            @PathVariable("authorId") Long authorId,
            HttpServletRequest request) {
        authorService.removeFromAuthor(bookId, authorId);
        return "redirect:" + request.getHeader("referer");
    }
}
