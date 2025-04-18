package it.epicode.ProgettoSettimanaleB6.controller;


import it.epicode.ProgettoSettimanaleB6.model.Viaggio;
import it.epicode.ProgettoSettimanaleB6.payload.CambiaStatoViaggioRequest;
import it.epicode.ProgettoSettimanaleB6.service.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public List<Viaggio> getAllViaggi() {
        return viaggioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaggio> getViaggioById(@PathVariable Long id) {
        return ResponseEntity.ok(viaggioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Viaggio> createViaggio(@RequestBody Viaggio viaggio) {
        return new ResponseEntity<>(viaggioService.save(viaggio), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaggio> updateViaggio(@PathVariable Long id, @RequestBody Viaggio viaggioDetails) {
        Viaggio viaggio = viaggioService.findById(id);
        viaggio.setDestinazione(viaggioDetails.getDestinazione());
        viaggio.setData(viaggioDetails.getData());
        viaggio.setStato(viaggioDetails.getStato());
        return ResponseEntity.ok(viaggioService.save(viaggio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViaggio(@PathVariable Long id) {
        viaggioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/stato")
    public ResponseEntity<Viaggio> cambiaStatoViaggio(@PathVariable Long id, @RequestBody CambiaStatoViaggioRequest request) {
        return ResponseEntity.ok(viaggioService.cambiaStatoViaggio(id, request.getStato()));
    }
}
