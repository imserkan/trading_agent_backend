package sislamoglu.in.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sislamoglu.in.model.ta4j.CurrencyTimeSeries;

@Repository
public interface CurrencyTimeSeriesRepository extends MongoRepository<CurrencyTimeSeries, String> {
}
