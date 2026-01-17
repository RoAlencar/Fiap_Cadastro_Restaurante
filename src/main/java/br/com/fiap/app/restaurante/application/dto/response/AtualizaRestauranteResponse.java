package br.com.fiap.app.restaurante.application.dto.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AtualizaRestauranteResponse extends RestauranteBaseResponse {
    public AtualizaRestauranteResponse(RestauranteBaseResponse restauranteBaseResponse) {
        super(restauranteBaseResponse);
    }
}
