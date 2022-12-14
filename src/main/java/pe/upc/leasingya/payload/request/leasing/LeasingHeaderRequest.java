package pe.upc.leasingya.payload.request.leasing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeasingHeaderRequest {

    //Atributos
    private Double precioVenta;
    private Integer cantidadAnio;
    private Integer frecuenciaPago;
    private Integer diasAnio;
    private Double porcentajeTEA;
    private Double porcentajeIGV;
    private Double porcentajeImpuestoRenta;
    private Double porcentajeRecompra;
    private Double costoNotarial;
    private Double costoRegistral;
    private Double tasacion;
    private Double comisionEstudio;
    private Double comisionActivacion;
    private Double comisionPeriodica;
    private Double porcentajeSeguroRiesgo;
    private Double tasaDescuentoKs;
    private Double tasaDescuentoWACC;

    //Constructores
    public LeasingHeaderRequest() {
    }

    public LeasingHeaderRequest(Double precioVenta, Integer cantidadAnio, Integer frecuenciaPago, Integer diasAnio,
                                Double porcentajeTEA, Double porcentajeIGV, Double porcentajeImpuestoRenta,
                                Double porcentajeRecompra, Double costoNotarial, Double costoRegistral, Double tasacion,
                                Double comisionEstudio, Double comisionActivacion, Double comisionPeriodica,
                                Double porcentajeSeguroRiesgo, Double tasaDescuentoKs, Double tasaDescuentoWACC) {
        this.precioVenta = precioVenta;
        this.cantidadAnio = cantidadAnio;
        this.frecuenciaPago = frecuenciaPago;
        this.diasAnio = diasAnio;
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
