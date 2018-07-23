package bitcamp.pms.config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//자바에서 iocContainer설정하는법

//@Configuration은 일반객체와 다르다!
//IoC 컨테이너에 객체를 저장할수있다 여기서
@Configuration
public class AppConfig {
    public AppConfig() {
        System.out.println("AppConfig() 호출됨");
    }
    
    //iocContainer에 저장 됨 이름은 sqlSessionFactory로지정되며 리턴값인 sqlSessionFactory객체가 저장됨
    /*@Bean("sqlSessionFactory") 
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        System.out.println("AppConfig.getSqlSessionFactory()에서 호출");
        String resource = "bitcamp/pms/config/mybatis-config.xml";
        InputStream inputStream;
        inputStream = Resources.getResourceAsStream(resource);
        
        return new SqlSessionFactoryBuilder().build(inputStream);
         
    }*/
}
