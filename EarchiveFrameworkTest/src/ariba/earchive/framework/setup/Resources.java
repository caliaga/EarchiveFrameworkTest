package ariba.earchive.framework.setup;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.testng.internal.PropertiesFile;

public class Resources {
      
      public static String getProperty(String tagName){
            PropertiesFile properties = null;
            String pathName = getResourcesPath();
            try {
                  properties = new PropertiesFile(pathName+getPropertyFileName(pathName));
            } catch (IOException e) {
                  e.printStackTrace();
            }
            return properties.getProperties().getProperty(tagName);
      }
      
      public static String getResourcesPath(){
            return System.getProperty("user.dir")+"\\res\\";
      }
      
      private static String getPropertyFileName(String pathName){
            File file = new File(pathName);
            return file.listFiles(new FilenameFilter() {
                  @Override
                  public boolean accept(File dir, String name) {
                        return name.endsWith(".properties");
                  }
            })[0].getName().toString();
      }
      
      public static String getExcelFileName(String pathName, int filePosition){
            File file = new File(pathName);
            return file.listFiles(new FilenameFilter() {
                  
                  @Override
                  public boolean accept(File dir, String name) {
                        return name.endsWith(".xls"); 
                  }
            })[filePosition].toString(); //[filePosition].getName().toString();
      }

}