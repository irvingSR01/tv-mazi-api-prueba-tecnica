package com.irvingSR01.tv_maze_api.service;

import com.irvingSR01.tv_maze_api.model.ShowResponse;
import com.irvingSR01.tv_maze_api.model.TvMazeShow;

import java.util.List;

public interface ShowService {
    List<ShowResponse> searchShows(String query);
    TvMazeShow getShowById(Integer id);
}