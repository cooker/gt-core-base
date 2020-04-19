package org.grant.zm.spring2.javassist;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ClassFile;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * grant
 * 15/4/2020 9:14 PM
 * 描述：
 */
public class ChangeClasstest {

    @Test
    public void ch() throws NotFoundException, ClassNotFoundException, CannotCompileException, IOException, NoSuchMethodException {
        ClassPool pool = ClassPool.getDefault();;
        CtClass ctClass =pool.getCtClass(UpAnnClass.class.getName());
        ClassFile ccFile = ctClass.getClassFile();
        AttributeInfo ai = ccFile.getAttribute("RuntimeVisibleAnnotations");
        System.out.println(ai);
        ccFile.getAttributes().forEach(a->{
            System.out.println(a.getName());
        });
        CtMethod ctMethods = ctClass.getDeclaredMethod("ss");
        UpAnn obj = (UpAnn)ctMethods.getAnnotation(UpAnn.class);

//        System.out.println(obj.getClass().getName());
//        ctClass.removeMethod(ctMethods);
        System.out.println(ctMethods.getName());
        System.out.println(obj);
        ctClass.setName("org.grant.zm.spring2.javassist.AClass");
        AnnotationsAttribute methodAttr = new AnnotationsAttribute(ccFile.getConstPool(), AnnotationsAttribute.visibleTag);
        methodAttr.addAnnotation(new Annotation("org.grant.zm.spring2.javassist.DpAnn", ccFile.getConstPool()));
        MethodInfo info = ctMethods.getMethodInfo();
        AttributeInfo attributeInfo =  info.getAttribute(AnnotationsAttribute.visibleTag);
        if (attributeInfo != null){
            ((AnnotationsAttribute)attributeInfo).removeAnnotation(UpAnn.class.getTypeName());
            Annotation an = new Annotation(DpAnn.class.getTypeName(), ccFile.getConstPool());
            an.addMemberValue("value", new StringMemberValue("test", ccFile.getConstPool()));
            ((AnnotationsAttribute)attributeInfo).addAnnotation(an);
        }
//        info.addAttribute(methodAttr);
//        ctClass.addMethod(ctMethods);
        ctClass.writeFile(SystemUtils.getUserDir() + "/target/test-classes");

        Class cl = Class.forName("org.grant.zm.spring2.javassist.AClass");
        Method method = cl.getMethod("ss");
        DpAnn dpAnn = method.getAnnotation(DpAnn.class);
        System.out.println(dpAnn);
    }
}
