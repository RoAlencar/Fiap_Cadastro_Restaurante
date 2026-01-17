package br.com.fiap.app.restaurante.application.dto.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BuscaRestauranteResponse extends RestauranteBaseResponse {

    public BuscaRestauranteResponse(RestauranteBaseResponse restauranteBaseResponse) {
        super(restauranteBaseResponse);
    }
}
