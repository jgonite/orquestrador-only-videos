package com.fiap.orquestrador_only_videos.domain.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.fiap.orquestrador_only_videos.domain.exception.AutenticacaoException;

@Service
public class AutenticacaoService {
	
	public void autenticar(Map<String,String> headers) throws AutenticacaoException {
		/*
		 * Integrar com o servico de autenticacao. Throw erro se nao autenticar corretamente
		 */
	}

}
