package org.application.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.transaction.Transactional;

import org.application.domain.PersonEntity;

@Named
public class PersonService extends BaseService<PersonEntity> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public PersonService(){
        super(PersonEntity.class);
    }
    
    @Transactional
    public List<PersonEntity> findAllPersonEntities() {
        
        return entityManager.createQuery("SELECT o FROM Person o ", PersonEntity.class).getResultList();
    }
    
}
