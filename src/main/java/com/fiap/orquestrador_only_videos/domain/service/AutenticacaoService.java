package com.fiap.orquestrador_only_videos.domain.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fiap.orquestrador_only_videos.domain.exception.AutenticacaoException;
import com.fiap.orquestrador_only_videos.domain.model.AuthenticationRequestDTO;
import com.fiap.orquestrador_only_videos.domain.model.AuthenticationResponseDTO;

@Service
public class AutenticacaoService {
	
	@Value("${auth.url}")
	private String authUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	private static final String usernameHeader = "X-Application-Username";
	private static final String passwordHeader = "X-Application-Password";
	private static final String autenticacaoResource = "/api/auth";
	private static final String autenticacaoRefreshResource = "/api/auth/refresh";
	
	public AuthenticationResponseDTO autenticar(Map<String,String> headers) throws AutenticacaoException {
		/*
		 * Integrar com o servico de autenticacao. Throw erro se nao autenticar corretamente
		 */
		try {

			
			final AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO();
			requestDTO.setUsername(headers.get(usernameHeader));
			requestDTO.setPassword(headers.get(passwordHeader));
			
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<AuthenticationRequestDTO> requestEntity = new HttpEntity<>(requestDTO, requestHeaders);
	        
	        String url = authUrl.concat(autenticacaoResource);
	        
	        ResponseEntity<AuthenticationResponseDTO> response = restTemplate.exchange(
	                url,
	                HttpMethod.POST,
	                requestEntity,
	                AuthenticationResponseDTO.class
	        );
	        
	        return response.getBody();
			
		} catch (Exception e) {
			throw new AutenticacaoException(e.getMessage());
		}

		
	}

}
