package top.yms.mas.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.yms.mas.compont.RecordHandlerFacade;
import top.yms.mas.entity.RestOut;

@Api(value = "数据同步", tags = {"数据同步"})
@RequestMapping("/sync")
public class SyncController {

    @Autowired
    private RecordHandlerFacade recordHandlerFacade;

    @ApiOperation(value = "微信账单同步")
    @Transactional
    @GetMapping("/wx")
    public RestOut wx() {
        return recordHandlerFacade.syncWx();
    }

    @ApiOperation(value = "支付宝账单同步")
    @Transactional
    @GetMapping("/ali")
    public RestOut ali() {
        return recordHandlerFacade.syncAli();
    }

}
