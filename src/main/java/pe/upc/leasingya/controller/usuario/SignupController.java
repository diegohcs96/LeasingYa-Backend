package pe.upc.leasingya.controller.usuario;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.upc.leasingya.entity.Rol;
import pe.upc.leasingya.entity.Usuario;
import pe.upc.leasingya.enums.RolNombre;
import pe.upc.leasingya.payload.request.usuario.SignupRequest;
import pe.upc.leasingya.payload.response.MessageResponse;
import pe.upc.leasingya.service.IRolService;
import pe.upc.leasingya.service.IUsuarioService;
import pe.upc.leasingya.validation.UsuarioValidation;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/signup")
@CrossOrigin
public class SignupController {

    final PasswordEncoder passwordEncoder;

    final IUsuarioService usuarioService;

    final IRolService rolService;

    public SignupController(PasswordEncoder passwordEncoder, IUsuarioService usuarioService, IRolService rolService) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> SignupUsuario(@RequestBody SignupRequest signupRequest) {

        Set<UsuarioValidation> listUsuariosToValidate = new HashSet<>();

        //Quitando Tilde de Nombre
        String nombreUsuario = StringUtils.stripAccents(signupRequest.getNombreUsuario());

        //Quitando Tilde de Apellido
        String apellidoUsuario = StringUtils.stripAccents(signupRequest.getApellidoUsuario());

        usuarioService.ValidarUsuario(
                nombreUsuario.toUpperCase().trim(),
                apellidoUsuario.toUpperCase().trim(),
                signupRequest.getEmailUsuario(),
                signupRequest.getUsernameUsuario()
        ).forEach(usuario -> listUsuariosToValidate.add(new UsuarioValidation(
                nombreUsuario.toUpperCase().trim(),
                apellidoUsuario.toUpperCase().trim(),
                signupRequest.getEmailUsuario(),
                signupRequest.getUsernameUsuario()
        )));

        if (listUsuariosToValidate.size() < 1) {
            //Asignando Rol: Usuario
            Optional<Rol> dataRol = rolService.BuscarRol_By_NombreRol(RolNombre.ROLE_USER);

            if (dataRol.isPresent()) {
                Rol currentRol = dataRol.get();

                Set<Rol> listRoles = new HashSet<>();

                listRoles.add(currentRol);

                Usuario usuario = new Usuario(
                        nombreUsuario.toUpperCase().trim(),
                        apellidoUsuario.toUpperCase().trim(),
                        signupRequest.getEmailUsuario(),
                        signupRequest.getUsernameUsuario(),
                        passwordEncoder.encode(signupRequest.getPasswordUsuario()),
                        listRoles
                );

                usuarioService.GuardarUsuario(usuario);

                return new ResponseEntity<>(new MessageResponse("Se registró el Usuario correctamente."),
                        HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(new MessageResponse("Ocurrió un error al encontrar el Rol por asignar al " +
                        "Usuario."),
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("No se insertó los datos del Usuario por ya encontrarse " +
                    "registrado previamente."),
                    HttpStatus.CONFLICT);
        }
    }
}
