package top.yms.mas.mapper;

import top.yms.mas.entity.AliExpendTmp;
import top.yms.mas.entity.AliExpendTmpExample;
import top.yms.mas.entity.AliExpendTmpExample.Criteria;
import top.yms.mas.entity.AliExpendTmpExample.Criterion;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class AliExpendTmpSqlProvider {

    public String countByExample(AliExpendTmpExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("ali_expend_tmp");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(AliExpendTmpExample example) {
        BEGIN();
        DELETE_FROM("ali_expend_tmp");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(AliExpendTmp record) {
        BEGIN();
        INSERT_INTO("ali_expend_tmp");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getCounterparty() != null) {
            VALUES("counterparty", "#{counterparty,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getPaymentMethod() != null) {
            VALUES("payment_method", "#{paymentMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getAmount() != null) {
            VALUES("amount", "#{amount,jdbcType=DECIMAL}");
        }
        
        if (record.getCategory() != null) {
            VALUES("category", "#{category,jdbcType=VARCHAR}");
        }
        
        if (record.getTxTime() != null) {
            VALUES("tx_time", "#{txTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getTxStatus() != null) {
            VALUES("tx_status", "#{txStatus,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String selectByExample(AliExpendTmpExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("counterparty");
        SELECT("title");
        SELECT("payment_method");
        SELECT("amount");
        SELECT("category");
        SELECT("tx_time");
        SELECT("tx_status");
        FROM("ali_expend_tmp");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        AliExpendTmp record = (AliExpendTmp) parameter.get("record");
        AliExpendTmpExample example = (AliExpendTmpExample) parameter.get("example");
        
        BEGIN();
        UPDATE("ali_expend_tmp");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=VARCHAR}");
        }
        
        if (record.getCounterparty() != null) {
            SET("counterparty = #{record.counterparty,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            SET("title = #{record.title,jdbcType=VARCHAR}");
        }
        
        if (record.getPaymentMethod() != null) {
            SET("payment_method = #{record.paymentMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getAmount() != null) {
            SET("amount = #{record.amount,jdbcType=DECIMAL}");
        }
        
        if (record.getCategory() != null) {
            SET("category = #{record.category,jdbcType=VARCHAR}");
        }
        
        if (record.getTxTime() != null) {
            SET("tx_time = #{record.txTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getTxStatus() != null) {
            SET("tx_status = #{record.txStatus,jdbcType=VARCHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("ali_expend_tmp");
        
        SET("id = #{record.id,jdbcType=VARCHAR}");
        SET("counterparty = #{record.counterparty,jdbcType=VARCHAR}");
        SET("title = #{record.title,jdbcType=VARCHAR}");
        SET("payment_method = #{record.paymentMethod,jdbcType=VARCHAR}");
        SET("amount = #{record.amount,jdbcType=DECIMAL}");
        SET("category = #{record.category,jdbcType=VARCHAR}");
        SET("tx_time = #{record.txTime,jdbcType=TIMESTAMP}");
        SET("tx_status = #{record.txStatus,jdbcType=VARCHAR}");
        
        AliExpendTmpExample example = (AliExpendTmpExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(AliExpendTmp record) {
        BEGIN();
        UPDATE("ali_expend_tmp");
        
        if (record.getCounterparty() != null) {
            SET("counterparty = #{counterparty,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getPaymentMethod() != null) {
            SET("payment_method = #{paymentMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getAmount() != null) {
            SET("amount = #{amount,jdbcType=DECIMAL}");
        }
        
        if (record.getCategory() != null) {
            SET("category = #{category,jdbcType=VARCHAR}");
        }
        
        if (record.getTxTime() != null) {
            SET("tx_time = #{txTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getTxStatus() != null) {
            SET("tx_status = #{txStatus,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return SQL();
    }

    protected void applyWhere(AliExpendTmpExample example, boolean includeExamplePhrase) {
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