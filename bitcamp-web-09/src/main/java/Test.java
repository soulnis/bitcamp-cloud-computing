import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.pms.annotation.Component;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.Repository;
import bitcamp.pms.context.ApplicationContext;


public class Test {

   
    public static void main(String[] args) throws Exception{

     ApplicationContext iocContainer = new ApplicationContext("bitcamp.pms");
     
     Object obj = iocContainer.getBean("ppp");
     System.out.println(obj.getClass().getName());
    }

}
