package pe.upc.leasingya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.upc.leasingya.entity.Leasing;
import pe.upc.leasingya.entity.Usuario;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ILeasingDAO extends JpaRepository<Leasing, UUID> {

    @Query("SELECT NEW Leasing(l.nombreLeasing, l.usuarioLeasing) " +
            "FROM Leasing l " +
            "WHERE l.nombreLeasing LIKE ?1 " +
            "AND l.usuarioLeasing = ?2")
    Set<Leasing> ValidarLeasing(String nombreLeasing, Usuario usuario);

    @Query("SELECT NEW Leasing(l.idLeasing, l.nombreLeasing, l.fecharegistroLeasing) " +
            "FROM Leasing l " +
            "WHERE l.usuarioLeasing = ?1")
    Set<Leasing> findLeasingsByUsuario(Usuario usuario);
}
