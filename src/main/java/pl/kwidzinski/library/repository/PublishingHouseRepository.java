package pl.kwidzinski.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kwidzinski.library.model.PublishingHouse;

@Repository
public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Long> {
}
