package com.ua.boardgames.exception;

import java.util.Map;

public record ErrorResponse(int status, String error, String message, Map<String, String> details) {
}
