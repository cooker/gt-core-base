package org.grant.zm.lazy.utils;

import org.grant.zm.lazy.LazyHumanMojo;
import org.apache.commons.lang3.SystemUtils;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.plugin.testing.ArtifactStubFactory;
import org.apache.maven.plugin.testing.stubs.MavenProjectStub;
import org.apache.maven.project.MavenProject;
import org.junit.Assert;

/**
 * grant
 * 18/4/2020 5:23 下午
 * 描述：
 */
public class LazyHumanMojoTest extends AbstractMojoTestCase {

    ArtifactStubFactory artifactStubFactory = null;
    MavenProject project = null;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        artifactStubFactory = new ArtifactStubFactory();
        project = new MavenProjectStub();
        artifactStubFactory.setWorkingDir(SystemUtils.getUserDir());
    }

    public void testLazy() throws Exception {
        LazyHumanMojo lazyHumanMojo = (LazyHumanMojo) lookupMojo("lazy",
                System.getProperty("user.dir")+ "/src/test/resources/unit/plugin-pom.xml");
        Assert.assertNotNull(lazyHumanMojo);
        MavenProject project = new MavenProjectStub();
        lazyHumanMojo.setProject(project);
        lazyHumanMojo.execute();
    }
}
