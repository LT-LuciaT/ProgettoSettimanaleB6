package it.epicode.ProgettoSettimanaleB6.controller;


import it.epicode.ProgettoSettimanaleB6.model.Prenotazione;
import it.epicode.ProgettoSettimanaleB6.payload.AssegnaViaggioRequest;
import it.epicode.ProgettoSettimanaleB6.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping
    public ResponseEntity<Prenotazione> creaPrenotazione(@RequestBody AssegnaViaggioRequest request) {
        Prenotazione prenotazione = prenotazioneService.creaPrenotazione(
                request.getViaggioId(),
                request.getDipendenteId(),
                request.getNote(),
                request.getPreferenzeVolo(),
                request.getPreferenzeAlloggio());

        return new ResponseEntity<>(prenotazione, HttpStatus.CREATED);
    }
}
