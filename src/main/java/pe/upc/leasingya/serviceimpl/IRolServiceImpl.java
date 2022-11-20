package pe.upc.leasingya.serviceimpl;

import org.springframework.stereotype.Service;
import pe.upc.leasingya.entity.Rol;
import pe.upc.leasingya.enums.RolNombre;
import pe.upc.leasingya.repository.IRolDAO;
import pe.upc.leasingya.service.IRolService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class IRolServiceImpl implements IRolService {

    final IRolDAO data;

    public IRolServiceImpl(IRolDAO data) {
        this.data = data;
    }

    @Override
    public Optional<Rol> BuscarRol_By_NombreRol(RolNombre rolNombre) {
        return data.findByNombreRol(rolNombre);
    }

    @Override
    public void GuardarRol(Rol rol) {
        data.save(rol);
    }
}
