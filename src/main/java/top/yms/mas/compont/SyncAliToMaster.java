package top.yms.mas.compont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yms.mas.entity.AliExpend;
import top.yms.mas.entity.AliExpendExample;
import top.yms.mas.entity.MyExpend;
import top.yms.mas.entity.RestOut;
import top.yms.mas.mapper.AliExpendMapper;

import java.util.List;

@Component
public class SyncAliToMaster extends AbstractSyncToMaster{

    @Autowired
    private AliExpendMapper aliExpendMapper;

    @Override
    public RestOut doSync() {

        AliExpendExample expendExample = new AliExpendExample();
        AliExpendExample.Criteria criteria = expendExample.createCriteria();
        criteria.andIsSyncEqualTo(SYNC_FLAG);

        List<AliExpend> aliExpends = aliExpendMapper.selectByExample(expendExample);

        for (AliExpend aliExpend : aliExpends) {
            String id = aliExpend.getId();
            MyExpend myExpend = expendMapper.selectByOrderId(id);
            if (myExpend == null) {
                myExpend = new MyExpend();
                myExpend.setOrderId(aliExpend.getId());
                myExpend.setMoney(aliExpend.getAmount());
                myExpend.setPayType(aliExpend.getPaymentMethod());
                myExpend.setPayTime(aliExpend.getTxTime());
                myExpend.setCounterparty(aliExpend.getCounterparty());
                myExpend.setCategory(aliExpend.getCategory());
                myExpend.setName(aliExpend.getTitle());
                myExpend.setRemarks("支付宝账单");

                //insert 到 my_expend
                expendMapper.insert(myExpend);

                //update ali_expend 的is_sync
                aliExpend.setIsSync("1");
                aliExpendMapper.updateByPrimaryKey(aliExpend);
            }
        }

        return RestOut.succeed("Ok=>同步完成"+aliExpends.size()+"条数据");
    }
}
