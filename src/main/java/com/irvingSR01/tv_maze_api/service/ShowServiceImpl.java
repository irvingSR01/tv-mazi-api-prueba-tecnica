package com.irvingSR01.tv_maze_api.service;

import com.irvingSR01.tv_maze_api.model.ShowResponse;
import com.irvingSR01.tv_maze_api.model.TvMazeSearchResponse;
import com.irvingSR01.tv_maze_api.model.TvMazeShow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {
    private final RestTemplate restTemplate;

    @Value("${tvmaze.api.base-url}")
    private String baseUrl;

    @Override
    public List<ShowResponse> searchShows(String query) {
        String url = this.baseUrl + "/search/shows?q=" + query;
        log.info("Searching on url={}", url);

        TvMazeSearchResponse[] response = restTemplate.getForObject(url, TvMazeSearchResponse[].class);

        if (response == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(response)
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ShowResponse mapToResponse(TvMazeSearchResponse response) {
        TvMazeShow show = response.show();

        return new ShowResponse(
                show.id(),
                show.name(),
                resolveChannelName(show),
                show.summary(),
                show.genres()
        );
    }

    private String resolveChannelName(TvMazeShow show) {
        if (show.network() != null && show.network().name() != null) {
            return show.network().name();
        }

        if (show.webChannel() != null && show.webChannel().name() != null) {
            return show.webChannel().name();
        }

        return "N/A";
    }
}
