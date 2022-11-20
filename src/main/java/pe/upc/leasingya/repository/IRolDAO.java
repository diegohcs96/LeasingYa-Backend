package pe.upc.leasingya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.upc.leasingya.entity.Rol;
import pe.upc.leasingya.enums.RolNombre;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface IRolDAO extends JpaRepository<Rol, UUID> {

    @Query("SELECT NEW Rol(r.nombreRol) " +
            "FROM Rol r " +
            "JOIN r.usuariosRoles u " +
            "WHERE u.idUsuario = ?1")
    Set<Rol> findRolesByIdUsuario(UUID idUsuario);

    Optional<Rol> findByNombreRol(RolNombre rolNombre);
}
