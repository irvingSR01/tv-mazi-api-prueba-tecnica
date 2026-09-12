package com.irvingSR01.tv_maze_api.service;

import com.irvingSR01.tv_maze_api.model.ShowResponse;

import java.util.List;
import java.util.Map;

public interface ShowService {
    List<ShowResponse> searchShows(String query);
    Map<String, Object> getShowById(Integer id);
}
