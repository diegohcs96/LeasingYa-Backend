package pe.upc.leasingya.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "leasingheader")
@Getter
@Setter
public class LeasingHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    //-- Datos de Prestamo
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_leasingheader")
    private UUID idLeasingHeader;

    @Column(name = "precio_venta")
    private double precioVenta;

    @Column(name = "cantidad_anio")
    private int cantidadAnio;

    @Column(name = "frecuencia_pago")
    private int frecuenciaPago;

    @Column(name = "porcentaje_tea")
    private double porcentajeTEA;

    @Column(name = "porcentaje_igv")
    private double porcentajeIGV;

    @Column(name = "porcentaje_impuestorenta")
    private double porcentajeImpuestoRenta;

    @Column(name = "porcentaje_recompra")
    private double porcentajeRecompra;

    //-- Datos de Costes/Gastos Iniciales
    @Column(name = "costo_notarial")
    private double costoNotarial;

    @Column(name = "costo_registral")
    private double costoRegistral;

    @Column(name = "tasacion")
    private double tasacion;

    @Column(name = "comision_estudio")
    private double comisionEstudio;

    @Column(name = "comision_activacion")
    private double comisionActivacion;

    //-- Datos de Costes/Gastos Iniciales
    @Column(name = "comision_periodica")
    private double comisionPeriodica;

    @Column(name = "porcentaje_seguroriesgo")
    private double porcentajeSeguroRiesgo;

    //-- Datos Costo Oportunidad;
    @Column(name = "tasadescuento_ks")
    private double tasaDescuentoKs;

    @Column(name = "tasadescuento_wacc")
    private double tasaDescuentoWACC;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_leasing")
    private Leasing leasingLeasingHeader;

    //Constructores
    public LeasingHeader() {
    }

    public LeasingHeader(double precioVenta, int cantidadAnio, int frecuenciaPago, double porcentajeTEA,
                         double porcentajeIGV, double porcentajeImpuestoRenta, double porcentajeRecompra,
                         double costoNotarial, double costoRegistral, double tasacion, double comisionEstudio,
                         double comisionActivacion, double comisionPeriodica, double porcentajeSeguroRiesgo,
                         double tasaDescuentoKs, double tasaDescuentoWACC, Leasing leasingLeasingHeader) {
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
        this.leasingLeasingHeader = leasingLeasingHeader;
    }

    public LeasingHeader(UUID idLeasingHeader, double precioVenta, int cantidadAnio, int frecuenciaPago,
                         double porcentajeTEA, double porcentajeIGV, double porcentajeImpuestoRenta,
                         double porcentajeRecompra, double costoNotarial, double costoRegistral,
                         double tasacion, double comisionEstudio, double comisionActivacion, double comisionPeriodica,
                         double porcentajeSeguroRiesgo, double tasaDescuentoKs, double tasaDescuentoWACC) {
        this.idLeasingHeader = idLeasingHeader;
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
