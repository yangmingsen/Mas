package top.yms.mas.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AliExpendTmp extends AliExpend {
    private String txStatus;

    public String getTxStatus() {
        return txStatus;
    }

    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus == null ? null : txStatus.trim();
    }

    @Override
    public String toString() {
        return "AliExpendTmp{" + super.toString()+
                " txStatus='" + txStatus + '\'' +
                '}';
    }
}