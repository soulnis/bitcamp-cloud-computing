package bitcamp.pms.context;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;

import org.apache.ibatis.io.Resources;

import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Component;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.Repository;

public class ApplicationContext {

   static HashMap<String, Object> objPool = new HashMap<>();
   
 
   //생성자 서블릿로더리스너에서 생성함
   public ApplicationContext(String packageName) throws Exception{
       //들어온 패키지이름을 .을 /로 변경
       String filePath = packageName.replace(".", "/");
       //해당 패키지를 찾아서 그 패키지의 디렉토리를 가져옴
       File dir = Resources.getResourceAsFile(filePath);
      
       findClass(dir,packageName);

       injectDependency();
       
   }

    private void injectDependency() {
        //objPool.values()하면 안에있는 객체가 모두 list에 넣어줌 list형식으로
    Collection<Object> list = objPool.values();
    for(Object obj : list) {
        //객체의 클래스 정보를 추출한다.
        Class<?> clazz = obj.getClass();
        
        //해당클래스의 모든 매서드를 추출한다.
        //Method는 해당 메소드에 대한 정보를 가지고있다 이름,리턴타입,파라미터타입,애노테이션유무 등을 알수있다.
        Method[] methods = clazz.getMethods();
        //모든 메서드를 검사
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
    
    public void refresh() throws Exception {
        injectDependency();
    }    
    public Object getBean(String name) {
        Object obj = objPool.get(name);
        if(obj == null)
            throw new RuntimeException("객체가 존재하지않습니다.");
        return obj;
    }

    private Object getBean(Class<?> paramType) {
        Collection<Object> list = objPool.values();
        for(Object obj : list) {
            if(paramType.isInstance(obj))
                return obj;
        }
        //객체가 존재하지않으면 런타임오류를 발생
        throw new RuntimeException(paramType.getName()+"의 객체가 존재하지 않습니다.");
    }

    private void findClass(File path, String packageName) throws Exception {
        //해당 패키지의 .class파일을 가져옴
        File[] subFile = path.listFiles((File pathname) -> {
                if(pathname.isDirectory())
                    return true;
                if(pathname.isFile() && pathname.getName().endsWith(".class"))
                    return true;
                return false;
            }
        );
        
            for(File file:subFile) {
                //파일만찾음 디렉토리는 버리고
                if(file.isFile()) {
                    String className = (packageName+"."+file.getName().replace(".class",""));
                    createObject(className);
                } else {
                    //아니면 다시 호출(재귀호출)
                    //계속 아래로아래로 내려가는 재귀호출 sub/public/a/b/c이면 sub돌고 public돌고 a돌고 b돌고하는식 찾음
                    findClass(file,packageName+"."+file.getName());
                }
            }
        
    }

    private void createObject(String className) throws Exception {
        //클래스 이름(패키지명+클래스명)으로 .class 파일을 찾아 로딩한다.
        //인수로 들어온 클래스의 정보를 가져온다.
        try {
            Class<?> clazz = Class.forName(className);
            
            //애노테이션이 없는 클래스거르는것
            if(clazz.getAnnotation(bitcamp.pms.annotation.Component.class)==null &&
                clazz.getAnnotation(Controller.class) == null &&
                clazz.getAnnotation(Repository.class) == null)
                    return;
             
            Constructor<?> constructor = clazz.getConstructor();
            
            Annotation[] ano = clazz.getAnnotations();
            for(Annotation a: ano) {
                System.out.println(a.toString());
            }
            
            String objName = getObjectName(clazz);
            
            //기본생성자를 호출하여 객체를 생성후 객체보관소에 저장한다.
            //키값으로 애노테이션 value를 지정하거나, 없으면 해당객체의 이름으로지정
            //constructor.newInstance()는 객체를 기본 생성자로 생성하는것
            objPool.put(objName, constructor.newInstance());
             
          

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

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

    public void addBean(String name,Object obj) {
        objPool.put(name, obj);
        
    }
        
    
}
