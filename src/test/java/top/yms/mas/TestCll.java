package top.yms.mas;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestCll {
    @Test
    public void dd() {
        List<BigDecimal> list = new ArrayList<>();
        for(int i=0; i<10; i++) {
            list.add(new BigDecimal(i+".34"));
        }

        BigDecimal bigDecimal = new BigDecimal("0.0");
        for(BigDecimal ss : list) {
            bigDecimal = bigDecimal.add(ss);
        }

        System.out.println(bigDecimal.toString());
    }
}
