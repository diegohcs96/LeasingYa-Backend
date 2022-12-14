package pe.upc.leasingya.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pe.upc.leasingya.entity.Usuario;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserDetailsImpl implements UserDetails {

    //Atributos
    private UUID idUsuario;
    private String usernameUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;

    @JsonIgnore
    private String passwordUsuario;

    private Collection<? extends GrantedAuthority> authorities;

    //Constructores
    public UserDetailsImpl() {
    }

    public UserDetailsImpl(UUID idUsuario, String usernameUsuario, String nombreUsuario, String apellidoUsuario,
                           String passwordUsuario, Collection<? extends GrantedAuthority> authorities) {
        this.idUsuario = idUsuario;
        this.usernameUsuario = usernameUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.passwordUsuario = passwordUsuario;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Usuario usuario) {
        List<GrantedAuthority> authorities = usuario.getRolesUsuario()
                .stream().map(rol -> new SimpleGrantedAuthority(rol.getNombreRol().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                usuario.getIdUsuario(),
                usuario.getUsernameUsuario(),
                usuario.getNombreUsuario(),
                usuario.getApellidoUsuario(),
                usuario.getPasswordUsuario(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    @Override
    public String getPassword() {
        return passwordUsuario;
    }

    @Override
    public String getUsername() {
        return usernameUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl usuario = (UserDetailsImpl) o;
        return Objects.equals(idUsuario, usuario.idUsuario);
    }
}
