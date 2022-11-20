package pe.upc.leasingya.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
public class JwtResponse {

    //Atributos
    private String type = "Bearer";
    private String token;
    private UUID idUsuario;
    private String nombreUsuario;
    private String usernameUsuario;
    private Collection<? extends GrantedAuthority> authorities;

    //Constructores
    public JwtResponse() {
    }

    public JwtResponse(String token, UUID idUsuario, String nombreUsuario, String usernameUsuario,
                       Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.usernameUsuario = usernameUsuario;
        this.authorities = authorities;
    }
}
