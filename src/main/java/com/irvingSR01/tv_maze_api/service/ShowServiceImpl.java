package com.irvingSR01.tv_maze_api.service;

import com.irvingSR01.tv_maze_api.client.TvMazeClient;
import com.irvingSR01.tv_maze_api.document.CommentDocument;
import com.irvingSR01.tv_maze_api.document.ShowDocument;
import com.irvingSR01.tv_maze_api.mapper.ShowMapper;
import com.irvingSR01.tv_maze_api.model.*;
import com.irvingSR01.tv_maze_api.repository.CommentRepository;
import com.irvingSR01.tv_maze_api.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
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
    private final ShowRepository showRepository;
    private final CommentRepository commentRepository;

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
    public ShowDetailResponse getShowById(Integer id) {
        TvMazeShow show = showRepository.findById(id)
                .map(document -> {
                    log.info("Show id={} found in cache", id);
                    return ShowMapper.toApiModel(document);
                })
                .orElseGet(() -> fetchAndCacheShow(id));

        return buildDetailResponse(show);
    }

    private ShowDetailResponse buildDetailResponse(TvMazeShow show) {
        return new ShowDetailResponse(
                show.id(),
                show.name(),
                show.type(),
                show.genres(),
                show.status(),
                show.runtime(),
                show.premiered(),
                show.ended(),
                show.summary(),
                show.rating(),
                show.image(),
                show.network(),
                show.webChannel(),
                findCommentsForShow(show.id())
        );
    }

    private TvMazeShow fetchAndCacheShow(Integer id) {
        log.info("Show id={} not in cache, fetching from TV Maze API", id);

        TvMazeShow show = tvMazeClient.getShowById(id);

        try {
            showRepository.save(ShowMapper.toDocument(show));
        } catch (DuplicateKeyException e) {
            log.warn("Show id={} was cached by a concurrent request, ignoring duplicate save", id);
        } catch (DataAccessException e) {
            log.error("Could not cache show id={}: {}", id, e.getMessage());
        }

        return show;
    }

    private ShowResponse mapToResponse(TvMazeSearchResponse response) {
        TvMazeShow show = response.show();

        return new ShowResponse(
                show.id(),
                show.name(),
                resolveChannelName(show),
                show.summary(),
                show.genres(),
                findCommentsForShow(show.id())
        );
    }

    private List<CommentResponse> findCommentsForShow(Integer showId) {
        try {
            return commentRepository.findByShowId(showId).stream()
                    .map(this::mapToCommentResponse)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            log.error("Could not fetch comments for showId={}: {}", showId, e.getMessage());
            return Collections.emptyList();
        }
    }

    private CommentResponse mapToCommentResponse(CommentDocument document) {
        return new CommentResponse(document.comment(), document.rating());
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