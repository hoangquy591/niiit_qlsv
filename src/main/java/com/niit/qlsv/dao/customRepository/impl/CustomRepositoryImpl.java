package com.niit.qlsv.dao.customRepository.impl;

import com.niit.qlsv.dao.customRepository.CustomRepository;
import com.niit.qlsv.dao.entities.BaseEnt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class CustomRepositoryImpl<T extends BaseEnt> implements CustomRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> persistentClass;

    public CustomRepositoryImpl(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public Page<T> paginator(Pageable pageable, String query, String alias, Map<String, Object> params) {
        // Create jpa query for fetch result
        Query jpaQuery = getEntityManager().createQuery(query);
        jpaQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        jpaQuery.setMaxResults(pageable.getPageSize());
        if (params != null && params.size() >0) {
            params.forEach(jpaQuery::setParameter);
        }
        List<T> resultList = jpaQuery.getResultList();

        // Create query for count total result
        String jpaQueryTotalStr = query.replaceFirst("(?i)(select)(.+?)(from)", "select count(" + alias + ".id) from");
        Query jpaQueryTotal = getEntityManager().createQuery(jpaQueryTotalStr);
        if (params != null && params.size()>0) {
            params.forEach(jpaQueryTotal::setParameter);
        }
        long totalRecord = (long) jpaQueryTotal.getSingleResult();
        return new PageImpl<>(resultList, pageable, totalRecord);
    }
}
