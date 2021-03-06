package top.yms.mas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.yms.mas.entity.MyExpend;
import top.yms.mas.entity.MyExpendExample;
import top.yms.mas.entity.odo.LineExpendDO;

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
        "remarks, pay_time, ",
        "category, counterparty, ",
        "order_id)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{money,jdbcType=DECIMAL}, #{payType,jdbcType=VARCHAR}, ",
        "#{remarks,jdbcType=VARCHAR}, #{payTime,jdbcType=TIMESTAMP}, ",
        "#{category,jdbcType=VARCHAR}, #{counterparty,jdbcType=VARCHAR}, ",
        "#{orderId,jdbcType=VARCHAR})"
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
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="counterparty", property="counterparty", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.VARCHAR)
    })
    List<MyExpend> selectByExample(MyExpendExample example);

    @Select({
        "select",
        "id, name, money, pay_type, remarks, pay_time, category, counterparty, order_id",
        "from my_expend",
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
          "pay_time = #{payTime,jdbcType=TIMESTAMP},",
          "category = #{category,jdbcType=VARCHAR},",
          "counterparty = #{counterparty,jdbcType=VARCHAR},",
          "order_id = #{orderId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MyExpend record);


    @Select({
            "select",
            "id, name, money, pay_type, remarks, pay_time, category, counterparty, order_id",
            "from my_expend",
            "where order_id = #{orderId,jdbcType=VARCHAR}"
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
    MyExpend selectByOrderId(String orderId);


    @Select({
            "SELECT\n" +
                    "\tSUBSTR(days, 9,9) as day, sum(money)\n as total " +
                    "FROM\n" +
                    "\t(\n" +
                    "\tSELECT NAME,\n" +
                    "\t\tmoney,\n" +
                    "\t\tDATE_FORMAT( pay_time, \"%Y-%m-%d\" ) days \n" +
                    "\tFROM\n" +
                    "\t\t`my_expend` \n" +
                    "\tWHERE\n" +
                    "\t\tYEAR ( pay_time ) = #{year,jdbcType=VARCHAR} \n" +
                    "\t\tAND MONTH ( pay_time ) = #{month,jdbcType=VARCHAR}  \n" +
                    "\t) t1  GROUP BY t1.days ORDER BY days asc;"
    })
    @Results({
            @Result(column="day", property="date", jdbcType=JdbcType.VARCHAR),
            @Result(column="total", property="total", jdbcType=JdbcType.DECIMAL),
    })
    List<LineExpendDO> getMonthExpend(@Param("year") String year, @Param("month") String month);


    @Select({
            "SELECT\n" +
                    "\tdays,\n" +
                    "\tsum( money ) AS total \n" +
                    "FROM\n" +
                    "\t(\n" +
                    "\tSELECT\n" +
                    "\t\tmoney,\n" +
                    "\t\tDATE_FORMAT( pay_time, \"%Y-%m\" ) days \n" +
                    "\tFROM\n" +
                    "\t\t`my_expend` \n" +
                    "\tWHERE\n" +
                    "\t\tYEAR ( pay_time ) = #{year,jdbcType=VARCHAR}\n" +
                    "\t\t \n" +
                    "\t) t1 \n" +
                    "GROUP BY\n" +
                    "\tt1.days \n" +
                    "ORDER BY\n" +
                    "\tdays ASC"
    })
    @Results({
            @Result(column="days", property="date", jdbcType=JdbcType.VARCHAR),
            @Result(column="total", property="total", jdbcType=JdbcType.DECIMAL),
    })
    List<LineExpendDO> getYearExpend(@Param("year") String year);



    @Select({
            "SELECT\n" +
                    "\tdays,\n" +
                    "\tsum( money ) AS total \n" +
                    "FROM\n" +
                    "\t( SELECT money, DATE_FORMAT( pay_time, \"%Y\" ) days FROM `my_expend` ) t1 \n" +
                    "GROUP BY\n" +
                    "\tt1.days \n" +
                    "ORDER BY\n" +
                    "\tdays ASC "
    })
    @Results({
            @Result(column="days", property="date", jdbcType=JdbcType.VARCHAR),
            @Result(column="total", property="total", jdbcType=JdbcType.DECIMAL),
    })
    List<LineExpendDO> getAllExpend();
}