package com.chen.nacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Properties;

/**
 * @author chenzhiying
 * @date 2023-05-24
 **/
@Slf4j
public class BeanPostProcessorsRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private String version;

    private Environment environment;

    public static final String GLOBAL_NACOS_PROPERTIES_BEAN_NAME = "globalNacosProperties";

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attributes = AnnotationAttributes
                .fromMap(importingClassMetadata
                        .getAnnotationAttributes(EnableDemo.class.getName()));
        registerGlobalProperties(attributes, registry, environment, GLOBAL_NACOS_PROPERTIES_BEAN_NAME);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public static void registerGlobalProperties(AnnotationAttributes attributes,
                                                     BeanDefinitionRegistry registry, PropertyResolver propertyResolver,
                                                     String beanName) {
        if (attributes == null) {
            return; // Compatible with null
        }
        AnnotationAttributes globalPropertiesAttributes = attributes
                .getAnnotation("globalProperties");
        Properties globalProperties = resolveProperties(globalPropertiesAttributes,
                propertyResolver);
        registerSingleton(registry, beanName, globalProperties);
    }

    public static void registerSingleton(BeanDefinitionRegistry registry, String beanName,
                                        Object singletonObject) {
        SingletonBeanRegistry beanRegistry = null;
        if (registry instanceof SingletonBeanRegistry) {
            beanRegistry = (SingletonBeanRegistry) registry;
        }
        else if (registry instanceof AbstractApplicationContext) {
            // Maybe AbstractApplicationContext or its sub-classes
            beanRegistry = ((AbstractApplicationContext) registry).getBeanFactory();
        }
        // Register Singleton Object if possible
        if (beanRegistry != null) {
            // Determine in advance whether injected with beans
            if (!beanRegistry.containsSingleton(beanName)) {
                beanRegistry.registerSingleton(beanName, singletonObject);
            }
        }
    }

    public static Properties resolveProperties(Map<?, ?> properties,
                                               PropertyResolver propertyResolver) {
        PropertiesPlaceholderResolver propertiesPlaceholderResolver = new PropertiesPlaceholderResolver(
                propertyResolver);
        return propertiesPlaceholderResolver.resolve(properties);
    }
}
