package it.epicode.ProgettoSettimanaleB6.repository;


import it.epicode.ProgettoSettimanaleB6.model.Dipendente;
import it.epicode.ProgettoSettimanaleB6.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;


public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByDipendenteAndViaggioData(Dipendente dipendente, LocalDate data);
}