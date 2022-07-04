package top.yms.mas.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.yms.mas.entity.WxExpend;
import top.yms.mas.entity.WxExpendExample;

import java.util.List;

@Mapper
public interface WxExpendMapper {
    @SelectProvider(type=WxExpendSqlProvider.class, method="countByExample")
    int countByExample(WxExpendExample example);

    @DeleteProvider(type=WxExpendSqlProvider.class, method="deleteByExample")
    int deleteByExample(WxExpendExample example);

    @Delete({
        "delete from wx_expend",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into wx_expend (id, counterparty, ",
        "title, payment_method, ",
        "amount, category, ",
        "tx_time)",
        "values (#{id,jdbcType=VARCHAR}, #{counterparty,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{paymentMethod,jdbcType=VARCHAR}, ",
        "#{amount,jdbcType=DECIMAL}, #{category,jdbcType=VARCHAR}, ",
        "#{txTime,jdbcType=TIMESTAMP})"
    })
    int insert(WxExpend record);

    @InsertProvider(type=WxExpendSqlProvider.class, method="insertSelective")
    int insertSelective(WxExpend record);

    @SelectProvider(type=WxExpendSqlProvider.class, method="selectByExample")
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
    List<WxExpend> selectByExample(WxExpendExample example);

    @Select({
        "select",
        "id, counterparty, title, payment_method, amount, category, tx_time, is_sync",
        "from wx_expend",
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
    WxExpend selectByPrimaryKey(String id);

    @UpdateProvider(type=WxExpendSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WxExpend record, @Param("example") WxExpendExample example);

    @UpdateProvider(type=WxExpendSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WxExpend record, @Param("example") WxExpendExample example);

    @UpdateProvider(type=WxExpendSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WxExpend record);

    @Update({
        "update wx_expend",
        "set counterparty = #{counterparty,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "payment_method = #{paymentMethod,jdbcType=VARCHAR},",
          "amount = #{amount,jdbcType=DECIMAL},",
          "category = #{category,jdbcType=VARCHAR},",
          "tx_time = #{txTime,jdbcType=TIMESTAMP},",
          "is_sync = #{isSync,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(WxExpend record);
}