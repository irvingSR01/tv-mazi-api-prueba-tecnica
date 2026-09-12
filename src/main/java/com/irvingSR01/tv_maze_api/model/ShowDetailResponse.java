package com.irvingSR01.tv_maze_api.model;

import java.util.List;

public record ShowDetailResponse(
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
        Channel webChannel,
        List<CommentResponse> comments
) {}