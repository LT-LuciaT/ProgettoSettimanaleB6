package it.epicode.ProgettoSettimanaleB6.service;


import it.epicode.ProgettoSettimanaleB6.exception.DataNonDisponibileException;
import it.epicode.ProgettoSettimanaleB6.model.Dipendente;
import it.epicode.ProgettoSettimanaleB6.model.Prenotazione;
import it.epicode.ProgettoSettimanaleB6.model.Viaggio;
import it.epicode.ProgettoSettimanaleB6.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private DipendenteService dipendenteService;

    public Prenotazione creaPrenotazione(Long viaggioId, Long dipendenteId, String note, String preferenzeVolo, String preferenzeAlloggio) {
        Viaggio viaggio = viaggioService.findById(viaggioId);
        Dipendente dipendente = dipendenteService.findById(dipendenteId);

        boolean giaPrenotato = prenotazioneRepository
                .findByDipendenteAndViaggioData(dipendente, viaggio.getData())
                .stream()
                .anyMatch(p -> !p.getId().equals(viaggioId));

        if (giaPrenotato) {
            throw new DataNonDisponibileException("Il dipendente ha già una prenotazione per questa data");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setViaggio(viaggio);
        prenotazione.setDipendente(dipendente);
        prenotazione.setDataRichiesta(java.time.LocalDate.now());
        prenotazione.setNote(note);
        prenotazione.setPreferenzeVolo(preferenzeVolo);
        prenotazione.setPreferenzeAlloggio(preferenzeAlloggio);

        return prenotazioneRepository.save(prenotazione);
    }
}
