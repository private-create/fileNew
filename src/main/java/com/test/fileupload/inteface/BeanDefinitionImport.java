package com.test.fileupload.inteface;

import com.test.fileupload.User;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class BeanDefinitionImport implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        RootBeanDefinition user =new RootBeanDefinition(User.class);

        registry.registerBeanDefinition("user",user);

    }
}
