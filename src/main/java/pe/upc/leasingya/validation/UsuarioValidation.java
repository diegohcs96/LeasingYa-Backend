package pe.upc.leasingya.validation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioValidation {

    //Atributos
    private String nombreUsuario;
    private String apellidoUsuario;
    private String emailUsuario;
    private String usernameUsuario;

    //Constructores
    public UsuarioValidation() {
    }

    public UsuarioValidation(String nombreUsuario, String apellidoUsuario, String emailUsuario, String usernameUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.emailUsuario = emailUsuario;
        this.usernameUsuario = usernameUsuario;
    }
}
