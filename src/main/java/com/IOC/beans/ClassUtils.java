package com.IOC.beans;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

/**
 * �๤��
 */
public class ClassUtils {

    /**
     * ��ȡĳ����������
     *
     * @param packageName ����
     * @param isRecursion �Ƿ�����Ӱ�
     * @return �����������
     */
    public static Set<String> getClassName(String packageName, boolean isRecursion) {
        Set<String> classNames = new HashSet<>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");
        URL url = loader.getResource(packagePath);
        String filePath = null;
        try {
            filePath = URLDecoder.decode(url.getPath(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (filePath != null) {
            classNames = getClassNameFromDir(filePath, packageName, isRecursion);
        }
        return classNames;
    }

    /**
     * ����Ŀ�ļ���ȡĳ��������
     *
     * @param filePath    �ļ�·��
     * @param isRecursion �Ƿ�����Ӱ�
     * @return �����������
     */
    private static Set<String> getClassNameFromDir(String filePath, String packageName, boolean isRecursion) {
        Set<String> className = new HashSet<>();
        File file = new File(filePath);
        File[] files = file.listFiles();
        for (File childFile : files) {

            if (childFile.isDirectory()) {
                if (isRecursion) {
                    className.addAll(getClassNameFromDir(childFile.getPath(), packageName + "." + childFile.getName(), isRecursion));
                }
            } else {
                String fileName = childFile.getName();
                if (fileName.endsWith(".class") && !fileName.contains("$")) {
                    className.add(packageName + "." + fileName.replace(".class", ""));
                }
            }
        }
        return className;
    }


    /**
     * ��ȡ�������
     * @return
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            if (classLoader == null) {
                classLoader = Thread.currentThread().getContextClassLoader();
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        if (classLoader == null) {
            classLoader = ClassUtils.class.getClassLoader();
            if (classLoader == null) {
                try {
                    classLoader = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        }
        return classLoader;
    }


}