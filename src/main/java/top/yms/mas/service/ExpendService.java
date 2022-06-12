package top.yms.mas.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yms.mas.entity.MyExpend;
import top.yms.mas.entity.MyExpendExample;
import top.yms.mas.entity.odo.LineExpendDO;
import top.yms.mas.entity.odo.MonthExpendDO;
import top.yms.mas.mapper.MyExpendMapper;
import top.yms.mas.utils.DateHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpendService {



    @Autowired
    private MyExpendMapper expendMapper;

    /**
     *
     * @param expendExample
     * @param pn 页码
     * @param ps 每页显示数量
     * @return
     */
    public PageInfo list(MyExpendExample expendExample, int pn, int ps) {
        PageHelper.startPage(pn,ps);
        List<MyExpend> list = expendMapper.selectByExample(expendExample);
        PageInfo<MyExpend> res = new PageInfo<>(list);

        return res;
    }

    public MonthExpendDO getMonthExpend(String year, String month) {
        List<LineExpendDO> expends = expendMapper.getMonthExpend(year, month);

        MonthExpendDO monthExpend = new MonthExpendDO();
        //处理数据库中不存在的情况
        if (expends == null || expends.size() == 0) {
            int days = DateHelper.getDays(new Integer(year), new Integer(month));
            expends = new ArrayList<>();
            BigDecimal zeroValue = new BigDecimal("0.0");
            for(int i=1; i<=days; i++) {
                if (i <= 10) {
                    expends.add(new LineExpendDO("0"+i, zeroValue));
                } else {
                    expends.add(new LineExpendDO(i+"", zeroValue));
                }
            }
            monthExpend.setTotalExpend(zeroValue);

        } else {
            BigDecimal totalExpend = new BigDecimal("0.0");
            for(LineExpendDO exp : expends) {
                totalExpend = totalExpend.add(exp.getTotal());
            }
            monthExpend.setTotalExpend(totalExpend);
        }
        monthExpend.setLineExpends(expends);


        return monthExpend;
    }

}
