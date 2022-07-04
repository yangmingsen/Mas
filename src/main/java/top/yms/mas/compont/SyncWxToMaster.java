package top.yms.mas.compont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yms.mas.entity.*;
import top.yms.mas.mapper.WxExpendMapper;

import java.util.List;

@Component
public class SyncWxToMaster extends AbstractSyncToMaster{

    @Autowired
    private WxExpendMapper wxExpendMapper;

    @Override
    public RestOut doSync() {
        WxExpendExample wxExpendExample = new WxExpendExample();
        WxExpendExample.Criteria criteria = wxExpendExample.createCriteria();
        criteria.andIsSyncEqualTo(SYNC_FLAG);

        List<WxExpend> wxExpends = wxExpendMapper.selectByExample(wxExpendExample);

        for (WxExpend wxExpend : wxExpends) {
            String id = wxExpend.getId();
            MyExpend myExpend = expendMapper.selectByOrderId(id);
            if (myExpend == null) {
                myExpend = new MyExpend();
                myExpend.setOrderId(wxExpend.getId());
                myExpend.setMoney(wxExpend.getAmount());
                myExpend.setPayType(wxExpend.getPaymentMethod());
                myExpend.setPayTime(wxExpend.getTxTime());
                myExpend.setCounterparty(wxExpend.getCounterparty());
                myExpend.setCategory(wxExpend.getCategory());
                myExpend.setName(wxExpend.getTitle());
                myExpend.setRemarks("微信账单");

                //insert 到 my_expend
                expendMapper.insert(myExpend);

                //update ali_expend 的is_sync
                wxExpend.setIsSync("1");
                wxExpendMapper.updateByPrimaryKey(wxExpend);
            }
        }

        return RestOut.succeed("Ok=>同步完成"+wxExpends.size()+"条数据");

    }
}
