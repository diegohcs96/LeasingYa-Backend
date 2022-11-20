package pe.upc.leasingya.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "leasing")
@Getter
@Setter
public class Leasing implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_leasing")
    private UUID idLeasing;

    @Column(name = "nombre_leasing")
    private String nombreLeasing;

    @Column(name = "fecharegistro_leasing")
    private LocalDateTime fecharegistroLeasing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioLeasing;

    @OneToOne(mappedBy = "leasingLeasingHeader")
    private LeasingHeader leasingheaderLeasing;

    @OneToOne(mappedBy = "leasingLeasingResultado")
    private LeasingResultado leasingresultadoLeasing;

    //Constructores
    public Leasing() {
    }

    public Leasing(String nombreLeasing, LocalDateTime fecharegistroLeasing, Usuario usuarioLeasing) {
        this.nombreLeasing = nombreLeasing;
        this.fecharegistroLeasing = fecharegistroLeasing;
        this.usuarioLeasing = usuarioLeasing;
    }

    public Leasing(String nombreLeasing, Usuario usuarioLeasing) {
        this.nombreLeasing = nombreLeasing;
        this.usuarioLeasing = usuarioLeasing;
    }

    public Leasing(UUID idLeasing, String nombreLeasing, LocalDateTime fecharegistroLeasing) {
        this.idLeasing = idLeasing;
        this.nombreLeasing = nombreLeasing;
        this.fecharegistroLeasing = fecharegistroLeasing;
    }
}
