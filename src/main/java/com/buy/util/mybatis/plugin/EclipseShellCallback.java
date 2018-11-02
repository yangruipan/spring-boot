package com.buy.util.mybatis.plugin;

import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.util.messages.Messages;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;


public class EclipseShellCallback implements ShellCallback {

    private boolean overwrite;
    private String workspace;

    public EclipseShellCallback(boolean overwrite) throws IOException {

        File directory = new File("../");
        workspace = directory.getCanonicalPath() + "\\";
        this.overwrite = overwrite;
    }

    public File getDirectory(String targetProject, String targetPackage)
            throws ShellException {
        //System.out.println(workspace+targetProject +"__"+ targetPackage);
        targetProject = workspace + targetProject;
        File project = new File(targetProject);
        if (!project.isDirectory()) {
            throw new ShellException(Messages.getString("Warning.9",
                    targetProject));
        }
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(targetPackage, ".");
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
            sb.append(File.separatorChar);
        }
        File directory = new File(project, sb.toString());
        if (!directory.isDirectory()) {
            boolean rc = directory.mkdirs();
            if (!rc) {
                throw new ShellException(Messages.getString("Warning.10",
                        directory.getAbsolutePath()));
            }
        }
        return directory;
    }

    public void refreshProject(String project) {
    }

    public boolean isMergeSupported() {
        return false;
    }

    public boolean isOverwriteEnabled() {
        return this.overwrite;
    }

    public String mergeJavaFile(String newFileSource,
                                String existingFileFullPath, String[] javadocTags,
                                String fileEncoding) throws ShellException {
        throw new UnsupportedOperationException();
    }
}