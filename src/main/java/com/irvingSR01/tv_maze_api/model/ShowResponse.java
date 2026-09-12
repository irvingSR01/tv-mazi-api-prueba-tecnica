package com.irvingSR01.tv_maze_api.model;

import java.util.List;

public record ShowResponse(
        Integer id,
        String name,
        String channel,
        String summary,
        List<String> genres
) {}