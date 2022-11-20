package pe.upc.leasingya.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_usuario")
    private UUID idUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "apellido_usuario")
    private String apellidoUsuario;

    @Column(name = "email_usuario")
    private String emailUsuario;

    @Column(name = "username_usuario")
    private String usernameUsuario;

    @Column(name = "password_usuario")
    private String passwordUsuario;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id_rol"))
    private Set<Rol> rolesUsuario = new HashSet<>();

    @OneToMany(mappedBy = "usuarioLeasing")
    private Set<Leasing> leasingsUsuario;

    //Constructores
    public Usuario() {
    }

    public Usuario(String nombreUsuario, String apellidoUsuario, String emailUsuario, String usernameUsuario,
                   String passwordUsuario, Set<Rol> rolesUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.emailUsuario = emailUsuario;
        this.usernameUsuario = usernameUsuario;
        this.passwordUsuario = passwordUsuario;
        this.rolesUsuario = rolesUsuario;
    }

    public Usuario(String usernameUsuario, String passwordUsuario) {
        this.usernameUsuario = usernameUsuario;
        this.passwordUsuario = passwordUsuario;
    }

    public Usuario(UUID idUsuario, String nombreUsuario, String apellidoUsuario, String emailUsuario,
                   String usernameUsuario, String passwordUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.emailUsuario = emailUsuario;
        this.usernameUsuario = usernameUsuario;
        this.passwordUsuario = passwordUsuario;
    }

    public Usuario(UUID idUsuario, String nombreUsuario, String apellidoUsuario, String emailUsuario,
                   String usernameUsuario, String passwordUsuario, Set<Rol> rolesUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.emailUsuario = emailUsuario;
        this.usernameUsuario = usernameUsuario;
        this.passwordUsuario = passwordUsuario;
        this.rolesUsuario = rolesUsuario;
    }

    public Usuario(String nombreUsuario, String apellidoUsuario, String emailUsuario, String usernameUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.emailUsuario = emailUsuario;
        this.usernameUsuario = usernameUsuario;
    }

    public Usuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }
}
