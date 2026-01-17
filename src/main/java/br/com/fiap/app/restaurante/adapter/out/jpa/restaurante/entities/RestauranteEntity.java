package br.com.fiap.app.restaurante.adapter.out.jpa.restaurante.entities;

import br.com.fiap.app.restaurante.domain.Endereco;
import br.com.fiap.app.restaurante.domain.HorarioFuncionamento;
import br.com.fiap.app.restaurante.domain.Restaurante;
import br.com.fiap.app.restaurante.domain.enums.TipoRestaurante;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "CORE_RESTAURANTE")
public class RestauranteEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "logradouro", column = @Column(name = "ENDERECO_LOGRADOURO")),
            @AttributeOverride(name = "numero", column = @Column(name = "ENDERECO_NUMERO")),
            @AttributeOverride(name = "complemento", column = @Column(name = "ENDERECO_COMPLEMENTO")),
            @AttributeOverride(name = "cidade", column = @Column(name = "ENDERECO_CIDADE")),
            @AttributeOverride(name = "cep", column = @Column(name = "ENDERECO_CEP"))
    })
    private Endereco endereco;

    @Access(AccessType.FIELD)
    @Column(name = "HORARIO_FUNCIONAMENTO")
    private HorarioFuncionamento horarioFuncionamento;

    @Column(name = "DONO_RESTAURANTE")
    private String donoRestaurante;

    @Access(AccessType.FIELD)
    @Column(name = "TIPO_RESTAURANTE")
    private TipoRestaurante tipoRestaurante;

    public Restaurante toDomain(){
        return Restaurante.builder()
                .id(this.id)
                .nome(this.nome)
                .endereco(this.endereco)
                .horarioFuncionamento(this.horarioFuncionamento)
                .donoRestaurante(this.donoRestaurante)
                .tipoRestaurante(this.tipoRestaurante)
                .build();
    }
}
