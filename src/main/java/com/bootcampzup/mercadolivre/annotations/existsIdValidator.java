package com.bootcampzup.mercadolivre.annotations;

import io.jsonwebtoken.lang.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class existsIdValidator implements ConstraintValidator<ExistsId, Object> {
    @PersistenceContext
    private EntityManager manager;
    private String domainAttribute;
    private Class<?> aClass;

    @Override
    public void initialize(ExistsId params) {
        domainAttribute = params.fieldname();
        aClass = params.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("SELECT 1 FROM "+aClass.getName()+" WHERE "+domainAttribute+" =:value");
        query.setParameter("value", o);
        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <= 1, "Existe mais de uma classe "+aClass.getName()+" com o "+domainAttribute+" = "+o);
        return !list.isEmpty();
    }
}
