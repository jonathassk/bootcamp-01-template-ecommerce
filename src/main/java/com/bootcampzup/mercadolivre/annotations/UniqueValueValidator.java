package com.bootcampzup.mercadolivre.annotations;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
    private String domainAttribute;
    private Class<?> aClass;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UniqueValue params) {
        domainAttribute = params.fieldname();
        aClass = params.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("SELECT 1 FROM "+aClass.getName()+" WHERE "+domainAttribute+" =:value");
        query.setParameter("value", o);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1,"Foi encontrado mais de um "+aClass+" com o atributo"+domainAttribute+" = "+o);
        return list.isEmpty();
    }
}
