package pe.upc.leasingya.service;

import pe.upc.leasingya.entity.Rol;
import pe.upc.leasingya.enums.RolNombre;

import java.util.Optional;

public interface IRolService {

    Optional<Rol> BuscarRol_By_NombreRol(RolNombre rolNombre);

    void GuardarRol(Rol rol);
}
