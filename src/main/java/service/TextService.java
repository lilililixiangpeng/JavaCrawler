package service;

import model.Text;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class TextService {
    private SqlSessionFactory sqlFactory;
    private Reader reader;
    private SqlSession session;


    public void savetext(Text text) throws IOException {
            try {
                reader = Resources.getResourceAsReader("mybatis-config.xml");
                sqlFactory = new SqlSessionFactoryBuilder().build(reader);
                session = sqlFactory.openSession();

                session.insert("user.savetext",text);
                session.commit();
            } catch (Exception e) {
                e.printStackTrace();
                session.rollback();
            }finally {
                session.close();
            }


    }
}
