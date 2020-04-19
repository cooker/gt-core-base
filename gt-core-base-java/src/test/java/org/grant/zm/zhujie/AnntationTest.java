package org.grant.zm.zhujie;

import junit.framework.TestCase;
import org.junit.Test;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * grant
 * 13/4/2020 2:11 下午
 * 描述：
 */
@ClassAnntation
public class AnntationTest {

    @Test
    public void testClass(){
        ClassAnntation classAnntation = AnntationTest.class.getAnnotation(ClassAnntation.class);
        System.out.println(classAnntation);
    }

}
