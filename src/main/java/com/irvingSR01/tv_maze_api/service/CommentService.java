package com.irvingSR01.tv_maze_api.service;

import com.irvingSR01.tv_maze_api.model.CommentRequest;

public interface CommentService {
    void saveComment(Integer showId, CommentRequest request);
}