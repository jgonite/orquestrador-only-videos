package com.fiap.orquestrador_only_videos.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.orquestrador_only_videos.domain.exception.ProcessarException;
import com.fiap.orquestrador_only_videos.domain.model.SolicitacaoUploadModel;
import com.fiap.orquestrador_only_videos.domain.repository.SolicitacaoUploadRepository;

@Service
public class ProcessarUploadService {
	
	@Autowired SolicitacaoUploadRepository solicitacaoRepository;

	public void processa(SolicitacaoUploadModel upload) throws ProcessarException {
		/* 
		 * Integrar com servico de processamento
		 */
		solicitacaoRepository.confirmarProcessamento(upload.getUuid());
	}

}
