package top.yms.mas.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yms.mas.entity.MyExpend;
import top.yms.mas.entity.MyExpendExample;
import top.yms.mas.mapper.MyExpendMapper;

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

}
