package pl.kwidzinski.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kwidzinski.library.model.PublishingHouse;
import pl.kwidzinski.library.service.PublishingHouseService;

import java.util.*;

@Controller
@RequestMapping(path = "/ph/")
public class PublishingHouseController {

    private final PublishingHouseService publishingHouseService;

    @Autowired
    PublishingHouseController(final PublishingHouseService publishingHouseService) {
        this.publishingHouseService = publishingHouseService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<PublishingHouse> publishingHouses = publishingHouseService.findAll();
        model.addAttribute("phouses", publishingHouses);

        return "ph-list";
    }

    @GetMapping("/add")
    public String add(Model model, PublishingHouse publishingHouse) {
        model.addAttribute("publishingHouse", publishingHouse);
        return "ph-form";
    }

    @PostMapping("/add")
    public String add(PublishingHouse house) {
        publishingHouseService.add(house);
        return "redirect:/ph/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model,
                       @PathVariable(name = "id") Long id) {
        Optional<PublishingHouse> publishingHouseOptional = publishingHouseService.getById(id);
        if (publishingHouseOptional.isPresent()) {
            model.addAttribute("publishingHouse", publishingHouseOptional.get());
            return "ph-form";
        }
        return "redirect:/ph/list";
    }

    @GetMapping("/remove/{id}")
    public String add(@PathVariable(name = "id") Long id) {
        publishingHouseService.remove(id);
        return "redirect:/ph/list";
    }

    @GetMapping("/books/{id}")
    public String getPHBooks(Model model, @PathVariable(name = "id") Long id) {
        final Optional<PublishingHouse> publishingHouseOptional = publishingHouseService.getById(id);
        if (publishingHouseOptional.isPresent()){
            PublishingHouse publishingHouse = publishingHouseOptional.get();
            model.addAttribute("books", publishingHouse.getBooks());
            return "book-list";
        }
        return "redirect:/ph/list";
    }

}
