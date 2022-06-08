package top.yms.mas.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.yms.mas.entity.RestOut;

@Api(value = "文件上传", tags = {"文件上传"})

@RestController
@RequestMapping("/file")
public class FileController {

    @ApiOperation(value = "支付宝账单上传")
    @PostMapping("/upload/ali")
    public RestOut uploadAli(
            @RequestParam(value = "file") MultipartFile file) {

        return RestOut.error("失败");
    }

    @ApiOperation(value = "微信账单上传")
    @PostMapping("/upload/wx")
    public RestOut uploadWX(
            @RequestParam(value = "file") MultipartFile file) {

        return RestOut.error("失败");
    }
}
