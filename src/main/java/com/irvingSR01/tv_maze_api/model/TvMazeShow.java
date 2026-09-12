package com.irvingSR01.tv_maze_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TvMazeShow(
        Integer id,
        String name,
        List<String> genres,
        String summary,
        Channel network,
        Channel webChannel
) {}
