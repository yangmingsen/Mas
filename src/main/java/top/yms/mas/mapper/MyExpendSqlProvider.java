package top.yms.mas.mapper;

import top.yms.mas.entity.MyExpend;
import top.yms.mas.entity.MyExpendExample;
import top.yms.mas.entity.MyExpendExample.Criteria;
import top.yms.mas.entity.MyExpendExample.Criterion;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class MyExpendSqlProvider {

    public String countByExample(MyExpendExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("my_expend");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(MyExpendExample example) {
        BEGIN();
        DELETE_FROM("my_expend");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(MyExpend record) {
        BEGIN();
        INSERT_INTO("my_expend");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getMoney() != null) {
            VALUES("money", "#{money,jdbcType=DECIMAL}");
        }
        
        if (record.getPayType() != null) {
            VALUES("pay_type", "#{payType,jdbcType=VARCHAR}");
        }
        
        if (record.getRemarks() != null) {
            VALUES("remarks", "#{remarks,jdbcType=VARCHAR}");
        }
        
        if (record.getPayTime() != null) {
            VALUES("pay_time", "#{payTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String selectByExample(MyExpendExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("name");
        SELECT("money");
        SELECT("pay_type");
        SELECT("remarks");
        SELECT("pay_time");
        FROM("my_expend");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        MyExpend record = (MyExpend) parameter.get("record");
        MyExpendExample example = (MyExpendExample) parameter.get("example");
        
        BEGIN();
        UPDATE("my_expend");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getMoney() != null) {
            SET("money = #{record.money,jdbcType=DECIMAL}");
        }
        
        if (record.getPayType() != null) {
            SET("pay_type = #{record.payType,jdbcType=VARCHAR}");
        }
        
        if (record.getRemarks() != null) {
            SET("remarks = #{record.remarks,jdbcType=VARCHAR}");
        }
        
        if (record.getPayTime() != null) {
            SET("pay_time = #{record.payTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("my_expend");
        
        SET("id = #{record.id,jdbcType=BIGINT}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("money = #{record.money,jdbcType=DECIMAL}");
        SET("pay_type = #{record.payType,jdbcType=VARCHAR}");
        SET("remarks = #{record.remarks,jdbcType=VARCHAR}");
        SET("pay_time = #{record.payTime,jdbcType=TIMESTAMP}");
        
        MyExpendExample example = (MyExpendExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(MyExpend record) {
        BEGIN();
        UPDATE("my_expend");
        
        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getMoney() != null) {
            SET("money = #{money,jdbcType=DECIMAL}");
        }
        
        if (record.getPayType() != null) {
            SET("pay_type = #{payType,jdbcType=VARCHAR}");
        }
        
        if (record.getRemarks() != null) {
            SET("remarks = #{remarks,jdbcType=VARCHAR}");
        }
        
        if (record.getPayTime() != null) {
            SET("pay_time = #{payTime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("id = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }

    protected void applyWhere(MyExpendExample example, boolean includeExamplePhrase) {
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