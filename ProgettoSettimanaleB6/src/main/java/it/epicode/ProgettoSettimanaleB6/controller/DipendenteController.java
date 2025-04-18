package it.epicode.ProgettoSettimanaleB6.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import it.epicode.ProgettoSettimanaleB6.model.Dipendente;
import it.epicode.ProgettoSettimanaleB6.payload.UploadImageResponse;
import it.epicode.ProgettoSettimanaleB6.service.CloudinaryService;
import it.epicode.ProgettoSettimanaleB6.service.DipendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {


    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private DipendenteService dipendenteService;

    // 1. CREAZIONE DIPENDENTE
    @Operation(summary = "Crea nuovo dipendente")
    @PostMapping
    public ResponseEntity<Dipendente> createDipendente(
            @Valid @RequestBody Dipendente dipendente) {
        return new ResponseEntity<>(
                dipendenteService.save(dipendente),
                HttpStatus.CREATED);
    }

    // 2. LISTA DIPENDENTI
    @Operation(summary = "Lista tutti i dipendenti")
    @GetMapping
    public ResponseEntity<List<Dipendente>> getAll() {
        return ResponseEntity.ok(dipendenteService.getAll());
    }

    // 3. RICERCA PER ID
    @Operation(summary = "Trova dipendente per ID")
    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(dipendenteService.getById(id));
    }

    // 4. AGGIORNAMENTO
    @Operation(summary = "Aggiorna dipendente")
    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> update(
            @PathVariable Long id,
            @Valid @RequestBody Dipendente dipendente) {
        return ResponseEntity.ok(dipendenteService.update(id, dipendente));
    }

    // 5. ELIMINAZIONE
    @Operation(summary = "Elimina dipendente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {
        dipendenteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 6. UPLOAD IMMAGINE (endpoint aggiuntivo)
    @Operation(summary = "Upload immagine profilo")
    @PostMapping(
            value = "/{id}/upload-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Dipendente> uploadImage(
            @PathVariable Long id,
            @RequestPart("file") MultipartFile file) throws IOException {

        String imageUrl = cloudinaryService.uploadImage(file);
        return ResponseEntity.ok(
                dipendenteService.updateImageUrl(id, imageUrl));
    }


}