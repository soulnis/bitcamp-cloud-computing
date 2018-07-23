package bitcamp.pms.context;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;

import org.apache.ibatis.io.Resources;

import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Component;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.Repository;

//객체를 관리!!
public class ApplicationContext {
    
    static HashMap<String,Object> objPool = new HashMap<String,Object>();
    
    public ApplicationContext(String packageName) throws Exception {
        String filePath = packageName.replace(".", "/");
        //다음에서 사용할 Resources.getResourceAsFile()이
        //대충 다음과 같이 되어있다는 점점이다.
        //ClassLoader deClassLoader =ClassLoader.getSystemClassLoader();
        //        
        ////패지키네임의 경로를 추출
        //URL url = deClassLoader.getSystemResource(filePath);
        
        //해당 패키지의 경로의파일(디렉토리)를 자바로 생성함
        File dir = Resources.getResourceAsFile(filePath);
        
        System.out.println(dir);
        
        //해당경로의 디렉토리와 패지키네임을 넣어준다.
        findClass(dir,packageName);
       
    }
    private void injectDependency() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        //objpool에 보관소에 저장된 모든 객체를 꺼낸다. map.values()라는 매소드를 실행하면 Collection(=List와비슷)로 값이 리턴됨
        Collection<Object> objList=objPool.values();
       
        //모든객체를 꺼내서 검사한다.
        for(Object obj : objList) {
            //객체의 클래스 정보를 추출한다.
            Class<?> clazz = obj.getClass();
            
            //해당클래스의 모든 매서드를 추출한다.
            //Method는 해당 메소드에 대한 정보를 가지고있다 이름,리턴타입,파라미터타입,애노테이션유무 등을 알수있다.
            Method[] methods = clazz.getMethods();
            
                for(Method m : methods) {
                    //각 객체에 존재하는 매서드 중에서 @Autowired가 붙은 셋터를 찾는다
                    //매서드의 이름이 setter가 아니면 무시
                    if(!m.getName().startsWith("set"))
                        continue;
                    //매서드 위에 @Autowired가 붙지 않으면 무시
                    if(m.getAnnotation(Autowired.class) == null)
                        continue;
                    // =>해당매서드의 파라미터 개수가 한개가 아니라면 무시
                    if(m.getParameterTypes().length > 1)
                        continue;
                    try {
                        //셋터의 파라미터 타입을 알아낸다.
                        //MemberDAO타입이라든지 SqlSessionFactory 타입이라던지 파라미터 타입 체크
                        Class<?> paramType = m.getParameterTypes()[0];
                        //셋터의 파라미터 타입에 해당하는 객체를찾아서 objPool에 보관소에서 꺼낸다.
                        Object dependency = getBean(paramType);
                        
                        //셋터를 호출하여 의존객체를 주입한다. 
                        //invoke 첫번째 인자는 해당매소드의 클래스의 리퍼런스(주소) 넣어준다.
                        m.invoke(obj, dependency);
                    } catch (Exception e) {
                        System.out.println("error:"+e.getMessage());
                        //의존 객체가 없으면 셋터를 호출하지않는다.
                      //그냥 무시
                    }
                    }
                }
        }
    
    //외부에서 objPool에 객체를 추가
    public void addBean(String name,Object obj) {
        objPool.put(name,obj);
    }
    
    
    //객체를 반환하는 매서드  ex)경로(value)를넣어주면 객체를 리턴한다. 
    public Object getBean(String name) {
        
        Object obj = objPool.get(name);
        if(obj == null)
            throw new RuntimeException("객체가 존재하지않습니다.");
        return obj;
    }
    
    //타입을 파라미터로 넣어주면 타입에맞는 객체를 반환
    public Object getBean(Class<?> type) {
     //objPool의 모든 객체를 꺼낸다
     Collection<Object> objList = objPool.values();
     for(Object obj : objList) {
         //들어온 타입과 objPool에들어있는 객체와 비교해서 true면 해당 객체를 반환 
         if(type.isInstance(obj))
             return obj;
     }
     //객체가 존재하지않으면 런타임오류를 발생
     throw new RuntimeException(type.getName()+"의 객체가 존재하지 않습니다.");
    }
    
    //패키지와 파일경로를 통해서 하위로계속 훑으면서 .class파일을 찾고 객체를 생성함
    private void findClass(File path, String packageName) {
        
        //listFiles(익명객체)를 인수로 넣어서 그 안에서  조건식에 알맞게 File[]로 반환한다
        File[] subFiles = path.listFiles(
            (File pathname) -> {
                    if(pathname.isDirectory())
                        return true;
                    if(pathname.isFile() && pathname.getName().endsWith(".class"))
                        return true;
                    return false;
                }
       );
        
        //조건에맞는 파일을 반환후 확장자 .class를 제거
        for(File subFile : subFiles) {
            if(subFile.isFile()) {
                String className = (packageName+"."+subFile.getName().replace(".class",""));
                createObject(className);
            } else {
                //아니면 다시 호출(재귀호출)
                //계속 아래로아래로 내려가는 재귀호출 sub/public/a/b/c이면 sub돌고 public돌고 a돌고 b돌고하는식 찾음
                findClass(subFile,packageName+"."+subFile.getName());
            }
        }
  }
    
    //@애노테이션이있는 객체를 생성해서 objPool에 추가해준다.
    private  void createObject(String className) {
        try {
            //클래스 이름(패키지명+클래스명)으로 .class 파일을 찾아 로딩한다.
            //인수로 들어온 클래스의 정보를 가져온다.
            Class<?> clazz = Class.forName(className);
          
            //해당클래스에 애노테이션을 가지고있는지 체크 없으면 더이상 진행하지않는다.
            //@Coponent,@Controller,@Repository 애노테이션이 붙은 클래스가 아니면 클래스 생성x
            if(clazz.getAnnotation(Component.class) == null &&
                  clazz.getAnnotation(Controller.class) == null &&
                          clazz.getAnnotation(Repository.class) == null )
              return;
         
                          
          
            //클래스 정보에서 기본 생성자를 알아낸다.
            Constructor<?> constructor = clazz.getConstructor();
            
            
            //객체를 저장할때 사용할 이름을 알아낸다.
            String objName = getObjectName(clazz);
         
        
            //기본생성자를 호출하여 객체를 생성후 객체보관소에 저장한다.
            //키값으로 애노테이션 value를 지정하거나, 없으면 해당객체의 이름으로지정
            //constructor.newInstance()는 객체를 기본 생성자로 생성하는것
            objPool.put(objName,constructor.newInstance());
             
            }catch (Exception e) {
               System.out.println(e.getMessage());
            }
              
  }
  
    //객체의 이름을 지정해준다.해당 애노테이션의 value값을 키값으로 지정해준다.
    private String getObjectName(Class<?> clazz) throws Exception{
        String objName = null;
        
        //컴포너트 애노테이션을 찾아라 찾았으면 value값을 저장하라
        Component compAnno = clazz.getAnnotation(Component.class);
        if(compAnno != null)
            objName = compAnno.value();
        //컨트롤러 애노테이션을 찾아라 찾았으면 value값을 저장하라
        Controller ctrlAnno = clazz.getAnnotation(Controller.class);
        if(ctrlAnno != null)
            objName = ctrlAnno.value();
        //레파지토리 애노테이션을 찾아라 찾았으면 value값을 저장하라
        Repository repoAnno = clazz.getAnnotation(Repository.class);
        if(repoAnno != null) {
            objName = repoAnno.value(); 
        }
        
        System.out.println("여기"+objName);
        
        //objName길이가 0이면 클래스의
        if(objName.length()==0) {
            return clazz.getCanonicalName();
        } else {
            return objName;
        }
    }
  
    

  public void refresh() throws Exception {
      injectDependency();
  }    

}
