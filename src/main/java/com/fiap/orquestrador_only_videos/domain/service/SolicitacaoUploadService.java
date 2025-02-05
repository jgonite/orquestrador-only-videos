package com.fiap.orquestrador_only_videos.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.orquestrador_only_videos.domain.exception.IniciarException;
import com.fiap.orquestrador_only_videos.domain.model.SolicitacaoUploadModel;
import com.fiap.orquestrador_only_videos.domain.repository.SolicitacaoUploadRepository;

@Service
public class SolicitacaoUploadService {
	
	@Autowired SolicitacaoUploadRepository solicitacaoRepository;

	public SolicitacaoUploadModel iniciar() throws IniciarException {
		return solicitacaoRepository.createNewSolicitacao();
	}
	
}
