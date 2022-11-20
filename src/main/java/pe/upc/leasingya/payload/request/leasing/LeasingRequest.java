package pe.upc.leasingya.payload.request.leasing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeasingRequest {

    //Atributos
    private String nombreLeasing;

    private LeasingHeaderRequest datosLeasing;

    //Constructores
    public LeasingRequest() {
    }

    public LeasingRequest(String nombreLeasing) {
        this.nombreLeasing = nombreLeasing;
    }
}
