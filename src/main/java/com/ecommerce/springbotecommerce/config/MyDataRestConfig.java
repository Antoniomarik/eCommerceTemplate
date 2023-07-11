package com.ecommerce.springbotecommerce.config;

import com.ecommerce.springbotecommerce.Entity.Product;
import com.ecommerce.springbotecommerce.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        HttpMethod[] unsupportedMethods = {HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.POST};

        //Product
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedMethods)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedMethods));

        //ProductCategory
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedMethods)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedMethods));

        //call an intenral helpet method to expose ids;
        exposeIds(config);
    }
       private void exposeIds (RepositoryRestConfiguration config) {

           // get list of all entities
            Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

            //create array of entities
            List<Class> entityClasses = new ArrayList<>();

            for(EntityType tempentityType : entities){
                entityClasses.add(tempentityType.getJavaType());

                //expose entities
                Class[] domainTypes = entityClasses.toArray(new Class[0]);
                config.exposeIdsFor(domainTypes);
        }
    }
}
