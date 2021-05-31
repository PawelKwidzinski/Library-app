package pl.kwidzinski.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kwidzinski.library.model.Author;
import pl.kwidzinski.library.service.AuthorService;

import java.util.List;

@Controller
@RequestMapping(path = "/author/")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(final AuthorService authorService) {
        this.authorService = authorService;
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
}
