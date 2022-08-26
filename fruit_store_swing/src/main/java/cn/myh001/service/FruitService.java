package cn.myh001.service;

import cn.myh001.mapper.FruitMapper;
import cn.myh001.pojo.Fruit;
import cn.myh001.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class FruitService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Fruit> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        List<Fruit> fruits = mapper.selectAll();
        sqlSession.close();
        return fruits;
    }

    public Fruit selectById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        Fruit fruit = mapper.selectById(id);
        sqlSession.close();
        return fruit;
    }

    public void update(Fruit fruit) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        mapper.update(fruit);
        sqlSession.commit();
        sqlSession.close();
    }

    public void deleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        mapper.deleteByIdA(id);
        sqlSession.commit();
        sqlSession.close();
    }

    public void add(Fruit fruit) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        mapper.insert(fruit);
        sqlSession.commit();
        sqlSession.close();

    }
}
