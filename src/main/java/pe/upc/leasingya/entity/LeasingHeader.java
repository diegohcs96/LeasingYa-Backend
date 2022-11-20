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
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_leasingheader")
    private UUID idLeasingHeader;

    //-- Datos de Prestamo
    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "cantidad_año")
    private Integer cantidadAnio;

    @Column(name = "frecuencia_pago")
    private Integer frecuenciaPago;

    @Column(name = "dias_año")
    private Integer diasAnio;

    @Column(name = "porcentaje_tea")
    private Double porcentajeTEA;

    @Column(name = "porcentaje_igv")
    private Double porcentajeIGV;

    @Column(name = "porcentaje_impuestorenta")
    private Double porcentajeImpuestoRenta;

    @Column(name = "porcentaje_recompra")
    private Double porcentajeRecompra;

    //-- Datos de Costes/Gastos Iniciales
    @Column(name = "costo_notarial")
    private Double costoNotarial;

    @Column(name = "costo_registral")
    private Double costoRegistral;

    @Column(name = "tasacion")
    private Double tasacion;

    @Column(name = "comision_estudio")
    private Double comisionEstudio;

    @Column(name = "comision_activacion")
    private Double comisionActivacion;

    //-- Datos de Costes/Gastos Iniciales
    @Column(name = "comision_periodica")
    private Double comisionPeriodica;

    @Column(name = "porcentaje_seguroriesgo")
    private Double porcentajeSeguroRiesgo;

    //-- Datos Costo Oportunidad;
    @Column(name = "tasadescuento_ks")
    private Double tasaDescuentoKs;

    @Column(name = "tasadescuento_wacc")
    private Double tasaDescuentoWACC;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_leasing")
    private Leasing leasingLeasingHeader;

    //Constructores
    public LeasingHeader() {
    }

    public LeasingHeader(Double precioVenta, Integer cantidadAnio, Integer frecuenciaPago, Integer diasAnio,
                         Double porcentajeTEA, Double porcentajeIGV, Double porcentajeImpuestoRenta,
                         Double porcentajeRecompra, Double costoNotarial, Double costoRegistral, Double tasacion,
                         Double comisionEstudio, Double comisionActivacion, Double comisionPeriodica,
                         Double porcentajeSeguroRiesgo, Double tasaDescuentoKs, Double tasaDescuentoWACC,
                         Leasing leasingLeasingHeader) {
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
        this.leasingLeasingHeader = leasingLeasingHeader;
    }

    public LeasingHeader(UUID idLeasingHeader, Double precioVenta, Integer cantidadAnio, Integer frecuenciaPago,
                         Integer diasAnio, Double porcentajeTEA, Double porcentajeIGV, Double porcentajeImpuestoRenta,
                         Double porcentajeRecompra, Double costoNotarial, Double costoRegistral, Double tasacion,
                         Double comisionEstudio, Double comisionActivacion, Double comisionPeriodica,
                         Double porcentajeSeguroRiesgo, Double tasaDescuentoKs, Double tasaDescuentoWACC) {
        this.idLeasingHeader = idLeasingHeader;
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
