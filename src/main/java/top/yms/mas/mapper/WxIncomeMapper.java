package top.yms.mas.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.yms.mas.entity.WxIncome;
import top.yms.mas.entity.WxIncomeExample;

import java.util.List;

@Mapper
public interface WxIncomeMapper {
    @SelectProvider(type=WxIncomeSqlProvider.class, method="countByExample")
    int countByExample(WxIncomeExample example);

    @DeleteProvider(type=WxIncomeSqlProvider.class, method="deleteByExample")
    int deleteByExample(WxIncomeExample example);

    @Delete({
        "delete from wx_income",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into wx_income (id, counterparty, ",
        "title, payment_method, ",
        "amount, category, ",
        "tx_time)",
        "values (#{id,jdbcType=VARCHAR}, #{counterparty,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{paymentMethod,jdbcType=VARCHAR}, ",
        "#{amount,jdbcType=DECIMAL}, #{category,jdbcType=VARCHAR}, ",
        "#{txTime,jdbcType=TIMESTAMP})"
    })
    int insert(WxIncome record);

    @InsertProvider(type=WxIncomeSqlProvider.class, method="insertSelective")
    int insertSelective(WxIncome record);

    @SelectProvider(type=WxIncomeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="counterparty", property="counterparty", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="payment_method", property="paymentMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="tx_time", property="txTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_sync", property="isSync", jdbcType=JdbcType.VARCHAR)
    })
    List<WxIncome> selectByExample(WxIncomeExample example);

    @Select({
        "select",
        "id, counterparty, title, payment_method, amount, category, tx_time, is_sync",
        "from wx_income",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="counterparty", property="counterparty", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="payment_method", property="paymentMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="tx_time", property="txTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_sync", property="isSync", jdbcType=JdbcType.VARCHAR)
    })
    WxIncome selectByPrimaryKey(String id);

    @UpdateProvider(type=WxIncomeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WxIncome record, @Param("example") WxIncomeExample example);

    @UpdateProvider(type=WxIncomeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WxIncome record, @Param("example") WxIncomeExample example);

    @UpdateProvider(type=WxIncomeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WxIncome record);

    @Update({
        "update wx_income",
        "set counterparty = #{counterparty,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "payment_method = #{paymentMethod,jdbcType=VARCHAR},",
          "amount = #{amount,jdbcType=DECIMAL},",
          "category = #{category,jdbcType=VARCHAR},",
          "tx_time = #{txTime,jdbcType=TIMESTAMP},",
          "is_sync = #{isSync,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(WxIncome record);
}