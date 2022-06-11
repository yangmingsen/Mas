package top.yms.mas.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import top.yms.mas.entity.MyTagRecord;
import top.yms.mas.entity.MyTagRecordExample;

public interface MyTagRecordMapper {
    @SelectProvider(type=MyTagRecordSqlProvider.class, method="countByExample")
    int countByExample(MyTagRecordExample example);

    @DeleteProvider(type=MyTagRecordSqlProvider.class, method="deleteByExample")
    int deleteByExample(MyTagRecordExample example);

    @Delete({
        "delete from my_tag_record",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into my_tag_record (id, tag_id, ",
        "record_id, record_type)",
        "values (#{id,jdbcType=BIGINT}, #{tagId,jdbcType=INTEGER}, ",
        "#{recordId,jdbcType=BIGINT}, #{recordType,jdbcType=VARCHAR})"
    })
    int insert(MyTagRecord record);

    @InsertProvider(type=MyTagRecordSqlProvider.class, method="insertSelective")
    int insertSelective(MyTagRecord record);

    @SelectProvider(type=MyTagRecordSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="tag_id", property="tagId", jdbcType=JdbcType.INTEGER),
        @Result(column="record_id", property="recordId", jdbcType=JdbcType.BIGINT),
        @Result(column="record_type", property="recordType", jdbcType=JdbcType.VARCHAR)
    })
    List<MyTagRecord> selectByExample(MyTagRecordExample example);

    @Select({
        "select",
        "id, tag_id, record_id, record_type",
        "from my_tag_record",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="tag_id", property="tagId", jdbcType=JdbcType.INTEGER),
        @Result(column="record_id", property="recordId", jdbcType=JdbcType.BIGINT),
        @Result(column="record_type", property="recordType", jdbcType=JdbcType.VARCHAR)
    })
    MyTagRecord selectByPrimaryKey(Long id);

    @UpdateProvider(type=MyTagRecordSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MyTagRecord record, @Param("example") MyTagRecordExample example);

    @UpdateProvider(type=MyTagRecordSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MyTagRecord record, @Param("example") MyTagRecordExample example);

    @UpdateProvider(type=MyTagRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MyTagRecord record);

    @Update({
        "update my_tag_record",
        "set tag_id = #{tagId,jdbcType=INTEGER},",
          "record_id = #{recordId,jdbcType=BIGINT},",
          "record_type = #{recordType,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MyTagRecord record);
}