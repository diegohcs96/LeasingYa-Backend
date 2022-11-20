package pe.upc.leasingya.payload.response.leasing;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class LeasingResponse {

    //Atributos
    private UUID idLeasing;
    private String nombreLeasing;
    private LocalDateTime fecharegistroLeasing;

    private LeasingHeaderResponse datosentradaLeasing;

    //Constructores
    public LeasingResponse() {
    }

    public LeasingResponse(UUID idLeasing, String nombreLeasing, LocalDateTime fecharegistroLeasing) {
        this.idLeasing = idLeasing;
        this.nombreLeasing = nombreLeasing;
        this.fecharegistroLeasing = fecharegistroLeasing;
    }
}
