package br.com.fiap.app.restaurante.application.dto.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CriarRestauranteResponse extends RestauranteBaseResponse {

    public CriarRestauranteResponse(RestauranteBaseResponse restauranteBaseResponse) {
        super(restauranteBaseResponse);
    }
}
