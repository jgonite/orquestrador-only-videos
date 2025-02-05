package com.fiap.orquestrador_only_videos.domain.repository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fiap.orquestrador_only_videos.domain.enumeration.UploadStatus;
import com.fiap.orquestrador_only_videos.domain.model.SolicitacaoUploadModel;

@Repository
public class SolicitacaoUploadRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public SolicitacaoUploadModel createNewSolicitacao() {

		SolicitacaoUploadModel s = new SolicitacaoUploadModel();
		s.setStatus(UploadStatus.INICIADO);
		s.setUuid(UUID.randomUUID().toString());

		jdbcTemplate.update("INSERT INTO solicitacoesUpload (guid, status) VALUES (?, ?)", s.getUuid(),
				s.getStatus().name());
		return s;
	}

	public int confirmarProcessamento(String uuid) {

		return jdbcTemplate.update("UPDATE solicitacoesUpload SET status = ? WHERE uuid = ?",
				UploadStatus.EM_ANDAMENTO_1.name(), uuid);
	}

}
