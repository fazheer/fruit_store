package cn.myh001.service;

import cn.myh001.mapper.AdminMapper;
import cn.myh001.pojo.Admin;
import cn.myh001.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class AdminService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public Admin login(String name, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Admin admin = mapper.select(name, password);
        sqlSession.close();
        return admin;
    }
    public Admin selectByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Admin Admin = mapper.selectByName(name);
        sqlSession.close();
        return Admin;
    }
    public boolean register(Admin admin) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Admin a = mapper.selectByName(admin.getName());

        if (a == null) {
            mapper.add(admin);
            sqlSession.commit();

        }
        sqlSession.close();

        return a == null;
    }
    public void update(Admin admin) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.update(admin);
        sqlSession.commit();
        sqlSession.close();
    }
}
