package com.irvingSR01.tv_maze_api.document;

import com.irvingSR01.tv_maze_api.model.Channel;
import com.irvingSR01.tv_maze_api.model.ImageInfo;
import com.irvingSR01.tv_maze_api.model.Rating;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "shows")
public record ShowDocument(
        @Id Integer id,
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