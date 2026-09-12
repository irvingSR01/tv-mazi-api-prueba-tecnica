package com.irvingSR01.tv_maze_api.repository;

import com.irvingSR01.tv_maze_api.document.CommentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<CommentDocument, String> {
}