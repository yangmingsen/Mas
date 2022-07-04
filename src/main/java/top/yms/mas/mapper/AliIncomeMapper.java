package top.yms.mas.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.yms.mas.entity.AliIncome;
import top.yms.mas.entity.AliIncomeExample;

import java.util.List;

@Mapper
public interface AliIncomeMapper {
    @SelectProvider(type=AliIncomeSqlProvider.class, method="countByExample")
    int countByExample(AliIncomeExample example);

    @DeleteProvider(type=AliIncomeSqlProvider.class, method="deleteByExample")
    int deleteByExample(AliIncomeExample example);

    @Delete({
            "delete from ali_income",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into ali_income (id, counterparty, ",
            "title, payment_method, ",
            "amount, category, ",
            "tx_time)",
            "values (#{id,jdbcType=VARCHAR}, #{counterparty,jdbcType=VARCHAR}, ",
            "#{title,jdbcType=VARCHAR}, #{paymentMethod,jdbcType=VARCHAR}, ",
            "#{amount,jdbcType=DECIMAL}, #{category,jdbcType=VARCHAR}, ",
            "#{txTime,jdbcType=TIMESTAMP})"
    })
    int insert(AliIncome record);

    @InsertProvider(type=AliIncomeSqlProvider.class, method="insertSelective")
    int insertSelective(AliIncome record);

    @SelectProvider(type=AliIncomeSqlProvider.class, method="selectByExample")
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
    List<AliIncome> selectByExample(AliIncomeExample example);

    @Select({
            "select",
            "id, counterparty, title, payment_method, amount, category, tx_time, is_sync",
            "from ali_income",
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
    AliIncome selectByPrimaryKey(String id);

    @UpdateProvider(type=AliIncomeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AliIncome record, @Param("example") AliIncomeExample example);

    @UpdateProvider(type=AliIncomeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AliIncome record, @Param("example") AliIncomeExample example);

    @UpdateProvider(type=AliIncomeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AliIncome record);

    @Update({
            "update ali_income",
            "set counterparty = #{counterparty,jdbcType=VARCHAR},",
            "title = #{title,jdbcType=VARCHAR},",
            "payment_method = #{paymentMethod,jdbcType=VARCHAR},",
            "amount = #{amount,jdbcType=DECIMAL},",
            "category = #{category,jdbcType=VARCHAR},",
            "tx_time = #{txTime,jdbcType=TIMESTAMP},",
            "is_sync = #{isSync,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AliIncome record);
}