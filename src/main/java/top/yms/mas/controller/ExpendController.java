package top.yms.mas.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yms.mas.entity.MyExpend;
import top.yms.mas.entity.MyExpendExample;
import top.yms.mas.service.ExpendService;


@Api(value = "Expend", tags = {"Expend"})

@RequestMapping("/expend")
@RestController
public class ExpendController {

    @Autowired
    private ExpendService expendService;


    @ApiOperation(value = "listSearch")
    @RequestMapping("/list")
    public PageInfo list(int pn, int ps) {
        MyExpendExample mee = new MyExpendExample();
        MyExpendExample.Criteria criteria = mee.createCriteria();
        criteria.andIdGreaterThan(3000l);

        return expendService.list(mee, pn, ps);
    }

}
