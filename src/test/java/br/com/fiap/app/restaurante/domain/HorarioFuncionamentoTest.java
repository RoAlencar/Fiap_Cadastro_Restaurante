package br.com.fiap.app.restaurante.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HorarioFuncionamentoTest {

    @Test
    public void HorarioFuncionamentoTest(){

        HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento();
        horarioFuncionamento.setAbertura(LocalTime.of(9,0));
        horarioFuncionamento.setFechamento(LocalTime.of(18,0));

        assertThat(horarioFuncionamento.getAbertura()).isEqualTo(LocalTime.of(9,0));
        assertThat(horarioFuncionamento.getFechamento()).isEqualTo(LocalTime.of(18,0));
    }
}
