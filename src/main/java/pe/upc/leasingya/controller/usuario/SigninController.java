package pe.upc.leasingya.controller.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.upc.leasingya.entity.Usuario;
import pe.upc.leasingya.payload.request.usuario.SigninRequest;
import pe.upc.leasingya.payload.response.MessageResponse;
import pe.upc.leasingya.security.JwtResponse;
import pe.upc.leasingya.security.jwt.JwtProvider;
import pe.upc.leasingya.security.service.UserDetailsImpl;
import pe.upc.leasingya.service.IUsuarioService;

import java.util.Optional;

@RestController
@RequestMapping("/api/signin")
@CrossOrigin
public class SigninController {

    final AuthenticationManager authenticationManager;

    final JwtProvider jwtProvider;

    final IUsuarioService usuarioService;

    public SigninController(AuthenticationManager authenticationManager, JwtProvider jwtProvider,
                            IUsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> SigninUsuario(@RequestBody SigninRequest signinRequest) {

        Optional<Usuario> dataUsuario =
                usuarioService.BuscarUsuario_SiExiste_By_UsernameUsuarioOrEmailUsuario(signinRequest.getUsernameUsuario());

        if (dataUsuario.isPresent()) {
            Usuario currentUsuario = dataUsuario.get();

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            if (bCryptPasswordEncoder.matches(signinRequest.getPasswordUsuario(), currentUsuario.getPasswordUsuario())) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                currentUsuario.getUsernameUsuario(),
                                signinRequest.getPasswordUsuario()
                        ));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = jwtProvider.generateJwtToken(authentication);

                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

                if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                    return new ResponseEntity<>(new JwtResponse(
                            jwt,
                            userDetails.getIdUsuario(),
                            userDetails.getNombreUsuario() + " " + userDetails.getApellidoUsuario(),
                            userDetails.getUsername(),
                            userDetails.getAuthorities()
                    ),
                            HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new MessageResponse("No cumple con los permisos correspondientes para " +
                            "acceder al sistema."),
                            HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(new MessageResponse("La contraseña ingresada es incorrecta."),
                        HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("Ocurrió un error al encontrar el Usuario en el Sistema."),
                    HttpStatus.NOT_FOUND);
        }
    }
}
