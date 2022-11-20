package pe.upc.leasingya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.upc.leasingya.entity.Leasing;
import pe.upc.leasingya.entity.LeasingHeader;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ILeasingHeaderDAO extends JpaRepository<LeasingHeader, UUID> {

    @Query("SELECT NEW LeasingHeader(lh.idLeasingHeader, lh.precioVenta, lh.cantidadAnio, lh.frecuenciaPago, lh.diasAnio, " +
            "lh.porcentajeTEA, lh.porcentajeIGV, lh.porcentajeImpuestoRenta, lh.porcentajeRecompra, lh.costoNotarial, " +
            "lh.costoRegistral, lh.tasacion, lh.comisionEstudio, lh.comisionActivacion, lh.comisionPeriodica, " +
            "lh.porcentajeSeguroRiesgo, lh.tasaDescuentoKs, lh.tasaDescuentoWACC) " +
            "FROM LeasingHeader lh " +
            "WHERE lh.leasingLeasingHeader = ?1")
    Optional<LeasingHeader> findLeasingHeaderByLeasing(Leasing leasing);
}

