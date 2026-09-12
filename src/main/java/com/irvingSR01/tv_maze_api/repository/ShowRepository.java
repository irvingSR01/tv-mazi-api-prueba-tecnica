package com.irvingSR01.tv_maze_api.repository;

import com.irvingSR01.tv_maze_api.document.ShowDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends MongoRepository<ShowDocument, Integer> {
}
