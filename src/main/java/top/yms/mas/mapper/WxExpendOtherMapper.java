package top.yms.mas.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.yms.mas.entity.WxExpendOther;
import top.yms.mas.entity.WxExpendOtherExample;

import java.util.List;

public interface WxExpendOtherMapper {
    @SelectProvider(type=WxExpendOtherSqlProvider.class, method="countByExample")
    int countByExample(WxExpendOtherExample example);

    @DeleteProvider(type=WxExpendOtherSqlProvider.class, method="deleteByExample")
    int deleteByExample(WxExpendOtherExample example);

    @Delete({
        "delete from wx_expend_other",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into wx_expend_other (id, counterparty, ",
        "title, payment_method, ",
        "amount, category, ",
        "tx_time)",
        "values (#{id,jdbcType=VARCHAR}, #{counterparty,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{paymentMethod,jdbcType=VARCHAR}, ",
        "#{amount,jdbcType=DECIMAL}, #{category,jdbcType=VARCHAR}, ",
        "#{txTime,jdbcType=TIMESTAMP})"
    })
    int insert(WxExpendOther record);

    @InsertProvider(type=WxExpendOtherSqlProvider.class, method="insertSelective")
    int insertSelective(WxExpendOther record);

    @SelectProvider(type=WxExpendOtherSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="counterparty", property="counterparty", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="payment_method", property="paymentMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="tx_time", property="txTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<WxExpendOther> selectByExample(WxExpendOtherExample example);

    @Select({
        "select",
        "id, counterparty, title, payment_method, amount, category, tx_time",
        "from wx_expend_other",
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
    WxExpendOther selectByPrimaryKey(String id);

    @UpdateProvider(type=WxExpendOtherSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WxExpendOther record, @Param("example") WxExpendOtherExample example);

    @UpdateProvider(type=WxExpendOtherSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WxExpendOther record, @Param("example") WxExpendOtherExample example);

    @UpdateProvider(type=WxExpendOtherSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WxExpendOther record);

    @Update({
        "update wx_expend_other",
        "set counterparty = #{counterparty,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "payment_method = #{paymentMethod,jdbcType=VARCHAR},",
          "amount = #{amount,jdbcType=DECIMAL},",
          "category = #{category,jdbcType=VARCHAR},",
          "tx_time = #{txTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(WxExpendOther record);
}