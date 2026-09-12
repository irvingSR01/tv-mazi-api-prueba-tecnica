package com.irvingSR01.tv_maze_api.service;

import com.irvingSR01.tv_maze_api.model.ShowResponse;

import java.util.List;

public interface ShowService {
    List<ShowResponse> searchShows(String query);
}
