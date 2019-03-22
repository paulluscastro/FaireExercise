package br.com.paullus.faireconsumer.repositories;

import java.util.List;

import br.com.paullus.faireconsumer.entities.IFaireEntity;

public interface IGenericRepository<Entity extends IFaireEntity> {
	Entity create(Entity entity);
	void delete(Entity entity);
	List<Entity> list();
	Entity get(String id);
}
