package com.irvingSR01.tv_maze_api.service;

import com.irvingSR01.tv_maze_api.client.TvMazeClient;
import com.irvingSR01.tv_maze_api.document.ShowDocument;
import com.irvingSR01.tv_maze_api.mapper.ShowMapper;
import com.irvingSR01.tv_maze_api.model.*;
import com.irvingSR01.tv_maze_api.repository.CommentRepository;
import com.irvingSR01.tv_maze_api.repository.ShowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShowServiceImplTest {

    @Mock
    private TvMazeClient tvMazeClient;

    @Mock
    private ShowRepository showRepository;

    @Mock
    private CommentRepository commentRepository;

    private ShowServiceImpl showService;

    private TvMazeShow sampleShow() {
        return new TvMazeShow(
                143,
                "Breaking Bad",
                "Scripted",
                List.of("Drama", "Crime"),
                "Ended",
                47,
                "2008-01-20",
                "2013-09-29",
                "A chemistry teacher turns to crime.",
                new Rating(9.4),
                new ImageInfo("medium.jpg", "original.jpg"),
                new Channel("AMC"),
                null
        );
    }

    @Test
    void getShowById_whenShowIsCached_returnsFromCacheAndNeverCallsApi() {
        showService = new ShowServiceImpl(tvMazeClient, showRepository, commentRepository);

        ShowDocument cachedDocument = ShowMapper.toDocument(sampleShow());
        when(showRepository.findById(143)).thenReturn(Optional.of(cachedDocument));
        when(commentRepository.findByShowId(143)).thenReturn(Collections.emptyList());

        ShowDetailResponse result = showService.getShowById(143);

        assertThat(result.id()).isEqualTo(143);
        assertThat(result.name()).isEqualTo("Breaking Bad");
        assertThat(result.comments()).isEmpty();

        verify(tvMazeClient, never()).getShowById(anyInt());
        verify(showRepository, never()).save(any());
    }

    @Test
    void getShowById_whenShowIsNotCached_fetchesFromApiAndCachesResult() {
        showService = new ShowServiceImpl(tvMazeClient, showRepository, commentRepository);

        when(showRepository.findById(143)).thenReturn(Optional.empty());
        when(tvMazeClient.getShowById(143)).thenReturn(sampleShow());
        when(commentRepository.findByShowId(143)).thenReturn(Collections.emptyList());

        ShowDetailResponse result = showService.getShowById(143);

        assertThat(result.id()).isEqualTo(143);
        assertThat(result.name()).isEqualTo("Breaking Bad");

        verify(tvMazeClient, times(1)).getShowById(143);
        verify(showRepository, times(1)).save(any(ShowDocument.class));
    }
}