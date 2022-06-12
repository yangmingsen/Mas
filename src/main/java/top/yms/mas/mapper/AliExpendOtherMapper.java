package top.yms.mas.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.yms.mas.entity.AliExpendOther;
import top.yms.mas.entity.AliExpendOtherExample;

import java.util.List;

@Mapper
public interface AliExpendOtherMapper {
    @SelectProvider(type=AliExpendOtherSqlProvider.class, method="countByExample")
    int countByExample(AliExpendOtherExample example);

    @DeleteProvider(type=AliExpendOtherSqlProvider.class, method="deleteByExample")
    int deleteByExample(AliExpendOtherExample example);

    @Delete({
        "delete from ali_expend_other",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into ali_expend_other (id, counterparty, ",
        "title, payment_method, ",
        "amount, category, ",
        "tx_time, tx_status)",
        "values (#{id,jdbcType=VARCHAR}, #{counterparty,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{paymentMethod,jdbcType=VARCHAR}, ",
        "#{amount,jdbcType=DECIMAL}, #{category,jdbcType=VARCHAR}, ",
        "#{txTime,jdbcType=TIMESTAMP}, #{txStatus,jdbcType=VARCHAR})"
    })
    int insert(AliExpendOther record);

    @InsertProvider(type=AliExpendOtherSqlProvider.class, method="insertSelective")
    int insertSelective(AliExpendOther record);

    @SelectProvider(type=AliExpendOtherSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="counterparty", property="counterparty", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="payment_method", property="paymentMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="tx_time", property="txTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="tx_status", property="txStatus", jdbcType=JdbcType.VARCHAR)
    })
    List<AliExpendOther> selectByExample(AliExpendOtherExample example);

    @Select({
        "select",
        "id, counterparty, title, payment_method, amount, category, tx_time, tx_status",
        "from ali_expend_other",
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
        @Result(column="tx_status", property="txStatus", jdbcType=JdbcType.VARCHAR)
    })
    AliExpendOther selectByPrimaryKey(String id);

    @UpdateProvider(type=AliExpendOtherSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AliExpendOther record, @Param("example") AliExpendOtherExample example);

    @UpdateProvider(type=AliExpendOtherSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AliExpendOther record, @Param("example") AliExpendOtherExample example);

    @UpdateProvider(type=AliExpendOtherSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AliExpendOther record);

    @Update({
        "update ali_expend_other",
        "set counterparty = #{counterparty,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "payment_method = #{paymentMethod,jdbcType=VARCHAR},",
          "amount = #{amount,jdbcType=DECIMAL},",
          "category = #{category,jdbcType=VARCHAR},",
          "tx_time = #{txTime,jdbcType=TIMESTAMP},",
          "tx_status = #{txStatus,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AliExpendOther record);
}