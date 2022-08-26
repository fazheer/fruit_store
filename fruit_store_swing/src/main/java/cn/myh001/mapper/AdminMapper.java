package cn.myh001.mapper;

import cn.myh001.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface AdminMapper {

    @Select("select * from admin where name=#{name} and password=#{password}")
    Admin select(@Param("name") String name, @Param("password") String password);

    @Select("select * from admin where name = #{name}")
    Admin selectByName(String name);

    @Insert("insert into admin (name, password) VALUE (#{name},#{password})")
    void add(Admin admin);

    @Update("update admin set password = #{password} where name=#{name}")
    void update(Admin admin);
}
