package pl.kwidzinski.library.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kwidzinski.library.model.PublishingHouse;
import pl.kwidzinski.library.repository.PublishingHouseRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublishingHouseService {

    private final PublishingHouseRepository publishingHouseRepository;

    public List<PublishingHouse> findAll() {
        return publishingHouseRepository.findAll();
    }

    public void add(final PublishingHouse house) {
        publishingHouseRepository.save(house);
    }

    public Optional<PublishingHouse> getById(Long id) {
        return publishingHouseRepository.findById(id);
    }

    public void remove(Long id) {
        if (publishingHouseRepository.existsById(id)) {
            PublishingHouse publishingHouse = publishingHouseRepository.getOne(id);
            if (publishingHouse.getBooks().size() == 0) {
                publishingHouseRepository.deleteById(id);
            } else {
                System.err.println("Unable to remove publishing house with assigned books to it.");
            }
        }
    }
}

