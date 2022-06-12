package top.yms.mas.compont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.yms.mas.entity.AliExpend;
import top.yms.mas.mapper.AliExpendMapper;

@Component
public class TestConpont {

    @Autowired
    private AliExpendMapper aliExpendMapper;

    int cnt=0;


    public void dotest1(String id) throws RuntimeException {
        AliExpend aliExpend = new AliExpend();
        aliExpend.setId(id);

        aliExpendMapper.insert(aliExpend);
        cnt++;
        if (cnt %2 ==0) throw new RuntimeException("insert error");
    }

    public void dotest(String id){
            dotest1(id);
    }

}
