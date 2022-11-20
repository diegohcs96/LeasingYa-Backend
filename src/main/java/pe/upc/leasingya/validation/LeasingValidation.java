package pe.upc.leasingya.validation;

import lombok.Getter;
import lombok.Setter;
import pe.upc.leasingya.entity.Usuario;

@Getter
@Setter
public class LeasingValidation {

    //Atributos
    private String nombreLeasing;
    private Usuario usuarioLeasing;

    //Constructores
    public LeasingValidation() {
    }

    public LeasingValidation(String nombreLeasing, Usuario usuarioLeasing) {
        this.nombreLeasing = nombreLeasing;
        this.usuarioLeasing = usuarioLeasing;
    }
}
