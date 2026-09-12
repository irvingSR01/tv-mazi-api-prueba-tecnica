package com.irvingSR01.tv_maze_api.controller;

import com.irvingSR01.tv_maze_api.model.CommentRequest;
import com.irvingSR01.tv_maze_api.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/shows/{id}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> addComment(
            @PathVariable("id") Integer showId,
            @Valid @RequestBody CommentRequest request
    ) {
        log.info("Adding comment for showId={}", showId);

        commentService.saveComment(showId, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}