package com.irvingSR01.tv_maze_api.service;

import com.irvingSR01.tv_maze_api.document.CommentDocument;
import com.irvingSR01.tv_maze_api.model.CommentRequest;
import com.irvingSR01.tv_maze_api.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void saveComment(Integer showId, CommentRequest request) {
        log.info("Saving comment for showId={}", showId);

        CommentDocument document = new CommentDocument(
                null,
                showId,
                request.comment(),
                request.rating()
        );

        commentRepository.save(document);
    }
}