package com.irvingSR01.tv_maze_api.service;

import com.irvingSR01.tv_maze_api.client.TvMazeClient;
import com.irvingSR01.tv_maze_api.model.ShowResponse;
import com.irvingSR01.tv_maze_api.model.TvMazeSearchResponse;
import com.irvingSR01.tv_maze_api.model.TvMazeShow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final TvMazeClient tvMazeClient;

    @Override
    public List<ShowResponse> searchShows(String query) {
        log.info("Searching shows with query={}", query);

        TvMazeSearchResponse[] response = tvMazeClient.search(query);

        if (response == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(response)
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TvMazeShow getShowById(Integer id) {
        log.info("Fetching show by id={}", id);
        return tvMazeClient.getShowById(id);
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