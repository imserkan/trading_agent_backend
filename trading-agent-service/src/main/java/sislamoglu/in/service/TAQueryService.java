package sislamoglu.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import sislamoglu.in.model.Currency;
import sislamoglu.in.model.enums.ESortDirection;
import sislamoglu.in.model.paging.PagedDataResponse;
import sislamoglu.in.model.query.QueryCurrencyParams;

import java.util.List;

@Service
public class TAQueryService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Currency getCurrencyInformation(String id) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("id").is(id))
                , Currency.class);
    }

    public PagedDataResponse<Currency> queryCurrency(QueryCurrencyParams queryParams){
        Query query = new Query();
        if (queryParams.getCurrencyName() != null){
            query.addCriteria(Criteria.where("name").regex(queryParams.getCurrencyName(), "i"));
        }
        if (queryParams.getLastModified_startDate() != null){
            query.addCriteria(Criteria.where("lastModifiedAt").gte(queryParams.getLastModified_startDate()));
        }
        if (queryParams.getLastModified_endDate() != null){
            query.addCriteria(Criteria.where("lastModifiedAt").lte(queryParams.getLastModified_endDate()));
        }
        if (queryParams.getCreatedAt_startDate() != null){
            query.addCriteria(Criteria.where("createdAt").gte(queryParams.getCreatedAt_startDate()));
        }
        if (queryParams.getCreatedAt_endDate() != null){
            query.addCriteria(Criteria.where("createdAt").lte(queryParams.getCreatedAt_endDate()));
        }
        if (queryParams.getCreatedBy() != null){
            query.addCriteria(Criteria.where("createdBy").regex(queryParams.getCreatedBy()));
        }
        if (queryParams.getLastModifiedBy() != null){
            query.addCriteria(Criteria.where("lastModifiedBy").regex(queryParams.getLastModifiedBy()));
        }
        if (queryParams.getSort().getSortDirection() == ESortDirection.ASCENDING){
            query.with(new Sort(Sort.Direction.ASC, queryParams.getSort().getOrderBy()));
        }else{
            query.with(new Sort(Sort.Direction.DESC, queryParams.getSort().getOrderBy()));
        }
        query.with(PageRequest.of(queryParams.getPage().getPageNumber(), queryParams.getPage().getPageSize()));
        List<Currency> currencyList = mongoTemplate.find(query, Currency.class);
        Long currencyCount = mongoTemplate.count(query, Currency.class);
        PagedDataResponse pagedDataResponse = new PagedDataResponse();
        pagedDataResponse.setData(currencyList);
        pagedDataResponse.setPagedDataRequest(queryParams);
        pagedDataResponse.setTotalCount(currencyCount);
        return pagedDataResponse;
    }
}
