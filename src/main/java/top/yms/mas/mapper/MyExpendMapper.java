package top.yms.mas.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.yms.mas.entity.MyExpend;
import top.yms.mas.entity.MyExpendExample;

import java.util.List;

@Mapper
public interface MyExpendMapper {
    @SelectProvider(type=MyExpendSqlProvider.class, method="countByExample")
    int countByExample(MyExpendExample example);

    @DeleteProvider(type=MyExpendSqlProvider.class, method="deleteByExample")
    int deleteByExample(MyExpendExample example);

    @Delete({
        "delete from my_expend",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into my_expend (id, name, ",
        "money, pay_type, ",
        "remarks, pay_time)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{money,jdbcType=DECIMAL}, #{payType,jdbcType=VARCHAR}, ",
        "#{remarks,jdbcType=VARCHAR}, #{payTime,jdbcType=TIMESTAMP})"
    })
    int insert(MyExpend record);

    @InsertProvider(type=MyExpendSqlProvider.class, method="insertSelective")
    int insertSelective(MyExpend record);

    @SelectProvider(type=MyExpendSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="money", property="money", jdbcType=JdbcType.DECIMAL),
        @Result(column="pay_type", property="payType", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<MyExpend> selectByExample(MyExpendExample example);

    @Select({
        "select",
        "id, name, money, pay_type, remarks, pay_time",
        "from my_expend",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="money", property="money", jdbcType=JdbcType.DECIMAL),
        @Result(column="pay_type", property="payType", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP)
    })
    MyExpend selectByPrimaryKey(Long id);

    @UpdateProvider(type=MyExpendSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MyExpend record, @Param("example") MyExpendExample example);

    @UpdateProvider(type=MyExpendSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MyExpend record, @Param("example") MyExpendExample example);

    @UpdateProvider(type=MyExpendSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MyExpend record);

    @Update({
        "update my_expend",
        "set name = #{name,jdbcType=VARCHAR},",
          "money = #{money,jdbcType=DECIMAL},",
          "pay_type = #{payType,jdbcType=VARCHAR},",
          "remarks = #{remarks,jdbcType=VARCHAR},",
          "pay_time = #{payTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MyExpend record);
}