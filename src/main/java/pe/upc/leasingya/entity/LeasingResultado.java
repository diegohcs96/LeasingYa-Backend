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
public class LeasingResultado implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_leasingresultado")
    private UUID idLeasingResultado;

    //-- Del Arrendamiento
    @Column(name = "igv")
    private Double igv;

    @Column(name = "valor_ventaactivo")
    private Double valorVentaActivo;

    @Column(name = "monto_leasing")
    private Double montoLeasing;

    @Column(name = "porcentaje_tep")
    private Double porcentajeTEP;

    @Column(name = "cuotas_año")
    private Integer cuotasAnio;

    @Column(name = "total_cuotas")
    private Integer totalCuotas;

    //-- De Costes/Gastos Periodicos
    @Column(name = "seguro_riesgo")
    private Double seguroRiesgo;

    //-- Totales
    @Column(name = "intereses")
    private Double intereses;

    @Column(name = "amortizacion_capital")
    private Double amortizacionCapital;

    @Column(name = "total_seguroriesgo")
    private Double totalSeguroRiesgo;

    @Column(name = "comisiones_periodicas")
    private Double comisionesPeriodicas;

    @Column(name = "recompra")
    private Double recompra;

    @Column(name = "desembolso_total")
    private Double desembolsoTotal;

    //-- Indicadores Rentabilidad
    @Column(name = "porcentajetcea_flujobruto")
    private Double porcentajeTCEAFlujoBruto;

    @Column(name = "porcentajetcea_flujoneto")
    private Double porcentajeTCEAFlujoNeto;

    @Column(name = "porcentajevan_flujobruto")
    private Double porcentajeVANFlujoBruto;

    @Column(name = "porcentajevan_flujoneto")
    private Double porcentajeVANFlujoNeto;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_leasing")
    private Leasing leasingLeasingResultado;

    //Constructores
    public LeasingResultado() {
    }

    public LeasingResultado(UUID idLeasingResultado, Double igv, Double valorVentaActivo, Double montoLeasing,
                            Double porcentajeTEP, Integer cuotasAnio, Integer totalCuotas, Double seguroRiesgo,
                            Double intereses, Double amortizacionCapital, Double totalSeguroRiesgo,
                            Double comisionesPeriodicas, Double recompra, Double desembolsoTotal,
                            Double porcentajeTCEAFlujoBruto, Double porcentajeTCEAFlujoNeto,
                            Double porcentajeVANFlujoBruto, Double porcentajeVANFlujoNeto) {
        this.idLeasingResultado = idLeasingResultado;
        this.igv = igv;
        this.valorVentaActivo = valorVentaActivo;
        this.montoLeasing = montoLeasing;
        this.porcentajeTEP = porcentajeTEP;
        this.cuotasAnio = cuotasAnio;
        this.totalCuotas = totalCuotas;
        this.seguroRiesgo = seguroRiesgo;
        this.intereses = intereses;
        this.amortizacionCapital = amortizacionCapital;
        this.totalSeguroRiesgo = totalSeguroRiesgo;
        this.comisionesPeriodicas = comisionesPeriodicas;
        this.recompra = recompra;
        this.desembolsoTotal = desembolsoTotal;
        this.porcentajeTCEAFlujoBruto = porcentajeTCEAFlujoBruto;
        this.porcentajeTCEAFlujoNeto = porcentajeTCEAFlujoNeto;
        this.porcentajeVANFlujoBruto = porcentajeVANFlujoBruto;
        this.porcentajeVANFlujoNeto = porcentajeVANFlujoNeto;
    }
}
