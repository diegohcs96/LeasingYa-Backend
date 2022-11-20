package pe.upc.leasingya.service;

import pe.upc.leasingya.entity.Leasing;
import pe.upc.leasingya.entity.LeasingHeader;

import java.util.Optional;

public interface ILeasingHeaderService {

    Optional<LeasingHeader> BuscarLeasing_By_Leasing(Leasing leasing);

    void Guardar(LeasingHeader leasingheader);
}
