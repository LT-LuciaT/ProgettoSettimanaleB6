package it.epicode.ProgettoSettimanaleB6.controller;


import com.cloudinary.utils.ObjectUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.epicode.ProgettoSettimanaleB6.payload.UploadImageResponse;
import it.epicode.ProgettoSettimanaleB6.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/cloudinary")
public class CloudinaryController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Operation(summary = "Upload immagine profilo")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UploadImageResponse> uploadImage(
            @RequestPart("file") MultipartFile file) {
        try {
            String imageUrl = cloudinaryService.uploadImage(file); // Versione semplificata
            return ResponseEntity.ok(
                    new UploadImageResponse(imageUrl, "Upload completato con successo"));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(
                    new UploadImageResponse(null, "Errore durante l'upload: " + e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteImage(
            @RequestParam("publicId") String publicId) {
        try {
            cloudinaryService.deleteImage(publicId);
            return ResponseEntity.ok("Immagine eliminata con successo");
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Errore durante l'eliminazione: " + e.getMessage());
        }
    }
}