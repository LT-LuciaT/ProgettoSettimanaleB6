package it.epicode.ProgettoSettimanaleB6.payload;

import it.epicode.ProgettoSettimanaleB6.model.Viaggio;
import lombok.Data;

@Data
public class CambiaStatoViaggioRequest {
    private Viaggio.StatoViaggio stato;


}