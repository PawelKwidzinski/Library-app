package pl.kwidzinski.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kwidzinski.library.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/book/")
public class BookController {

//    @GetMapping("/list")
//    public String list(Model model){
//        List<Book> bookList = new ArrayList<>(Arrays.asList(
//                new Book("a"),
//                new Book("a"),
//                new Book("a"),
//                new Book("a"),
//                new Book("a"),
//        ));
//
//    }
}
