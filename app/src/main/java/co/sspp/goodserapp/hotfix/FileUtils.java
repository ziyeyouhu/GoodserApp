package co.sspp.goodserapp.hotfix;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils{
/**
 * @param fileDir  文件目录
 * @param fileType 后缀名
 * @return 特定目录下的所有后缀名为fileType的文件列表
 */
public static List<String> getFiles(File fileDir, String fileType) throws Exception{
          List<String> lfile=new ArrayList<String>();
           File[] fs=fileDir.listFiles();
          for (File f : fs) {
                   if (f.isFile()) {
                   if (fileType
                  .equals(f.getName().substring(
                   f.getName().lastIndexOf(".") + 1,
                               f.getName().length())))
                   lfile.add(f.getName());
            }
          }
    return lfile;
}
public static String getLoadPatchName(File fileDir,String fileType,String versionCode)  throws Exception{
               List<String> files=getFiles(fileDir,fileType);
                      int maxPatchVersion=0;
                for (String name : files) {
                        if (name.startsWith(versionCode + "_")) {
                                  int patchVersion=Integer.valueOf(name.substring(name.indexOf("_") + 1,name.indexOf(".")));
                                 maxPatchVersion=Math.max(maxPatchVersion,patchVersion);
                      }
                 }
                return String.valueOf(maxPatchVersion);
       }
}