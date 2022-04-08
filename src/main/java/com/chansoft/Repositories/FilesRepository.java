package com.chansoft.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.chansoft.Entities.FilesEntity;

@RepositoryRestResource(exported = false)
public interface FilesRepository extends CrudRepository<FilesEntity, Long> {

	public List<FilesEntity> findByFileDirectoryIgnoreCase(String FileDirectory);

}
