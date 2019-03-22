package br.com.paullus.faireconsumer.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.com.paullus.faireconsumer.entities.IFaireEntity;

@Repository
public class GenericRepository<Entity extends IFaireEntity> implements IGenericRepository<Entity> {
    private static final Logger logger = LoggerFactory.getLogger(GenericRepository.class);
    
	private List<Entity> list = new ArrayList<>(); 

	@Override
	public Entity create(Entity entity) {
		logger.info("Adding entity [" + entity.getClass().getSimpleName() + "] with id '" + entity.getId() + "'.");
		Entity ent = get(entity.getId());
		if (ent == null)
		{
			ent = entity;
			list.add(ent);
		}
		else
			logger.warn("Entity [" + entity.getClass().getSimpleName() + "] already exists with id '" + entity.getId() + "'. Ignoring.");
		return ent;
	}

	@Override
	public void delete(IFaireEntity entity) {
		logger.info("Removing entity [" + entity.getClass().getSimpleName() + "] with id '" + entity.getId() + "'.");
		Entity ent = get(entity.getId());
		if (ent != null)
			list.remove(ent);
		else
			logger.info("Entity [" + entity.getClass().getSimpleName() + "] not found with id '" + entity.getId() + "'. Ignoring.");
	}

	@Override
	public List<Entity> list() {
		return Collections.unmodifiableList(list);
	}

	@Override
	public Entity get(String id) {
		return list.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
	}
}
