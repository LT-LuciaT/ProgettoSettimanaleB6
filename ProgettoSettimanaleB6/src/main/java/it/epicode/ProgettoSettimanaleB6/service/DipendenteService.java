package it.epicode.ProgettoSettimanaleB6.service;


import it.epicode.ProgettoSettimanaleB6.exception.DipendenteNotFoundException;
import it.epicode.ProgettoSettimanaleB6.model.Dipendente;
import it.epicode.ProgettoSettimanaleB6.repository.DipendenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository repository;

    // Metodi CRUD base
    public List<Dipendente> getAll() {
        return repository.findAll();
    }

    public Dipendente save(Dipendente dipendente) {
        return repository.save(dipendente);
    }

    public Dipendente getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
    }

    public Dipendente findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
    }
    public Dipendente update(Long id, Dipendente dipendente) {
        Dipendente existing = getById(id);
        existing.setUsername(dipendente.getUsername());
        existing.setNome(dipendente.getNome());
        existing.setCognome(dipendente.getCognome());
        existing.setEmail(dipendente.getEmail());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Metodo per l'immagine
    public Dipendente updateImageUrl(Long id, String imageUrl) {
        Dipendente dipendente = getById(id);
        dipendente.setImageUrl(imageUrl);
        return repository.save(dipendente);
    }
}
