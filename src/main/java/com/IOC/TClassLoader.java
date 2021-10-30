package com.IOC;

import com.IOC.beans.MyBean;

import java.io.*;

public class TClassLoader {

    public Class<?> load(String realJavaFileName) throws ClassNotFoundException {
        String name = "com.IOC.beans." + realJavaFileName.substring(0, realJavaFileName.lastIndexOf(".")) ;
        String fileName = realJavaFileName.substring(0, realJavaFileName.lastIndexOf("."))  + ".class";
        Class clasz = Class.forName(name);

        if (clasz.isAnnotationPresent(MyBean.class)) {
            System.out.println(clasz.getName());
        }
        return clasz;
        //ClassLoader loader = new ClassLoader(null) {
        //    @Override
        //    public Class<?> findClass(String name) {
        //        byte[] data = readClass(name);
        //        return this.defineClass(name, data, 0, data.length);
        //    }
        //
        //    private byte[] readClass(String name) {
        //        InputStream is = null;
        //        byte[] returnData = null;
        //        File file = new File("C:\\SpringCode\\src\\main\\java\\com\\IOC\\beans\\BeanA.class");
        //        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //        try {
        //            is = new FileInputStream(file);
        //            int tmp = 0;
        //            while((tmp = is.read()) != -1) {
        //                os.write(tmp);
        //            }
        //            returnData = os.toByteArray();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        } finally {
        //            try {
        //                is.close();
        //                os.close();
        //            } catch (Exception e2) {
        //
        //            }
        //        }
        //        return returnData;
        //    }


            //@Override
            //public Class<?> loadClass(String name) throws ClassNotFoundException {
            //    try {
            //        InputStream is = getClass().getResourceAsStream("beans/" + fileName);
            //        //if (is == null || name.startsWith("java")) {//为了防止TClassLoader加载Object
            //        //    return super.loadClass(name);
            //        //}
            //        byte[] b = new byte[is.available()];
            //        is.read(b);
            //        return defineClass(name, b, 0, b.length);
            //    } catch (IOException E) {
            //        throw  new ClassNotFoundException();
            //    }
            //};
//
//            @Override
//            protected Class<?> findClass(String name) throws ClassNotFoundException {
//                return super.findClass(name);
//            }
//        };
//        return loader.loadClass(name);

    }
}
