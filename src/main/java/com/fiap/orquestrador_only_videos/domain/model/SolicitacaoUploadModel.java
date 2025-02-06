package com.fiap.orquestrador_only_videos.domain.model;

import com.fiap.orquestrador_only_videos.domain.enumeration.UploadStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitacaoUploadModel {

	public String uuid;
	public UploadStatus status;
	public LinkModel link;
	
	
}
