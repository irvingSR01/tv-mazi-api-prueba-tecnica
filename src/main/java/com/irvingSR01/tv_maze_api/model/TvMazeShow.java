package com.irvingSR01.tv_maze_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TvMazeShow(
        Integer id,
        String name,
        String type,
        List<String> genres,
        String status,
        Integer runtime,
        String premiered,
        String ended,
        String summary,
        Rating rating,
        ImageInfo image,
        Channel network,
        Channel webChannel
) {}