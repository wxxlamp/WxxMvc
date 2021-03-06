package cn.wxxlamp.mvc.util;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * class有关工具类
 * @author wxxlamp
 * @date 2021/05/01~10:07
 */
public class ClazzUtils {
    private static final String CLASS_SUFFIX = ".class";
    private static final String PACKAGE_SEPARATOR = ".";
    private static final String FILE_PROTOCOL = "file";
    private static final String JAR_PROTOCOL = "jar";
    private static final String DOLLAR = "$";

    /**
     * 查找包下的所有类的名字
     * @param packageName 包名
     * @param showChildPackageFlag 是否需要显示子包内容
     * @return List集合，内容为类的全名
     */
    public static List<String> getClazzName(String packageName, boolean showChildPackageFlag) {
        List<String> result = new ArrayList<>();
        String suffixPath = packageName.replaceAll("\\.", "/");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> urls = loader.getResources(suffixPath);
            while(urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if(url != null) {
                    String protocol = url.getProtocol();
                    if(FILE_PROTOCOL.equals(protocol)) {
                        String path = url.getPath();
                        result.addAll(getAllClassNameByFile(new File(path), packageName));
                    } else if(JAR_PROTOCOL.equals(protocol)) {
                        JarFile jarFile = null;
                        try{
                            jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                        if(jarFile != null) {
                            result.addAll(getAllClassNameByJar(jarFile, packageName, showChildPackageFlag));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
        /**
         * 递归获取所有class文件的名字
         * @param file 文件
         * @return List
         */
    private static List<String> getAllClassNameByFile(File file, String packageName) {
        List<String> result =  new ArrayList<>();
        if(!file.exists()) {
            return result;
        }
        if(file.isFile()) {
            String path = file.getPath();
            // 注意：这里替换文件分割符要用replace。因为replaceAll里面的参数是正则表达式,而windows环境中File.separator="\\"的,因此会有问题
            if(path.endsWith(CLASS_SUFFIX)) {
                // 从包名开始的地方截取
                path = path.replace(CLASS_SUFFIX, "")
                    .replace(File.separator, PACKAGE_SEPARATOR);
                String clazzName = path
                    .substring(path.indexOf(packageName));
                if(!clazzName.contains(DOLLAR)) {
                    result.add(clazzName);
                }
            }
        } else {
            File[] listFiles = file.listFiles();
            if(listFiles != null && listFiles.length > 0) {
                for (File f : listFiles) {
                        result.addAll(getAllClassNameByFile(f, packageName));

                }
            }
        }
        return result;
    }
    /**
     * 递归获取jar所有class文件的名字
     * @param jarFile jar
     * @param packageName 包名
     * @param flag  是否需要迭代遍历
     * @return List
     */
    private static List<String> getAllClassNameByJar(JarFile jarFile, String packageName, boolean flag) {
        List<String> result =  new ArrayList<>();
        Enumeration<JarEntry> entries = jarFile.entries();
        while(entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String name = jarEntry.getName();
            // 判断是不是class文件
            if(name.endsWith(CLASS_SUFFIX)) {
                name = name.replace(CLASS_SUFFIX, "")
                        .replace("/", ".");
                if(flag) {
                    // 如果要子包的文件,那么就只要开头相同且不是内部类就ok
                    if(name.startsWith(packageName) && !name.contains(DOLLAR)) {
                        result.add(name);
                    }
                } else {
                    // 如果不要子包的文件,那么就必须保证最后一个"."之前的字符串和包名一样且不是内部类
                    if(packageName.equals(name.substring(0, name.lastIndexOf("."))) && !name.contains("$")) {
                        result.add(name);
                    }
                }
            }
        }
        return result;
    }
}
