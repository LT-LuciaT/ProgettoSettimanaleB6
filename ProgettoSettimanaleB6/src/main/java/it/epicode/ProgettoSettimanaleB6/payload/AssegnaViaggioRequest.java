package it.epicode.ProgettoSettimanaleB6.payload;

import lombok.Data;

@Data
public class AssegnaViaggioRequest {
    private Long viaggioId;
    private Long dipendenteId;
    private String note;
    private String preferenzeVolo;
    private String preferenzeAlloggio;


}