package top.yms.mas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.yms.mas.entity.MyIncome;
import top.yms.mas.entity.MyIncomeExample;

@Mapper
public interface MyIncomeMapper {
    @SelectProvider(type=MyIncomeSqlProvider.class, method="countByExample")
    int countByExample(MyIncomeExample example);

    @DeleteProvider(type=MyIncomeSqlProvider.class, method="deleteByExample")
    int deleteByExample(MyIncomeExample example);

    @Delete({
        "delete from my_income",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into my_income (id, name, ",
        "money, pay_type, ",
        "remarks, pay_time, ",
        "category, counterparty, ",
        "order_id)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{money,jdbcType=DECIMAL}, #{payType,jdbcType=VARCHAR}, ",
        "#{remarks,jdbcType=VARCHAR}, #{payTime,jdbcType=TIMESTAMP}, ",
        "#{category,jdbcType=VARCHAR}, #{counterparty,jdbcType=VARCHAR}, ",
        "#{orderId,jdbcType=VARCHAR})"
    })
    int insert(MyIncome record);

    @InsertProvider(type=MyIncomeSqlProvider.class, method="insertSelective")
    int insertSelective(MyIncome record);

    @SelectProvider(type=MyIncomeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="money", property="money", jdbcType=JdbcType.DECIMAL),
        @Result(column="pay_type", property="payType", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="counterparty", property="counterparty", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.VARCHAR)
    })
    List<MyIncome> selectByExample(MyIncomeExample example);

    @Select({
        "select",
        "id, name, money, pay_type, remarks, pay_time, category, counterparty, order_id",
        "from my_income",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="money", property="money", jdbcType=JdbcType.DECIMAL),
        @Result(column="pay_type", property="payType", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="counterparty", property="counterparty", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.VARCHAR)
    })
    MyIncome selectByPrimaryKey(Long id);

    @UpdateProvider(type=MyIncomeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MyIncome record, @Param("example") MyIncomeExample example);

    @UpdateProvider(type=MyIncomeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MyIncome record, @Param("example") MyIncomeExample example);

    @UpdateProvider(type=MyIncomeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MyIncome record);

    @Update({
        "update my_income",
        "set name = #{name,jdbcType=VARCHAR},",
          "money = #{money,jdbcType=DECIMAL},",
          "pay_type = #{payType,jdbcType=VARCHAR},",
          "remarks = #{remarks,jdbcType=VARCHAR},",
          "pay_time = #{payTime,jdbcType=TIMESTAMP},",
          "category = #{category,jdbcType=VARCHAR},",
          "counterparty = #{counterparty,jdbcType=VARCHAR},",
          "order_id = #{orderId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MyIncome record);
}