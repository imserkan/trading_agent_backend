package sislamoglu.in.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sislamoglu.in.model.Currency;

public interface TAServiceCurrencyRepository extends MongoRepository<Currency, String> {

    Currency findByName(String name);
}

