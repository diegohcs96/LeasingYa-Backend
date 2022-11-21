package pe.upc.leasingya.controller.leasing;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.upc.leasingya.entity.Leasing;
import pe.upc.leasingya.entity.LeasingHeader;
import pe.upc.leasingya.entity.LeasingResultado;
import pe.upc.leasingya.entity.Usuario;
import pe.upc.leasingya.payload.request.leasing.LeasingRequest;
import pe.upc.leasingya.payload.response.MessageResponse;
import pe.upc.leasingya.payload.response.leasing.LeasingResponse;
import pe.upc.leasingya.service.ILeasingHeaderService;
import pe.upc.leasingya.service.ILeasingResultadoService;
import pe.upc.leasingya.service.ILeasingService;
import pe.upc.leasingya.service.IUsuarioService;
import pe.upc.leasingya.validation.LeasingValidation;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LeasingController {

    final ILeasingService leasingService;

    final ILeasingHeaderService leasingHeaderService;

    final ILeasingResultadoService leasingResultadoService;

    final IUsuarioService usuarioService;

    public LeasingController(ILeasingService leasingService, ILeasingHeaderService leasingHeaderService,
                             ILeasingResultadoService leasingResultadoService, IUsuarioService usuarioService) {
        this.leasingService = leasingService;
        this.leasingHeaderService = leasingHeaderService;
        this.leasingResultadoService = leasingResultadoService;
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

            Set<LeasingValidation> listLeasingsToValidate = new HashSet<>();

            leasingService.ValidarLeasing(
                    leasingRequest.getNombreLeasing(),
                    currentUsuario
            ).forEach(leasing -> listLeasingsToValidate.add(new LeasingValidation(
                    leasingRequest.getNombreLeasing(),
                    currentUsuario
            )));

            if (listLeasingsToValidate.size() < 1) {
                //Fecha y Hora Actual Local - Lima
                ZoneId zoneId = ZoneId.of("America/Lima");
                LocalDateTime currentFechaHora =
                        LocalDateTime.now().atZone(zoneId).toLocalDateTime();

                Leasing leasing = new Leasing(
                        leasingRequest.getNombreLeasing(),
                        currentFechaHora,
                        currentUsuario
                );

                leasingService.GuardarLeasing(leasing);

                LeasingHeader leasingheader = new LeasingHeader(
                        leasingRequest.getDatosLeasing().getPrecioVenta(),
                        leasingRequest.getDatosLeasing().getCantidadAnio(),
                        leasingRequest.getDatosLeasing().getFrecuenciaPago(),
                        leasingRequest.getDatosLeasing().getDiasAnio(),
                        leasingRequest.getDatosLeasing().getPorcentajeTEA(),
                        leasingRequest.getDatosLeasing().getPorcentajeIGV(),
                        leasingRequest.getDatosLeasing().getPorcentajeImpuestoRenta(),
                        leasingRequest.getDatosLeasing().getPorcentajeRecompra(),
                        leasingRequest.getDatosLeasing().getCostoNotarial(),
                        leasingRequest.getDatosLeasing().getCostoRegistral(),
                        leasingRequest.getDatosLeasing().getTasacion(),
                        leasingRequest.getDatosLeasing().getComisionEstudio(),
                        leasingRequest.getDatosLeasing().getComisionActivacion(),
                        leasingRequest.getDatosLeasing().getComisionPeriodica(),
                        leasingRequest.getDatosLeasing().getPorcentajeSeguroRiesgo(),
                        leasingRequest.getDatosLeasing().getTasaDescuentoKs(),
                        leasingRequest.getDatosLeasing().getTasaDescuentoWACC(),
                        leasing
                );

                leasingHeaderService.Guardar(leasingheader);

                //Iniciando Calculo de Resultados
                //-- IGV
                Double igv = (leasingRequest.getDatosLeasing().getPrecioVenta() /
                        (1 + (leasingRequest.getDatosLeasing().getPorcentajeIGV() / 100)) *
                        (leasingRequest.getDatosLeasing().getPorcentajeIGV() / 100));

                //-- Valor Venta Activo
                double valorVentaActivo = leasingRequest.getDatosLeasing().getPrecioVenta() - igv;

                //-- Monto Leasing
                Double montoLeasing = valorVentaActivo + (
                        leasingRequest.getDatosLeasing().getCostoNotarial() +
                                leasingRequest.getDatosLeasing().getCostoRegistral() +
                                leasingRequest.getDatosLeasing().getTasacion() +
                                leasingRequest.getDatosLeasing().getComisionEstudio() +
                                leasingRequest.getDatosLeasing().getComisionActivacion());

                //-- Porcentaje TEP
                Double porcentajeTEP =
                        ((Math.pow(
                                1 + (leasingRequest.getDatosLeasing().getPorcentajeTEA() / 100),
                                (Double.valueOf(leasingRequest.getDatosLeasing().getFrecuenciaPago()) /
                                        Double.valueOf(leasingRequest.getDatosLeasing().getDiasAnio())))
                                - 1) * 100);

                //-- Cuotas Año
                Integer cuotasAnio = (int) Math.round((Double.valueOf(leasingRequest.getDatosLeasing().getDiasAnio())) /
                        (Double.valueOf(leasingRequest.getDatosLeasing().getFrecuenciaPago())));

                //-- Total Cuotas
                Integer totalCuotas = cuotasAnio * leasingRequest.getDatosLeasing().getCantidadAnio();

                //-- Seguro Riesgo
                Double seguroRiesgo = (leasingRequest.getDatosLeasing().getPorcentajeSeguroRiesgo() / 100) *
                        leasingRequest.getDatosLeasing().getPrecioVenta() / cuotasAnio;

                LeasingResultado leasingresultado = new LeasingResultado(
                        igv,
                        valorVentaActivo,
                        montoLeasing,
                        porcentajeTEP,
                        cuotasAnio,
                        totalCuotas,
                        seguroRiesgo,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        leasing
                );

                leasingResultadoService.GuardarLeasingResultado(leasingresultado);

                return new ResponseEntity<>(new MessageResponse("Se registró el Leasing correctamente."),
                        HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(new MessageResponse("Ya existe un Leasing previamente registrado con " +
                        "el mismo nombre."),
                        HttpStatus.CONFLICT);
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
