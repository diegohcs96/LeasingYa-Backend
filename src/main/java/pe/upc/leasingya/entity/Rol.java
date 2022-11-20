package pe.upc.leasingya.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import pe.upc.leasingya.enums.RolNombre;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "rol")
@Getter
@Setter
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_rol")
    private UUID idRol;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private RolNombre nombreRol;

    @ManyToMany(mappedBy = "rolesUsuario")
    Set<Usuario> usuariosRoles;

    //Constructores
    public Rol() {
    }

    public Rol(RolNombre nombreRol) {
        this.nombreRol = nombreRol;
    }
}
