package it.epicode.ProgettoSettimanaleB6.service;


import it.epicode.ProgettoSettimanaleB6.exception.ViaggioNotFoundException;
import it.epicode.ProgettoSettimanaleB6.model.Viaggio;
import it.epicode.ProgettoSettimanaleB6.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    public List<Viaggio> findAll() {
        return viaggioRepository.findAll();
    }

    public Viaggio findById(Long id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new ViaggioNotFoundException("Viaggio non trovato con ID: " + id));
    }

    public Viaggio save(Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }

    public void deleteById(Long id) {
        viaggioRepository.deleteById(id);
    }

    public Viaggio cambiaStatoViaggio(Long id, Viaggio.StatoViaggio nuovoStato) {
        Viaggio viaggio = findById(id);
        viaggio.setStato(nuovoStato);
        return viaggioRepository.save(viaggio);
    }
}
