package br.com.fiap.app.restaurante.infrastructure.exception;

import br.com.fiap.app.restaurante.infrastructure.exception.custom.AddressRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.DonoRestaurenteRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.HorarioAberturaRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.HorarioFechamentoRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.NameRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.NoChangesDetectedException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RequestRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RestauranteNotFoundException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.TipoRestauranteRequiredException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(AddressRequiredException.class)
    public ResponseEntity<ProblemDetail> handleAddressRequiredException(AddressRequiredException ex, HttpServletRequest request) {
        log.warn("[Restaurante - Cadastra Restaurante] O Endereco do restaurante é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "O Endereco do restaurante é obrigatório",
                        request));
    }



    @ExceptionHandler(DonoRestaurenteRequiredException.class)
    public ResponseEntity<ProblemDetail> handleDonoRequiredException(DonoRestaurenteRequiredException ex, HttpServletRequest request) {
        log.warn("[Restaurante - Cadastra Restaurante] O dono do restaurante é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "O dono do restaurante é obrigatório",
                        request));
    }

    @ExceptionHandler(HorarioAberturaRequiredException.class)
    public ResponseEntity<ProblemDetail> handleHorarioAberturaRequiredException(HorarioAberturaRequiredException ex, HttpServletRequest request) {
        log.warn("[Restaurante - Cadastra Restaurante] O horario de abertura do restaurante é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "O horario de abertura do restaurante é obrigatório",
                        request));
    }

    @ExceptionHandler(HorarioFechamentoRequiredException.class)
    public ResponseEntity<ProblemDetail> handleHorarioFechamentoRequiredException(HorarioFechamentoRequiredException ex, HttpServletRequest request) {
        log.warn("[Restaurante - Cadastra Restaurante] O horario de fechamento do restaurante é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "O horario de fechamento do restaurante é obrigatório",
                        request));
    }

    @ExceptionHandler(NameRequiredException.class)
    public ResponseEntity<ProblemDetail> handleNameRequiredException(NameRequiredException ex, HttpServletRequest request) {
        log.warn("[Restaurante - Cadastra Restaurante] O Nome do restaurante é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "O Nome do restaurante é obrigatório",
                        request));
    }

    @ExceptionHandler(RequestRequiredException.class)
    public ResponseEntity<ProblemDetail> handleRequestRequiredException(RequestRequiredException ex, HttpServletRequest request) {
        log.warn("[Restaurante - Cadastra Restaurante] A requisição é obrigatória");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "A Requisição é obrigatória",
                        request));
    }

    @ExceptionHandler(RestauranteNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleRestauranteNotFoundException(RestauranteNotFoundException ex, HttpServletRequest request) {
        String httpMethod = request.getMethod();
        log.error("[Restaurante - {}] O restaurante não foi encontrado.", httpMethod);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildProblemDetail(HttpStatus.NOT_FOUND,"O restaurante não foi encontrado",request));
    }

    @ExceptionHandler(TipoRestauranteRequiredException.class)
    public ResponseEntity<ProblemDetail> handleTipoRestauranteRequiredException(RestauranteNotFoundException ex, HttpServletRequest request) {
        log.warn("[Restaurante - Cadastra Restaurante] O Tipo do restaurante é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "O Tipo do restaurante é obrigatório",
                        request));
    }

    @ExceptionHandler(NoChangesDetectedException.class)
    public ResponseEntity<ProblemDetail> handleNoChangesDetectedException(NoChangesDetectedException ex,
                                                                          HttpServletRequest request) {
        log.error("[Restaurante - Atualiza Restaurante ] Não foram detectadas alterações nos dados do Restaurante");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST,
                        "Não foram detectadas alterações nos dados do Restaurante",
                        request));
    }



    private ProblemDetail buildProblemDetail(HttpStatus status, String detail, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setTitle(status.getReasonPhrase());
        problem.setDetail(detail);
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setType(URI.create("https://api.fiap.com/errors/" + status.value()));
        return problem;
    }
}
