package bitcamp.pms.factory;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.FactoryBean;

public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>{

    //이 클래스가 어떤 타입의 객체를 만들어주는 알려준다.
    //IoC컨테이너는 이 공장 객체가 어떤 타입의 객체를 만드는지 만드는지 체크한다
    // 확인한 다음에 이 공장 객체로부터 값을 얻어 컨테이너에 저장한다.
    @Override
    public Class<?> getObjectType() {
        System.out.println("getObjectType()에서 호출");
        return SqlSessionFactory.class;
    }

    //이 공장 객체가 객체를 생산할때 호출된다.
    @Override
    public SqlSessionFactory getObject() throws Exception {
        System.out.println("getObject()에서 호출");
        String resource = "bitcamp/pms/config/mybatis-config.xml";
        InputStream inputStream;
        inputStream = Resources.getResourceAsStream(resource);
        
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


}
