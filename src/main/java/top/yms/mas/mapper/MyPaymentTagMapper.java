package top.yms.mas.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.yms.mas.entity.MyPaymentTag;
import top.yms.mas.entity.MyPaymentTagExample;

import java.util.List;

public interface MyPaymentTagMapper {
    @SelectProvider(type=MyPaymentTagSqlProvider.class, method="countByExample")
    int countByExample(MyPaymentTagExample example);

    @DeleteProvider(type=MyPaymentTagSqlProvider.class, method="deleteByExample")
    int deleteByExample(MyPaymentTagExample example);

    @Delete({
        "delete from my_payment_tag",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into my_payment_tag (id, name, ",
        "create_time)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(MyPaymentTag record);

    @InsertProvider(type=MyPaymentTagSqlProvider.class, method="insertSelective")
    int insertSelective(MyPaymentTag record);

    @SelectProvider(type=MyPaymentTagSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<MyPaymentTag> selectByExample(MyPaymentTagExample example);

    @Select({
        "select",
        "id, name, create_time",
        "from my_payment_tag",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    MyPaymentTag selectByPrimaryKey(Integer id);

    @UpdateProvider(type=MyPaymentTagSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MyPaymentTag record, @Param("example") MyPaymentTagExample example);

    @UpdateProvider(type=MyPaymentTagSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MyPaymentTag record, @Param("example") MyPaymentTagExample example);

    @UpdateProvider(type=MyPaymentTagSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MyPaymentTag record);

    @Update({
        "update my_payment_tag",
        "set name = #{name,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MyPaymentTag record);
}