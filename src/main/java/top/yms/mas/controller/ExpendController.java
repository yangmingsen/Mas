package top.yms.mas.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yms.mas.compont.TestConpont;
import top.yms.mas.entity.MyExpendExample;
import top.yms.mas.entity.RestOut;
import top.yms.mas.entity.odo.AllExpendDO;
import top.yms.mas.entity.odo.MonthExpendDO;
import top.yms.mas.entity.odo.YearExpendDO;
import top.yms.mas.mapper.AliExpendMapper;
import top.yms.mas.service.ExpendService;


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


   // @Transactional
    @ApiOperation(value = "test")
    @GetMapping("/test")
    public RestOut doTest(String id) {
        testConpont.dotest(id);
        return RestOut.success("ok");
    }

    @ApiOperation(value = "listSearch")
    @GetMapping("/list")
    public PageInfo list(int pn, int ps) {
        MyExpendExample mee = new MyExpendExample();
        MyExpendExample.Criteria criteria = mee.createCriteria();
        criteria.andIdGreaterThan(3000l);

        return expendService.list(mee, pn, ps);
    }



    @ApiOperation(value = "获取月统计数据")
    @GetMapping("/getMonthExpend")
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


    @ApiOperation(value = "获取年统计数据")
    @GetMapping("/getYearExpend")
    public RestOut getYearExpend(String year) {

        if (StringUtils.isBlank(year) ) {
            return RestOut.error("year不能为空");
        }

        if (year.length() != 4 ) {
            return RestOut.error("year格式不正确");
        }


        try {
            new Integer(year);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestOut.error(e.getMessage());
        }

        YearExpendDO res = expendService.getYearExpend(year);

        return RestOut.success(res, "OK");
    }


    @ApiOperation(value = "获取统计数据")
    @GetMapping("/getAllExpend")
    public RestOut getAllExpend() {

        AllExpendDO res = expendService.getAllExpend();

        return RestOut.success(res, "OK");
    }

}
