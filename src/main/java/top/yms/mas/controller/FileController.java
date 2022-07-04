package top.yms.mas.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.yms.mas.compont.RecordHandlerFacade;
import top.yms.mas.entity.RestOut;

@Api(value = "文件上传", tags = {"文件上传"})

@RestController
@RequestMapping("/file/upload")
public class FileController {

    @Autowired
    private RecordHandlerFacade recordHandlerFacade;


    @Transactional
    @ApiOperation(value = "支付宝账单上传")
    @PostMapping("/ali")
    public RestOut uploadAli(@RequestParam(value = "file") MultipartFile file) {
        if (file == null) return RestOut.error("上传文件为空");
        return recordHandlerFacade.parseAliRecord(file);
    }

    @Transactional
    @ApiOperation(value = "微信账单上传")
    @PostMapping("/wx")
    public RestOut uploadWX(@RequestParam(value = "file") MultipartFile file) {
        if (file == null) return RestOut.error("上传文件为空");
        return recordHandlerFacade.parseWxRecord(file);
    }
}
