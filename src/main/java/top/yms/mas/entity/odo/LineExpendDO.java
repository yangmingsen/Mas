package top.yms.mas.entity.odo;

import java.io.Serializable;
import java.math.BigDecimal;

public class LineExpendDO implements Serializable {
    private String date;
    private BigDecimal total;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "LineExpendDO{" +
                "date='" + date + '\'' +
                ", total=" + total +
                '}';
    }
}
