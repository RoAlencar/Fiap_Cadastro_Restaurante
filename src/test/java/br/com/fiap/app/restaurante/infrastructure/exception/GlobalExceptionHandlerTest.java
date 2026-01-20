package br.com.fiap.app.restaurante.infrastructure.exception;

import br.com.fiap.app.restaurante.infrastructure.exception.custom.AddressRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.DonoRestaurenteRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.HorarioAberturaRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.HorarioFechamentoRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.NameRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.NoChangesDetectedException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RequestRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RestauranteNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();


    @Test
    public void handlerAddressRequiredException() {
        AddressRequiredException exception = new AddressRequiredException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/restaurantes");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleAddressRequiredException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O Endereco do restaurante é obrigatório", response.getBody().getDetail());
    }

    @Test
    public void handlerDonoRestaurenteRequiredException() {
        DonoRestaurenteRequiredException exception = new DonoRestaurenteRequiredException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/restaurantes");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleDonoRequiredException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O dono do restaurante é obrigatório", response.getBody().getDetail());
    }

    @Test
    public void handlerHorarioAberturaRequiredException() {
        HorarioAberturaRequiredException exception = new HorarioAberturaRequiredException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/restaurantes");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleHorarioAberturaRequiredException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O horario de abertura do restaurante é obrigatório", response.getBody().getDetail());
    }

    @Test
    public void handlerHorarioFechamentoRequiredException() {
        HorarioFechamentoRequiredException exception = new HorarioFechamentoRequiredException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/restaurantes");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleHorarioFechamentoRequiredException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O horario de fechamento do restaurante é obrigatório", response.getBody().getDetail());
    }

    @Test
    public void handlerNameRequiredException() {
        NameRequiredException exception = new NameRequiredException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/restaurantes");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleNameRequiredException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O Nome do restaurante é obrigatório", response.getBody().getDetail());
    }


    @Test
    public void handlerRequestRequiredException() {
        RequestRequiredException exception = new RequestRequiredException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/restaurantes");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleRequestRequiredException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("A Requisição é obrigatória", response.getBody().getDetail());
    }

    @Test
    public void handleRestauranteNotFoundException() {
        RestauranteNotFoundException exception = new RestauranteNotFoundException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/restaurantes/1");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleRestauranteNotFoundException(exception, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("O restaurante não foi encontrado", response.getBody().getDetail());
    }


    @Test
    public void handleNoChangesDetectedException() {
        NoChangesDetectedException exception = new NoChangesDetectedException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/restaurantes/1");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleNoChangesDetectedException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Não foram detectadas alterações nos dados do Restaurante", response.getBody().getDetail());
    }


}
