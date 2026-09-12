package com.irvingSR01.tv_maze_api.client;

import com.irvingSR01.tv_maze_api.model.TvMazeSearchResponse;
import com.irvingSR01.tv_maze_api.model.TvMazeShow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class TvMazeClientImpl implements TvMazeClient {

    private final RestTemplate restTemplate;

    @Value("${tvmaze.api.base-url}")
    private String baseUrl;

    @Override
    public TvMazeSearchResponse[] search(String query) {
        String url = baseUrl + "/search/shows?q=" + query;
        log.info("Calling TV Maze search url={}", url);
        return restTemplate.getForObject(url, TvMazeSearchResponse[].class);
    }

    @Override
    public TvMazeShow getShowById(Integer id) {
        String url = baseUrl + "/shows/" + id;
        log.info("Calling TV Maze show url={}", url);
        return restTemplate.getForObject(url, TvMazeShow.class);
    }
}