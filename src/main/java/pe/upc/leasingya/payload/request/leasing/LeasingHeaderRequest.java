package pe.upc.leasingya.payload.request.leasing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeasingHeaderRequest {

    //Atributos
    private double precioVenta;
    private int cantidadAnio;
    private int frecuenciaPago;
    private double porcentajeTEA;
    private double porcentajeIGV;
    private double porcentajeImpuestoRenta;
    private double porcentajeRecompra;
    private double costoNotarial;
    private double costoRegistral;
    private double tasacion;
    private double comisionEstudio;
    private double comisionActivacion;
    private double comisionPeriodica;
    private double porcentajeSeguroRiesgo;
    private double tasaDescuentoKs;
    private double tasaDescuentoWACC;

    //Constructores
    public LeasingHeaderRequest() {
    }

    public LeasingHeaderRequest(double precioVenta, int cantidadAnio, int frecuenciaPago, double porcentajeTEA,
                                double porcentajeIGV, double porcentajeImpuestoRenta, double porcentajeRecompra,
                                double costoNotarial, double costoRegistral, double tasacion, double comisionEstudio,
                                double comisionActivacion, double comisionPeriodica, double porcentajeSeguroRiesgo,
                                double tasaDescuentoKs, double tasaDescuentoWACC) {
        this.precioVenta = precioVenta;
        this.cantidadAnio = cantidadAnio;
        this.frecuenciaPago = frecuenciaPago;
        this.porcentajeTEA = porcentajeTEA;
        this.porcentajeIGV = porcentajeIGV;
        this.porcentajeImpuestoRenta = porcentajeImpuestoRenta;
        this.porcentajeRecompra = porcentajeRecompra;
        this.costoNotarial = costoNotarial;
        this.costoRegistral = costoRegistral;
        this.tasacion = tasacion;
        this.comisionEstudio = comisionEstudio;
        this.comisionActivacion = comisionActivacion;
        this.comisionPeriodica = comisionPeriodica;
        this.porcentajeSeguroRiesgo = porcentajeSeguroRiesgo;
        this.tasaDescuentoKs = tasaDescuentoKs;
        this.tasaDescuentoWACC = tasaDescuentoWACC;
    }
}
