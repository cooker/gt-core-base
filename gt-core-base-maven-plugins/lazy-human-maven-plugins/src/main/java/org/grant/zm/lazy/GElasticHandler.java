package org.grant.zm.lazy;

import org.grant.zm.lazy.utils.PropertiesUtils;
import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.artifact.Artifact;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * grant
 * 18/4/2020 10:03 上午
 * 描述：
 */
public class GElasticHandler {
    private ClassPool pool = null;

    LazyHumanMojo lazyHumanMojo = null;
    Map<String, Properties> cache = new HashMap<>();

    public GElasticHandler(LazyHumanMojo lazyHumanMojo) {
        this.lazyHumanMojo = lazyHumanMojo;
    }

    public void elastic(List<String> classes) throws NotFoundException, CannotCompileException, IOException {
        pool = ClassPool.getDefault();
        String basePath = lazyHumanMojo.project.getBasedir().getAbsolutePath();
        pool.appendClassPath(basePath + File.separatorChar + lazyHumanMojo.targetPath);
        for (Artifact artifact : lazyHumanMojo.project.getArtifacts()){
            pool.appendClassPath(artifact.getFile().getAbsolutePath());
        }
        CtClass ctClass = null;
        ClassFile classFile = null;
        for (String cl : classes){
            lazyHumanMojo.getLog().info("查找类：>> " + cl);
            ctClass = pool.get(cl);
            classFile = ctClass.getClassFile();
            doClass(classFile);
            doMethod(classFile);
            ctClass.writeFile(lazyHumanMojo.project.getBasedir().getAbsolutePath() + lazyHumanMojo.targetPath);
        }

    }

    protected void doMethod(ClassFile classFile) {
        List<MethodInfo> methodInfos = classFile.getMethods();
        for (MethodInfo info : methodInfos){
            AnnotationsAttribute attribute = (AnnotationsAttribute)info.getAttribute("RuntimeVisibleAnnotations");
            this.lookAttribute(attribute, classFile);
        }
    }

    protected void doClass(ClassFile classFile){
        AnnotationsAttribute attribute = (AnnotationsAttribute) classFile.getAttribute("RuntimeVisibleAnnotations");
        this.lookAttribute(attribute, classFile);
    }

    protected void lookAttribute(AnnotationsAttribute attribute, ClassFile classFile){
        if (attribute == null) return;
        Annotation annotation = attribute.getAnnotation(GElastic.class.getTypeName());
        if (annotation == null){
            return;
        }
        //移除注解
        attribute.removeAnnotation(GElastic.class.getTypeName());
        GElastic gElastic = null;
        try {
            gElastic = (GElastic) annotation.toAnnotationType(classFile.getClass().getClassLoader(), pool);
            lazyHumanMojo.getLog().info("注解解析：" + gElastic);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String propFile = gElastic.propFile();
        Properties properties = getProperties(propFile);
        String value = properties.getProperty(gElastic.propKey());
        GElasticType[] gElasticTypes = null;
        if (PropertiesUtils.check(value, gElastic.propVal(), gElastic.matchIfMissing())){
            gElasticTypes = gElastic.success();
        }else {
            gElasticTypes = gElastic.failure();
        }
        if (ArrayUtils.isEmpty(gElasticTypes)) return;
        for (GElasticType type : gElasticTypes){
            Annotation an = new Annotation(type.typeName(), classFile.getConstPool());
            for (GElasticValue vl : type.value()){
                an.addMemberValue(vl.key(), memberValue(vl.keyType(), vl.value(), classFile.getConstPool()));
            }
            attribute.addAnnotation(an);
        }
    }


    public MemberValue autoMemberValue(GElasticValueType gElasticValueType, String value, ConstPool pool){
        if (gElasticValueType.name().contains("ARRAY_")){
            return memberValues(gElasticValueType, value, pool);
        }else {
            return memberValue(gElasticValueType, value, pool);
        }
    }


    protected MemberValue memberValue(GElasticValueType gElasticValueType, String value, ConstPool pool){
        MemberValue memberValue = null;
        switch (gElasticValueType){
            case STRING:
                memberValue = new StringMemberValue(value, pool);
                break;
            case INT:
                memberValue = new IntegerMemberValue(pool, Integer.valueOf(value));
                break;
            case SHORT:
                memberValue = new ShortMemberValue(Short.valueOf(value), pool);
                break;
            case LONG:
                memberValue = new LongMemberValue(Long.valueOf(value), pool);
                break;
            case DOUBLE:
                memberValue = new DoubleMemberValue(Double.valueOf(value), pool);
                break;
            case FLOAT:
                memberValue = new FloatMemberValue(Float.valueOf(value), pool);
                break;
            case BOOL:
                memberValue = new BooleanMemberValue(Boolean.valueOf(value), pool);
                break;
            case BYTE:
                memberValue = new ByteMemberValue(Byte.valueOf(value), pool);
                break;
            case CHAR:
                memberValue = new CharMemberValue(CharUtils.toChar(value), pool);
                break;
            case CLASS:
                memberValue = new ClassMemberValue(value, pool);
                break;
            case ENUM:
                EnumMemberValue eVal = new EnumMemberValue(pool);
                eVal.setType(StringUtils.substringBeforeLast(value, "."));
                eVal.setValue(StringUtils.substringAfterLast(value, "."));
                memberValue = eVal;
                break;
            case ANNOTATION:
                memberValue = new AnnotationMemberValue(new Annotation(value, pool), pool);
                break;
        }
        return memberValue;
    }

    protected MemberValue memberValues(GElasticValueType gElasticValueType, String value, ConstPool pool){
        ArrayMemberValue memberValue = new ArrayMemberValue(pool);
        GElasticValueType baseType = getBaseType(gElasticValueType);
        String[] strs = org.grant.zm.lazy.utils.ArrayUtils.toArrays(value);
        MemberValue mVal = null;
        List<MemberValue> memberValues = new ArrayList<>();
        for (String str : strs) {
            mVal =  memberValue(baseType, str, pool);
            memberValues.add(mVal);
        }
        memberValue.setValue(memberValues.toArray(new MemberValue[0]));
        return memberValue;
    }

    protected GElasticValueType getBaseType(GElasticValueType gElasticValueType){
        String name = gElasticValueType.name().replace("ARRAY_", "");
        return GElasticValueType.valueOf(name);
    }

    private Properties getProperties(String propFile){
        if (cache.containsKey(propFile)){
            return cache.get(propFile);
        }else {
            Properties properties = PropertiesUtils.getProperties(lazyHumanMojo.project.getBasedir().getPath(), lazyHumanMojo.targetPath, propFile);
            cache.put(propFile, properties);
            return properties;
        }
    }
}

