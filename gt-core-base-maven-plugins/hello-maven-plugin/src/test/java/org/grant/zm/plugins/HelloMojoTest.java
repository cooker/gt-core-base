package org.grant.zm.plugins;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

/**
 * grant
 * 12/4/2020 5:49 下午
 * 描述：
 */
public class HelloMojoTest extends AbstractMojoTestCase {

    /** {@inheritDoc} */
    protected void setUp()
            throws Exception
    {
        super.setUp();
    }

    /** {@inheritDoc} */
    protected void tearDown()
            throws Exception
    {
        super.tearDown();
    }

    public void testHelloWorld() throws Exception {
        HelloMojo helloMojo = (HelloMojo) lookupMojo("hello",  System.getProperty("user.dir")+ "/src/test/resources/unit/plugin-pom.xml");
        helloMojo.execute();
    }
}
