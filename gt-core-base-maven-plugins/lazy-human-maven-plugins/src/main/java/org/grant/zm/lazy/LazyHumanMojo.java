package org.grant.zm.lazy;

import org.grant.zm.lazy.utils.PackageUtils;
import com.google.inject.internal.util.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;

import java.io.IOException;
import java.util.List;

/**
 * 17/4/2020 10:13 上午
 * 描述：
 * @author grant
 */
@Mojo(name = "lazy", defaultPhase = LifecyclePhase.TEST, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME , threadSafe = true)
public class LazyHumanMojo extends AbstractMojo {

    @Parameter( defaultValue = "${project}", readonly = true, required = true )
    MavenProject project;

    public void setProject(MavenProject project) {
        this.project = project;
    }

    @Parameter(name = "basePackage", defaultValue = "")
    String basePackage;

    @Parameter(name = "targetPath", defaultValue = PackageUtils.targetPath)
    String targetPath;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Log log = getLog();
        log.info("Lazy Human 插件运行");
        log.info("读取配置参数：");
        log.info("basePackage：" + basePackage);
        log.info("targetPath：" + targetPath);

        log.info("====扫描开始====");
        String temp = StringUtils.isEmpty(basePackage) ? "" : basePackage.replaceAll(".", "/");
        List<String> classes = Lists.newArrayList();

        try {
            classes = PackageUtils.getAllClasses(project.getBasedir().getAbsolutePath() + temp, targetPath);
        } catch (IOException e) {
            log.error("扫描失败，请检查配置", e);
        }

        if (classes.isEmpty()) return;
        log.info("@GElastic 动态替换注解 检查...");
        try {
            new GElasticHandler(this).elastic(classes);
        } catch (Exception e) {
            log.info("@GElastic 处理失败", e);
        }

    }
}
