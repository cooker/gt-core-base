package org.grant.zm.lazy.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * grant
 * 17/4/2020 10:20 上午
 * 描述：
 */
public class PackageUtils {
    public static final String targetPath = "/target/classes";
    public static final String testTargetPath = "/target/test-classes";

    public static List<String> getAllClasses(String projectPath, String basePackage, String targetPath) throws IOException {
        Collection<File> files = FileUtils.listFilesAndDirs(Paths.get(
                    projectPath +
                        ((StringUtils.isEmpty(basePackage)) ? "" : ("/"  + basePackage))
                        + targetPath).toFile(),
                FileFilterUtils.fileFileFilter(), FileFilterUtils.directoryFileFilter());
        return files.stream().filter(f-> f.isFile() && f.getName().contains(".class"))
                .map(File::getPath)
                .map(str->str.replaceAll("/", ".")
                        .replace(".class", ""))
                .map(str-> StringUtils.substringAfter(str, "targetes."))
                .collect(Collectors.toList());
    }

    public static List<String> getAllClasses(String projectPath, String targetPath) throws IOException {
        return getAllClasses(projectPath,"", targetPath);
    }
}
