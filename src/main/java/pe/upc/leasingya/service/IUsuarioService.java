package pe.upc.leasingya.service;

import pe.upc.leasingya.entity.Usuario;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IUsuarioService {

    Set<Usuario> ValidarUsuario(String nombreUsuario, String apellidoUsuario, String emailUsuario, String usernameUsuario);

    Optional<Usuario> BuscarUsuario_SiExiste(UUID idUsuario);

    Optional<Usuario> BuscarUsuario_SiExiste_By_UsernameUsuarioOrEmailUsuario(String usernameoremailUsuario);

    void GuardarUsuario(Usuario usuario);
}
