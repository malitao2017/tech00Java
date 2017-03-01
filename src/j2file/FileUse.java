package j2file;

import java.net.URL;

public class FileUse {
	//测试：对系统取类文件信息的类方法
    public static void sys(){
//        Class cls = null;
//        Object obj = null;
//        obj = ServiceLocator.getInstance().getService(serviceClass);
//        cls = obj.getClass();
    }
    public String getClassFilePath() {
        String strClassName = getClass().getName();
        String strPackageName = "";
        if (getClass().getPackage() != null) {
            strPackageName = getClass().getPackage().getName();
        }
        String strClassFileName = "";
        if (!"".equals(strPackageName)) {
            strClassFileName = strClassName.substring(
                    strPackageName.length() + 1, strClassName.length());
        } else {
            strClassFileName = strClassName;
        }
        URL url = null;
        url = getClass().getResource(strClassFileName + ".class");

        String strURL = url.getFile();
        try {
            strURL = java.net.URLDecoder.decode(strURL, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		/*
		 * strURL = strURL.substring(strURL.indexOf('/') + 1,
		 * strURL.lastIndexOf('/'))
		 */;
        return strURL;
    }
}
