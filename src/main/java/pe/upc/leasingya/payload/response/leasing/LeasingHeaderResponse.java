package pe.upc.leasingya.payload.response.leasing;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LeasingHeaderResponse {

    //Atributos
    //-- Datos de Prestamo
    private UUID idLeasingHeader;
    private Double precioVenta;
    private Integer cantidadAnio;
    private Integer diasAnio;
    private Integer frecuenciaPago;
    private Double porcentajeTEA;
    private Double porcentajeIGV;
    private Double porcentajeImpuestoRenta;
    private Double porcentajeRecompra;

    //-- Datos de Costes/Gastos Iniciales
    private Double costoNotarial;
    private Double costoRegistral;
    private Double tasacion;
    private Double comisionEstudio;
    private Double comisionActivacion;

    //-- Datos de Costes/Gastos Iniciales
    private Double comisionPeriodica;
    private Double porcentajeSeguroRiesgo;

    //-- Datos Costo Oportunidad;
    private Double tasaDescuentoKs;
    private Double tasaDescuentoWACC;

    //Constructores
    public LeasingHeaderResponse() {
    }

    public LeasingHeaderResponse(UUID idLeasingHeader, Double precioVenta, Integer cantidadAnio, Integer diasAnio,
                                 Integer frecuenciaPago, Double porcentajeTEA, Double porcentajeIGV,
                                 Double porcentajeImpuestoRenta, Double porcentajeRecompra, Double costoNotarial,
                                 Double costoRegistral, Double tasacion, Double comisionEstudio, Double comisionActivacion,
                                 Double comisionPeriodica, Double porcentajeSeguroRiesgo, Double tasaDescuentoKs,
                                 Double tasaDescuentoWACC) {
        this.idLeasingHeader = idLeasingHeader;
        this.precioVenta = precioVenta;
        this.cantidadAnio = cantidadAnio;
        this.diasAnio = diasAnio;
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
