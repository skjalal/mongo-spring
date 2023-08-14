package com.example.repository;

import com.example.domain.PaginatedResult;
import com.example.domain.Restaurant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, ObjectId> {

    @Aggregation(
        pipeline = {
            "{ $facet: {" +
                "data: [" +
                    "{ $match: { ?2: {$regex: '.*?3.*', $options: 'i'} } }," +
                    "{ $skip: ?0 }," +
                    "{ $limit: ?1 }," +
                    "{ $sort: { ?4: ?5 } }" +
                "]," +
                "total: [" +
                    "{ $match: { ?2: {$regex: '.*?3.*', $options: 'i'} } }," +
                    "{ $count: count }" +
                "]}" +
            "}",
            "{ $project: { data: 1, count: { $first: '$total.count' } } }"
    })
    PaginatedResult findAllRestaurants(int offset, int limit, String searchField, String search, String sort, int order);

    @Aggregation(
        pipeline = {
            "{ $facet: {" +
                    "data: [" +
                        "{ $skip: ?0 }," +
                        "{ $limit: ?1 }," +
                        "{ $sort: { ?2: ?3 } }" +
                    "]," +
                    "total: [" +
                        "{ $count: count }" +
                    "]" +
                "}" +
            "}",
            "{ $project: { data: 1, count: { $first: '$total.count' } } }"
        })
    PaginatedResult findAllRestaurants(int offset, int limit, String sort, int order);
}
