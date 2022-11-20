package pe.upc.leasingya.controller.leasing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.upc.leasingya.entity.Leasing;
import pe.upc.leasingya.entity.Usuario;
import pe.upc.leasingya.payload.request.leasing.LeasingRequest;
import pe.upc.leasingya.payload.response.MessageResponse;
import pe.upc.leasingya.payload.response.leasing.LeasingResponse;
import pe.upc.leasingya.service.ILeasingService;
import pe.upc.leasingya.service.IUsuarioService;
import pe.upc.leasingya.validation.LeasingValidation;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LeasingController {

    private final static Logger logger = LoggerFactory.getLogger(LeasingController.class);

    final ILeasingService leasingService;

    final IUsuarioService usuarioService;

    public LeasingController(ILeasingService leasingService, IUsuarioService usuarioService) {
        this.leasingService = leasingService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/leasing/save")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> RegistrarLeasing(@RequestHeader(value = "idUsuario") String idUsuario,
                                              @RequestBody LeasingRequest leasingRequest) {

        UUID currentIdUsuario = UUID.fromString(idUsuario);

        Optional<Usuario> dataUsuario = usuarioService.BuscarUsuario_SiExiste(currentIdUsuario);

        if (dataUsuario.isPresent()) {
            Usuario currentUsuario = dataUsuario.get();

            String defaultNombreLeasing = "Leasing Financiero ";

            //Fecha y Hora Actual Local - Lima
            ZoneId zoneId = ZoneId.of("America/Lima");
            LocalDateTime currentFechaHora =
                    LocalDateTime.now().atZone(zoneId).toLocalDateTime();

            if (!Objects.equals(leasingRequest.getNombreLeasing(), "")) {
                Set<LeasingValidation> listLeasingsToValidate = new HashSet<>();

                leasingService.ValidarLeasing(
                        leasingRequest.getNombreLeasing(),
                        currentUsuario
                ).forEach(leasing -> listLeasingsToValidate.add(new LeasingValidation(
                        leasingRequest.getNombreLeasing(),
                        currentUsuario
                )));

                if (listLeasingsToValidate.size() < 1) {
                    Leasing leasing = new Leasing(
                            leasingRequest.getNombreLeasing(),
                            currentFechaHora,
                            currentUsuario
                    );

                    leasingService.GuardarLeasing(leasing);

                    return new ResponseEntity<>(new MessageResponse("Se registró el Leasing correctamente."),
                            HttpStatus.ACCEPTED);
                } else {
                    return new ResponseEntity<>(new MessageResponse("Ya existe un Leasing previamente registrado con " +
                            "el mismo nombre."),
                            HttpStatus.CONFLICT);
                }
            } else {
                logger.info("Ingresando a Flujo de Insertar Nombre por Defecto.");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                String currentFechaHoraFormat = currentFechaHora.format(formatter);

                Leasing leasing = new Leasing(
                        defaultNombreLeasing + currentFechaHoraFormat,
                        currentFechaHora,
                        currentUsuario
                );

                leasingService.GuardarLeasing(leasing);

                return new ResponseEntity<>(new MessageResponse("Se registró el Leasing correctamente."),
                        HttpStatus.ACCEPTED);
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("Ocurrió un error al encontrar el presente Usuario."),
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/leasings/usuario/{idUsuario}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> MostrarHistorialLeasing(@PathVariable("idUsuario") UUID idUsuario) {

        Optional<Usuario> dataUsuario = usuarioService.BuscarUsuario_SiExiste(idUsuario);

        if (dataUsuario.isPresent()) {
            Usuario currentUsuario = dataUsuario.get();

            Set<LeasingResponse> listLeasings = new HashSet<>();

            leasingService.MostrarLeasings_By_Usuario(currentUsuario)
                    .forEach(leasing -> listLeasings.add(new LeasingResponse(
                            leasing.getIdLeasing(),
                            leasing.getNombreLeasing(),
                            leasing.getFecharegistroLeasing()
                    )));

            return new ResponseEntity<>(listLeasings,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Ocurrió un error al encontrar el presente Usuario."),
                    HttpStatus.NOT_FOUND);
        }
    }
}
