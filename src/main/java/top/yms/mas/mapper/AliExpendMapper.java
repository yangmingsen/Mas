package top.yms.mas.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.yms.mas.entity.AliExpend;
import top.yms.mas.entity.AliExpendExample;

import java.util.List;

@Mapper
public interface AliExpendMapper {
    @SelectProvider(type=AliExpendSqlProvider.class, method="countByExample")
    int countByExample(AliExpendExample example);

    @DeleteProvider(type=AliExpendSqlProvider.class, method="deleteByExample")
    int deleteByExample(AliExpendExample example);

    @Delete({
        "delete from ali_expend",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into ali_expend (id, counterparty, ",
        "title, payment_method, ",
        "amount, category, ",
        "tx_time)",
        "values (#{id,jdbcType=VARCHAR}, #{counterparty,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{paymentMethod,jdbcType=VARCHAR}, ",
        "#{amount,jdbcType=DECIMAL}, #{category,jdbcType=VARCHAR}, ",
        "#{txTime,jdbcType=TIMESTAMP})"
    })
    int insert(AliExpend record);

    @InsertProvider(type=AliExpendSqlProvider.class, method="insertSelective")
    int insertSelective(AliExpend record);

    @SelectProvider(type=AliExpendSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="counterparty", property="counterparty", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="payment_method", property="paymentMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="tx_time", property="txTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<AliExpend> selectByExample(AliExpendExample example);

    @Select({
        "select",
        "id, counterparty, title, payment_method, amount, category, tx_time",
        "from ali_expend",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="counterparty", property="counterparty", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="payment_method", property="paymentMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="tx_time", property="txTime", jdbcType=JdbcType.TIMESTAMP)
    })
    AliExpend selectByPrimaryKey(String id);

    @UpdateProvider(type=AliExpendSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AliExpend record, @Param("example") AliExpendExample example);

    @UpdateProvider(type=AliExpendSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AliExpend record, @Param("example") AliExpendExample example);

    @UpdateProvider(type=AliExpendSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AliExpend record);

    @Update({
        "update ali_expend",
        "set counterparty = #{counterparty,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "payment_method = #{paymentMethod,jdbcType=VARCHAR},",
          "amount = #{amount,jdbcType=DECIMAL},",
          "category = #{category,jdbcType=VARCHAR},",
          "tx_time = #{txTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AliExpend record);
}