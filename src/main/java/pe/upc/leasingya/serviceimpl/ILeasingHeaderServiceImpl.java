package pe.upc.leasingya.serviceimpl;

import org.springframework.stereotype.Service;
import pe.upc.leasingya.entity.Leasing;
import pe.upc.leasingya.entity.LeasingHeader;
import pe.upc.leasingya.repository.ILeasingHeaderDAO;
import pe.upc.leasingya.service.ILeasingHeaderService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ILeasingHeaderServiceImpl implements ILeasingHeaderService {

    final ILeasingHeaderDAO data;

    public ILeasingHeaderServiceImpl(ILeasingHeaderDAO data) {
        this.data = data;
    }

    @Override
    public Optional<LeasingHeader> BuscarLeasing_By_Leasing(Leasing leasing) {
        return data.findLeasingHeaderByLeasing(leasing);
    }

    @Override
    public void Guardar(LeasingHeader leasingheader) {
        data.save(leasingheader);
    }
}
