package sislamoglu.in.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sislamoglu.in.model.Currency;

@Repository
public interface CurrencyRepository extends MongoRepository<Currency, String> {

    Currency findByName(String name);
}
