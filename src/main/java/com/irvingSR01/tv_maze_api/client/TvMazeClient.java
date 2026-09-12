package com.irvingSR01.tv_maze_api.client;

import com.irvingSR01.tv_maze_api.model.TvMazeSearchResponse;
import com.irvingSR01.tv_maze_api.model.TvMazeShow;

public interface TvMazeClient {
    TvMazeSearchResponse[] search(String query);
    TvMazeShow getShowById(Integer id);
}