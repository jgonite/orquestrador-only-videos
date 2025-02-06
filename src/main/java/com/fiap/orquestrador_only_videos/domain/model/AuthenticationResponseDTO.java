package com.fiap.orquestrador_only_videos.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponseDTO {

	private String accessToken;
	private String refreshToken;
	
}
