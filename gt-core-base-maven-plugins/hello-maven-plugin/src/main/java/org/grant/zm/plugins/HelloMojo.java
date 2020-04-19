package org.grant.zm.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
//import org.apache.maven.plugins.annotations.Parameter;
//import org.apache.maven.plugins.annotations.ResolutionScope;

/**
 * grant
 * 12/4/2020 5:49 下午
 * 描述：
 */
@Mojo(name = "hello", threadSafe = true, defaultPhase = LifecyclePhase.PACKAGE)
public class HelloMojo extends AbstractMojo {

//    @Parameter(name = "maven.hello.name", defaultValue = "aaa")
//    private String name;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Hello World...");
        System.out.println("sasasa");
    }
}
