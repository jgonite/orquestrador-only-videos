package com.fiap.orquestrador_only_videos.adapter.http.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.orquestrador_only_videos.domain.exception.AutenticacaoException;
import com.fiap.orquestrador_only_videos.domain.exception.IniciarException;
import com.fiap.orquestrador_only_videos.domain.exception.ProcessarException;
import com.fiap.orquestrador_only_videos.domain.model.AuthenticationResponseDTO;
import com.fiap.orquestrador_only_videos.domain.model.PostVideoRequestModel;
import com.fiap.orquestrador_only_videos.domain.model.PostVideoResponseModel;
import com.fiap.orquestrador_only_videos.domain.model.SolicitacaoUploadModel;
import com.fiap.orquestrador_only_videos.domain.service.AutenticacaoService;
import com.fiap.orquestrador_only_videos.domain.service.ProcessarUploadService;
import com.fiap.orquestrador_only_videos.domain.service.SolicitacaoUploadService;

@RestController("/api/upload")
public class OnlyVideosController {
	
	
	@Autowired private SolicitacaoUploadService solicitacaoUploadService;
	@Autowired private ProcessarUploadService processarUploadService;
	@Autowired private AutenticacaoService autenticacaoService;
	
	@PostMapping
	public ResponseEntity<PostVideoResponseModel> uploadVideo(
			@RequestHeader Map<String, String> headers,
			@RequestBody PostVideoRequestModel request) {
		
		PostVideoResponseModel response = new PostVideoResponseModel();
		try {
			AuthenticationResponseDTO tokens = autenticacaoService.autenticar(headers);
			SolicitacaoUploadModel solicitacao = solicitacaoUploadService.iniciar();
			processarUploadService.processa(solicitacao, tokens);
			
			response.setUuid(solicitacao.getUuid());
		} catch (AutenticacaoException ae) {
			return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(response);
		} catch (IniciarException ie) {
			return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(response);
		} catch (ProcessarException pe) {
			return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(response);
		}
		return ResponseEntity.ok(response);
		
	}
	
}
