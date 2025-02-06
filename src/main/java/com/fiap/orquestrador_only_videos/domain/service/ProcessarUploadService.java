package com.fiap.orquestrador_only_videos.domain.service;

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
import com.fiap.orquestrador_only_videos.domain.exception.ProcessarException;
import com.fiap.orquestrador_only_videos.domain.model.AuthenticationResponseDTO;
import com.fiap.orquestrador_only_videos.domain.model.SolicitacaoUploadModel;
import com.fiap.orquestrador_only_videos.domain.repository.SolicitacaoUploadRepository;

@Service
public class ProcessarUploadService {

	@Autowired
	SolicitacaoUploadRepository solicitacaoRepository;

	@Value("${processamento.url}")
	private String processamentoUrl;
	private static final String processamentoResource = "/api/XXXX";
	@Autowired
	private RestTemplate restTemplate;

	public void processa(SolicitacaoUploadModel upload, AuthenticationResponseDTO tokens) throws ProcessarException {
		/*
		 * Integrar com servico de processamento
		 */
		try {

			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.APPLICATION_JSON);
			requestHeaders.set("Authorization", "Bearer ".concat(tokens.getAccessToken()));
			HttpEntity<String> requestEntity = new HttpEntity<>("", requestHeaders);

			String url = processamentoUrl.concat(processamentoResource);

			restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

			solicitacaoRepository.confirmarProcessamento(upload.getUuid());

		} catch (Exception e) {
			throw new ProcessarException(e.getMessage());
		}

	}

}
