package com.irvingSR01.tv_maze_api.controller;

import com.irvingSR01.tv_maze_api.model.ShowDetailResponse;
import com.irvingSR01.tv_maze_api.model.ShowResponse;
import com.irvingSR01.tv_maze_api.model.TvMazeShow;
import com.irvingSR01.tv_maze_api.service.ShowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @GetMapping("/search")
    public ResponseEntity<List<ShowResponse>> searchShows(
            @RequestParam("search_query") String searchQuery
    ) {
        log.info("search shows query: {}", searchQuery);
        List<ShowResponse> shows = showService.searchShows(searchQuery);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowDetailResponse> getShowById(
            @PathVariable("id") Integer id
    ) {
        log.info("Requesting get show by id {}", id);
        ShowDetailResponse show = showService.getShowById(id);
        return ResponseEntity.ok(show);
    }
}