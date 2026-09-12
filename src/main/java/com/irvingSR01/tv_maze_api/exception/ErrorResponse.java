package com.irvingSR01.tv_maze_api.exception;

public record ErrorResponse(
        int status,
        String error,
        String message
) {
}
