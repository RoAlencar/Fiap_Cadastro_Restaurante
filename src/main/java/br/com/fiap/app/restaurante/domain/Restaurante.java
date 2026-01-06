package br.com.fiap.app.restaurante.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurante {

    private Long id;
    private String nome;
    private Endereco endereco;
    private HorarioFuncionamento horarioFuncionamento;
    private String donoRestaurante;


}
