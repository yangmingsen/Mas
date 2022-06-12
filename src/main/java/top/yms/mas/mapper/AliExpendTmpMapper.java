package top.yms.mas.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.yms.mas.entity.AliExpendTmp;
import top.yms.mas.entity.AliExpendTmpExample;

import java.util.List;

@Mapper
public interface AliExpendTmpMapper {
    @SelectProvider(type=AliExpendTmpSqlProvider.class, method="countByExample")
    int countByExample(AliExpendTmpExample example);

    @DeleteProvider(type=AliExpendTmpSqlProvider.class, method="deleteByExample")
    int deleteByExample(AliExpendTmpExample example);

    @Delete({
        "delete from ali_expend_tmp",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into ali_expend_tmp (id, counterparty, ",
        "title, payment_method, ",
        "amount, category, ",
        "tx_time, tx_status)",
        "values (#{id,jdbcType=VARCHAR}, #{counterparty,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{paymentMethod,jdbcType=VARCHAR}, ",
        "#{amount,jdbcType=DECIMAL}, #{category,jdbcType=VARCHAR}, ",
        "#{txTime,jdbcType=TIMESTAMP}, #{txStatus,jdbcType=VARCHAR})"
    })
    int insert(AliExpendTmp record);

    @InsertProvider(type=AliExpendTmpSqlProvider.class, method="insertSelective")
    int insertSelective(AliExpendTmp record);

    @SelectProvider(type=AliExpendTmpSqlProvider.class, method="selectByExample")
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
    List<AliExpendTmp> selectByExample(AliExpendTmpExample example);

    @Select({
        "select",
        "id, counterparty, title, payment_method, amount, category, tx_time, tx_status",
        "from ali_expend_tmp",
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
    AliExpendTmp selectByPrimaryKey(String id);

    @UpdateProvider(type=AliExpendTmpSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AliExpendTmp record, @Param("example") AliExpendTmpExample example);

    @UpdateProvider(type=AliExpendTmpSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AliExpendTmp record, @Param("example") AliExpendTmpExample example);

    @UpdateProvider(type=AliExpendTmpSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AliExpendTmp record);

    @Update({
        "update ali_expend_tmp",
        "set counterparty = #{counterparty,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "payment_method = #{paymentMethod,jdbcType=VARCHAR},",
          "amount = #{amount,jdbcType=DECIMAL},",
          "category = #{category,jdbcType=VARCHAR},",
          "tx_time = #{txTime,jdbcType=TIMESTAMP},",
          "tx_status = #{txStatus,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AliExpendTmp record);
}