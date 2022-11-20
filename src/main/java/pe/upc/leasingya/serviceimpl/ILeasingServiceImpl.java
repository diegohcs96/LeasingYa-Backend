package pe.upc.leasingya.serviceimpl;

import org.springframework.stereotype.Service;
import pe.upc.leasingya.entity.Leasing;
import pe.upc.leasingya.entity.Usuario;
import pe.upc.leasingya.repository.ILeasingDAO;
import pe.upc.leasingya.service.ILeasingService;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class ILeasingServiceImpl implements ILeasingService {

    final ILeasingDAO data;

    public ILeasingServiceImpl(ILeasingDAO data) {
        this.data = data;
    }


    @Override
    public Set<Leasing> ValidarLeasing(String nombreLeasing, Usuario usuario) {
        return data.ValidarLeasing(nombreLeasing, usuario);
    }

    @Override
    public Set<Leasing> MostrarLeasings_By_Usuario(Usuario usuario) {
        return data.findLeasingsByUsuario(usuario);
    }

    @Override
    public void GuardarLeasing(Leasing leasing) {
        data.save(leasing);
    }
}
