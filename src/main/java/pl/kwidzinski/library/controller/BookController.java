package pl.kwidzinski.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kwidzinski.library.model.Book;
import pl.kwidzinski.library.service.BookService;
import pl.kwidzinski.library.service.PublishingHouseService;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Optional;


@Controller
@AllArgsConstructor
@RequestMapping(path = "/book/")
public class BookController {

    private final BookService bookService;
    private final PublishingHouseService publishingHouseService;

    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "book-list";
    }


    @GetMapping("/add")
    public String getForm(Model model, Book book) {
        book.setYearWritten(LocalDate.now().getYear());

        model.addAttribute("publishingHouses", publishingHouseService.findAll());
        model.addAttribute("book", book);
        return "book-form";
    }

    @PostMapping("/add")
    // nazwa 'publishingHouseId' pochodzi z formularza book-form th:name="publishingHouseId
    public String addBook(Book book, Long publishingHouseId) {
        bookService.saveBook(book, publishingHouseId);
        return "redirect:/book/list";
    }

    @GetMapping("/edit/{id}")
    public String getForm(Model model,
                          @PathVariable(name = "id") Long id) {
        final Optional<Book> optionalBook = bookService.getById(id);
        if (optionalBook.isPresent()) {
            model.addAttribute("publishingHouses", publishingHouseService.findAll());
            model.addAttribute("book", optionalBook.get());
            return "book-form";
        }
        return "redirect:/book/list";
    }

    @GetMapping("/remove/{id}")
    public String remove(HttpServletRequest request,
                         @PathVariable(name = "id") Long id) {
        String referer = request.getHeader("referer");
        bookService.remove(id);
        if (referer != null) {
            return "redirect:" + referer;
        }
        return "redirect:/book/list";
    }

    @GetMapping("/details/{id}")
    public String details(Model model,
                          HttpServletRequest request,
                          @PathVariable (name = "id") Long id) {
        final Optional<Book> optionalBook = bookService.getById(id);
        if (optionalBook.isPresent()){
            model.addAttribute("book", optionalBook.get());
            model.addAttribute("referer", request.getHeader("referer")); // do przycisku back
            return "book-details";
        }
        return "redirect:/book/list";
    }

}
