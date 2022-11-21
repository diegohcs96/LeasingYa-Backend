package pe.upc.leasingya.serviceimpl;

import org.springframework.stereotype.Service;
import pe.upc.leasingya.entity.LeasingResultado;
import pe.upc.leasingya.repository.ILeasingResultadoDAO;
import pe.upc.leasingya.service.ILeasingResultadoService;

import javax.transaction.Transactional;

@Service
@Transactional
public class ILeasingResultadoServiceImpl implements ILeasingResultadoService {

    final ILeasingResultadoDAO data;

    public ILeasingResultadoServiceImpl(ILeasingResultadoDAO data) {
        this.data = data;
    }

    @Override
    public void GuardarLeasingResultado(LeasingResultado leasingresultado) {
        data.save(leasingresultado);
    }
}
