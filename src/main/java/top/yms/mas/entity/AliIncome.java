package top.yms.mas.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AliIncome {
    private String id;

    private String counterparty;

    private String title;

    private String paymentMethod;

    private BigDecimal amount;

    private String category;

    private Date txTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty == null ? null : counterparty.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod == null ? null : paymentMethod.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Date getTxTime() {
        return txTime;
    }

    public void setTxTime(Date txTime) {
        this.txTime = txTime;
    }
}