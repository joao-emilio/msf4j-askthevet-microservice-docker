/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package me.joaoemilio.petclinic.microservice.askthevet.dao;


import me.joaoemilio.petclinic.microservice.askthevet.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * UserRepository class which is extended from AbstractRepository class.
 */

@Transactional
@Repository
public class AskTheVetRepository {

    @Autowired
    private EntityManagerFactory emf;

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void add(Question model) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();

        manager.persist(model);
        manager.getTransaction().commit();
        manager.close();
    }

    public void remove(long id) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.remove(manager.find(Question.class, id));
        manager.getTransaction().commit();
        manager.close();
    }

    public Question find(long id) {
        System.out.println( "Question id: " + id  );
        Question model = getEntityManager().find(Question.class, id);
        System.out.println( "repository find: " + id  );
        return model;
    }

    public List<Question> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Question> cq = cb.createQuery(Question.class);
        Root<Question> rootEntry = cq.from(Question.class);
        CriteriaQuery<Question> all = cq.select(rootEntry).where(  );
        TypedQuery<Question> allQuery = getEntityManager().createQuery(all);
        return allQuery.getResultList();
    }

    public List<Question> findAllByVetId(Long id) {
        Query query = getEntityManager().createQuery( "SELECT q FROM Question q where q.vetId = :id" ).setParameter( "id", id);
        List<Question> list = query.getResultList();
        return list;
    }
}
