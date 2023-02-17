package ua.profitsoft.hw8.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.profitsoft.hw8.data.Pep;

@Repository
public interface PepRepository extends MongoRepository<Pep, String>, CustomPepRepository {
}
