package pe.upc.leasingya.serviceimpl;

import org.springframework.stereotype.Service;
import pe.upc.leasingya.entity.Usuario;
import pe.upc.leasingya.repository.IUsuarioDAO;
import pe.upc.leasingya.service.IUsuarioService;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class IUsuarioServiceImpl implements IUsuarioService {

    final IUsuarioDAO data;

    public IUsuarioServiceImpl(IUsuarioDAO data) {
        this.data = data;
    }

    @Override
    public Set<Usuario> ValidarUsuario(String nombreUsuario, String apellidoUsuario, String emailUsuario,
                                       String usernameUsuario) {
        return data.findUsuarioToValidate(nombreUsuario, apellidoUsuario, emailUsuario, usernameUsuario);
    }

    @Override
    public Optional<Usuario> BuscarUsuario_SiExiste(UUID idUsuario) {
        return data.findUsuarioIfExists(idUsuario);
    }

    @Override
    public Optional<Usuario> BuscarUsuario_SiExiste_By_UsernameUsuarioOrEmailUsuario(String usernameoremailUsuario) {
        return data.findUsuarioIfExistsByUsernameUsuarioOrEmailUsuario(usernameoremailUsuario);
    }

    @Override
    public void GuardarUsuario(Usuario usuario) {
        data.save(usuario);
    }
}
