package pe.upc.leasingya.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.upc.leasingya.entity.Rol;
import pe.upc.leasingya.entity.Usuario;
import pe.upc.leasingya.repository.IRolDAO;
import pe.upc.leasingya.repository.IUsuarioDAO;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    final IUsuarioDAO dataUsuario;

    final IRolDAO dataRol;

    public UserDetailServiceImpl(IUsuarioDAO dataUsuario, IRolDAO dataRol) {
        this.dataUsuario = dataUsuario;
        this.dataRol = dataRol;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = dataUsuario.findUsuarioToAuthenticate(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        Set<Rol> roles = dataRol.findRolesByIdUsuario(usuario.getIdUsuario());

        Usuario currentUsuario = new Usuario(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getApellidoUsuario(),
                usuario.getEmailUsuario(),
                usuario.getUsernameUsuario(),
                usuario.getPasswordUsuario(),
                roles
        );

        return UserDetailsImpl.build(currentUsuario);
    }
}
