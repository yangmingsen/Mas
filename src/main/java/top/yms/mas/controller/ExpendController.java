package top.yms.mas.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yms.mas.compont.TestConpont;
import top.yms.mas.entity.AliExpend;
import top.yms.mas.entity.MyExpend;
import top.yms.mas.entity.MyExpendExample;
import top.yms.mas.entity.RestOut;
import top.yms.mas.entity.odo.LineExpendDO;
import top.yms.mas.entity.odo.MonthExpendDO;
import top.yms.mas.mapper.AliExpendMapper;
import top.yms.mas.service.ExpendService;

import java.util.List;


@Api(value = "Expend", tags = {"Expend"})

@RequestMapping("/expend")
@RestController
public class ExpendController {

    @Autowired
    private ExpendService expendService;

    private static final Logger logger = LoggerFactory.getLogger(ExpendController.class);


    @Autowired
    private AliExpendMapper aliExpendMapper;

    private int cnt =0;

    @Autowired
    private TestConpont testConpont;

    @Transactional
    @ApiOperation(value = "test")
    @RequestMapping("/test")
    public RestOut doTest(String id) {
        testConpont.dotest(id);
        return RestOut.success("ok");
    }

    @ApiOperation(value = "listSearch")
    @RequestMapping("/list")
    public PageInfo list(int pn, int ps) {
        MyExpendExample mee = new MyExpendExample();
        MyExpendExample.Criteria criteria = mee.createCriteria();
        criteria.andIdGreaterThan(3000l);

        return expendService.list(mee, pn, ps);
    }



    @ApiOperation(value = "获取月统计数据")
    @RequestMapping("/getMonthExpend")
    public RestOut getMonthExpend(String year, String month) {
        if (StringUtils.isBlank(year) ||StringUtils.isBlank(month)) {
            return RestOut.error("year或month不能为空");
        }
        if (year.length() != 4 || month.length()>2) {
            return RestOut.error("year或month格式不正确");
        }
        try {
            new Integer(year);
            new Integer(month);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestOut.error(e.getMessage());
        }

        MonthExpendDO res =  expendService.getMonthExpend(year, month);

        return RestOut.success(res, "OK");

    }

}
