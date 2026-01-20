package br.com.fiap.app.restaurante.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class HorarioFuncionamento {

    @JsonFormat(pattern = "HH:mm")
    @Schema(
            type = "string",
            example = "09:00",
            format = "time"
    )
    private LocalTime abertura;

    @JsonFormat(pattern = "HH:mm")
    @Schema(
            type = "string",
            example = "18:00",
            format = "time"
    )
    private LocalTime fechamento;
}
