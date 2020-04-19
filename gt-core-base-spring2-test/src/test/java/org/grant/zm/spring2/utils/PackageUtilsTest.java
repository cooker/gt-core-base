package org.grant.zm.spring2.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * grant
 * 17/4/2020 11:12 上午
 * 描述：
 */
public class PackageUtilsTest {

    @Test
    public void list(){
        String path = SystemUtils.getUserDir().getPath() + "/target/classes";

        Collection<File> files = FileUtils.listFilesAndDirs(Paths.get(path).toFile(), FileFilterUtils.fileFileFilter(), FileFilterUtils.directoryFileFilter());
        files.stream().filter(f->{
            return f.isFile() && f.getName().contains(".class");
        }).forEach(
                f->{
                    System.out.println(f.getPath());
                }
        );
    }
}
