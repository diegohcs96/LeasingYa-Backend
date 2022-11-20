package pe.upc.leasingya.service;

import pe.upc.leasingya.entity.Leasing;
import pe.upc.leasingya.entity.Usuario;

import java.util.Set;

public interface ILeasingService {

    Set<Leasing> ValidarLeasing(String nombreLeasing, Usuario usuario);

    Set<Leasing> MostrarLeasings_By_Usuario(Usuario usuario);

    void GuardarLeasing(Leasing leasing);
}
