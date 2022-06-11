package top.yms.mas.entity.odo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class MonthExpendDO implements Serializable {
    private List<LineExpendDO> lineExpends;
    private BigDecimal totalExpend;

    public List<LineExpendDO> getLineExpends() {
        return lineExpends;
    }

    public void setLineExpends(List<LineExpendDO> lineExpends) {
        this.lineExpends = lineExpends;
    }

    public BigDecimal getTotalExpend() {
        return totalExpend;
    }

    public void setTotalExpend(BigDecimal totalExpend) {
        this.totalExpend = totalExpend;
    }

    @Override
    public String toString() {
        return "MonthExpendDO{" +
                "lineExpends=" + lineExpends +
                ", totalExpend=" + totalExpend +
                '}';
    }
}
