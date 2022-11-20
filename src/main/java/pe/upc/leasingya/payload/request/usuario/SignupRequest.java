package pe.upc.leasingya.payload.request.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    //Atributos
    private String nombreUsuario;
    private String apellidoUsuario;
    private String emailUsuario;
    private String usernameUsuario;
    private String passwordUsuario;

    //Constructores
    public SignupRequest() {
    }

    public SignupRequest(String nombreUsuario, String apellidoUsuario, String emailUsuario, String usernameUsuario,
                         String passwordUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.emailUsuario = emailUsuario;
        this.usernameUsuario = usernameUsuario;
        this.passwordUsuario = passwordUsuario;
    }
}
