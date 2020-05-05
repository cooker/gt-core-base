package com.github.cooker.spring;

import com.github.cooker.spring.ioc.MyBean;
import com.github.cooker.spring.ioc.MyBeanEntity;
import org.junit.Test;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * grant
 * 3/5/2020 2:37 下午
 * 描述：
 */
public class ScannerTest {

    @Test
    public void aa(){
        AnnotationMetadata metadata = new AnnotationMetadataReadingVisitor(this.getClass().getClassLoader());
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(MyBeanEntity.class.getName()));
        System.out.println("ssss");
    }

    @Test
    public void ad(){
        GenericApplicationContext context = new GenericApplicationContext();
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context, false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(MyBean.class));
        int beanCount = scanner.scan(MyBean.class.getPackage().getName());
        System.out.println(beanCount);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

    }
}
