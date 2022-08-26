package cn.myh001.mapper;

import cn.myh001.pojo.Fruit;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FruitMapper {

    //查询所有水果，并放在List集合中
    @Select("select *from fruit")
    @ResultMap("fruitResultMap")
    List<Fruit> selectAll();

    //根据ID查询
    @Select("select *from fruit where id = #{id}")
    @ResultMap("fruitResultMap")
    Fruit selectById(int id);

    //更新水果信息
    @Update("update fruit set fruit_name = #{fruitName},fruit_price=#{fruitPrice},sale_unit = #{saleUnit} where id = #{id}")
    void update(Fruit fruit);

    //根据ID查询水果
    @Delete("delete from fruit where id=#{id}")
    void deleteByIdA(int id);

    @Insert("insert into fruit (fruit_name, fruit_price, sale_unit) VALUE (#{fruitName},#{fruitPrice},#{saleUnit})")
    void insert(Fruit fruit);

//    @Select("select *from fruit where fruit_price like CONCAT('%',#{fruitName},'%') ")
    List<Fruit> selectByFruitName(String fruitName);

}
