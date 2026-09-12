package com.irvingSR01.tv_maze_api.mapper;

import com.irvingSR01.tv_maze_api.document.ShowDocument;
import com.irvingSR01.tv_maze_api.model.TvMazeShow;

public final class ShowMapper {

    private ShowMapper() { }

    public static ShowDocument toDocument(TvMazeShow show) {
        return new ShowDocument(
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
                show.webChannel()
        );
    }

    public static TvMazeShow toApiModel(ShowDocument document) {
        return new TvMazeShow(
                document.id(),
                document.name(),
                document.type(),
                document.genres(),
                document.status(),
                document.runtime(),
                document.premiered(),
                document.ended(),
                document.summary(),
                document.rating(),
                document.image(),
                document.network(),
                document.webChannel()
        );
    }
}