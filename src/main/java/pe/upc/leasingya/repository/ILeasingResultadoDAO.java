package pe.upc.leasingya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.leasingya.entity.LeasingResultado;

import java.util.UUID;

@Repository
public interface ILeasingResultadoDAO extends JpaRepository<LeasingResultado, UUID> {

}
