package br.com.fiap.app.restaurante.application.dto.request;

import br.com.fiap.app.restaurante.domain.Endereco;
import br.com.fiap.app.restaurante.domain.HorarioFuncionamento;
import br.com.fiap.app.restaurante.domain.enums.TipoRestaurante;
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
public class AtualizaRestauranteDto {

    private Long id;
    private String nome;
    private Endereco endereco;
    private HorarioFuncionamento horarioFuncionamento;
    private String donoRestaurante;
    private TipoRestaurante tipoRestaurante;

    public AtualizaRestauranteDto(AtualizaRestauranteDto atualizaRestauranteDto) {
        this.nome = atualizaRestauranteDto.getNome();
        this.endereco = atualizaRestauranteDto.getEndereco();;
        this.horarioFuncionamento = atualizaRestauranteDto.getHorarioFuncionamento();
        this.donoRestaurante = atualizaRestauranteDto.getDonoRestaurante();
        this.tipoRestaurante = atualizaRestauranteDto.getTipoRestaurante();
    }
}
