package top.yms.mas.mapper;

import top.yms.mas.entity.MyTagRecord;
import top.yms.mas.entity.MyTagRecordExample;
import top.yms.mas.entity.MyTagRecordExample.Criteria;
import top.yms.mas.entity.MyTagRecordExample.Criterion;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class MyTagRecordSqlProvider {

    public String countByExample(MyTagRecordExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("my_tag_record");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(MyTagRecordExample example) {
        BEGIN();
        DELETE_FROM("my_tag_record");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(MyTagRecord record) {
        BEGIN();
        INSERT_INTO("my_tag_record");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getTagId() != null) {
            VALUES("tag_id", "#{tagId,jdbcType=INTEGER}");
        }
        
        if (record.getRecordId() != null) {
            VALUES("record_id", "#{recordId,jdbcType=BIGINT}");
        }
        
        if (record.getRecordType() != null) {
            VALUES("record_type", "#{recordType,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String selectByExample(MyTagRecordExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("tag_id");
        SELECT("record_id");
        SELECT("record_type");
        FROM("my_tag_record");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        MyTagRecord record = (MyTagRecord) parameter.get("record");
        MyTagRecordExample example = (MyTagRecordExample) parameter.get("example");
        
        BEGIN();
        UPDATE("my_tag_record");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getTagId() != null) {
            SET("tag_id = #{record.tagId,jdbcType=INTEGER}");
        }
        
        if (record.getRecordId() != null) {
            SET("record_id = #{record.recordId,jdbcType=BIGINT}");
        }
        
        if (record.getRecordType() != null) {
            SET("record_type = #{record.recordType,jdbcType=VARCHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("my_tag_record");
        
        SET("id = #{record.id,jdbcType=BIGINT}");
        SET("tag_id = #{record.tagId,jdbcType=INTEGER}");
        SET("record_id = #{record.recordId,jdbcType=BIGINT}");
        SET("record_type = #{record.recordType,jdbcType=VARCHAR}");
        
        MyTagRecordExample example = (MyTagRecordExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(MyTagRecord record) {
        BEGIN();
        UPDATE("my_tag_record");
        
        if (record.getTagId() != null) {
            SET("tag_id = #{tagId,jdbcType=INTEGER}");
        }
        
        if (record.getRecordId() != null) {
            SET("record_id = #{recordId,jdbcType=BIGINT}");
        }
        
        if (record.getRecordType() != null) {
            SET("record_type = #{recordType,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }

    protected void applyWhere(MyTagRecordExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}