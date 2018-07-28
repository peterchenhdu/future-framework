/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.services;

//import org.springframework.orm.jpa.support.JpaDaoSupport;
//TODO 重新实现该类
import java.util.List;

/**
 * Implementation of the ServiceRegistryDao based on JPA.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public final class JpaServiceRegistryDaoImpl
//        extends JpaDaoSupport implements
//    ServiceRegistryDao
{

    public boolean delete(final RegisteredService registeredService) {
//        getJpaTemplate().remove(
//            getJpaTemplate().contains(registeredService) ? registeredService
//                : getJpaTemplate().merge(registeredService));
        return true;
    }

    public List<RegisteredService> load() {
//        return getJpaTemplate().find("select r from AbstractRegisteredService r");
        return null;
    }

    public RegisteredService save(final RegisteredService registeredService) {
//        final boolean isNew = registeredService.getId() == -1;

//        final RegisteredService r = getJpaTemplate().merge(registeredService);
//
//        if (!isNew) {
//            getJpaTemplate().persist(r);
//        }
        
//        return r;
        return null;
    }

    public RegisteredService findServiceById(final long id) {
//        return getJpaTemplate().find(AbstractRegisteredService.class, id);
        return null;
    }
}
