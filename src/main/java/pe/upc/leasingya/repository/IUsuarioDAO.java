package pe.upc.leasingya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.upc.leasingya.entity.Usuario;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario, UUID> {

    @Query("SELECT NEW Usuario(u.nombreUsuario, u.apellidoUsuario, u.emailUsuario, u.usernameUsuario) " +
            "FROM Usuario u " +
            "WHERE u.nombreUsuario LIKE ?1 " +
            "AND u.apellidoUsuario LIKE ?2 " +
            "AND u.emailUsuario LIKE ?3 " +
            "AND u.usernameUsuario LIKE ?4")
    Set<Usuario> findUsuarioToValidate(String nombreUsuario, String apellidoUsuario, String emailUsuario,
                                       String usernameUsuario);

    @Query("SELECT NEW Usuario(u.idUsuario) " +
            "FROM Usuario u " +
            "WHERE u.idUsuario = ?1")
    Optional<Usuario> findUsuarioIfExists(UUID idUsuario);

    @Query("SELECT NEW Usuario(u.usernameUsuario, u.passwordUsuario) " +
            "FROM Usuario u " +
            "WHERE u.usernameUsuario LIKE ?1 OR u.emailUsuario LIKE ?1")
    Optional<Usuario> findUsuarioIfExistsByUsernameUsuarioOrEmailUsuario(String usernameoremailUsuario);

    @Query("SELECT NEW Usuario(u.idUsuario, u.nombreUsuario, u.apellidoUsuario, u.emailUsuario, u.usernameUsuario, " +
            "u.passwordUsuario) " +
            "FROM Usuario u " +
            "WHERE u.usernameUsuario LIKE ?1")
    Optional<Usuario> findUsuarioToAuthenticate(String usernameUsuario);
}
