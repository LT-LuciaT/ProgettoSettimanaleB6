package it.epicode.ProgettoSettimanaleB6.payload;

import lombok.Getter;

@Getter
public class UploadImageResponse {
    private String imageUrl;
    private String message;

    public UploadImageResponse(String imageUrl, String message) {
        this.imageUrl = imageUrl;
        this.message = message;
    }
}