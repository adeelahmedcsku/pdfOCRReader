package com.chansoft.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "files")
@Getter
@Setter
public class FilesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fileID")
	private Long fileID;

	@Column(name = "uploaded", columnDefinition = "tinyint(1)")
	@NonNull
	private boolean uploaded;

	@Column(name = "ocrCompleted", columnDefinition = "tinyint(1)")
	@NonNull
	private boolean ocrCompleted;

	@Column(name = "ocrDownloaded", columnDefinition = "tinyint(1)")
	@NonNull
	private boolean ocrDownloaded;

	@Column(name = "ocrIndexed", columnDefinition = "tinyint(1)")
	@NonNull
	private boolean ocrIndexed;

	@Column(name = "ocrJSONFileName", columnDefinition = "json")
	private String ocrJSONFileName;

	@Column(name = "ocrJSONFileName", columnDefinition = "varchar(1000)")
	private String fileDirectory;
}
