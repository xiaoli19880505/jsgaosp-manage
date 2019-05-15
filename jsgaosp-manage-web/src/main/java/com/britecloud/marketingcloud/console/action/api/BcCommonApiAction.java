package com.britecloud.marketingcloud.console.action.api;

import com.britecloud.marketingcloud.service.BcAreaService;
import com.britecloud.marketingcloud.service.BcCodeService;
import com.britecloud.marketingcloud.service.BcLinkInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/common")
@Api(value = "BcCommonApiAction", tags = "共用信息API", description = "共用信息")
public class BcCommonApiAction {
    @Autowired
    private BcAreaService bcAreaService;

    @Autowired
    private BcCodeService bcCodeService;

    @Autowired
    private BcLinkInfoService bcLinkInfoService;

    @RequestMapping(value = "getAreaList", method = RequestMethod.GET)
    @ApiOperation(value = "query", httpMethod = "GET", notes = "行政区", produces = "application/json", response = String.class)
    @ApiImplicitParam(name = "channel",
            value = "渠道 01-PC端、02-微信端",
            dataType = "Integer",
            required = true,
            paramType = "query")
    @ResponseBody
    public String getAreaList(@RequestParam(value = "channel", required = true) Integer channel){
        String result = bcAreaService.getAreaList();
        return result;
    }

    @RequestMapping(value = "getCodeList", method = RequestMethod.GET)
    @ApiOperation(value = "query", httpMethod = "GET", notes = "字典查询", produces = "application/json", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "channel",
                    value = "渠道 01-PC端、02-微信端",
                    dataType = "Integer",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "sortCode",
                    value = "参数类型: yw_type -业务类型、xz_type -行政类别、bl_type -办理类型（不见面、见一次面等）、server_type -服务类型（法人、个人）、link_type -友情链接类型",
                    dataType = "String",
                    required = true,
                    paramType = "query")
    })
    @ResponseBody
    public String getCodeList(@RequestParam(value = "channel", required = true) Integer channel,
                              @RequestParam(value = "sortCode", required = true) String sortCode){
        String result = bcCodeService.getCodeList(channel, sortCode);
        return result;
    }

    @RequestMapping(value = "getLinkInfoList", method = RequestMethod.GET)
    @ApiOperation(value = "query", httpMethod = "GET", notes = "友情链接查询", produces = "application/json", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "channel",
                    value = "渠道 01-PC端、02-微信端",
                    dataType = "Integer",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "linkType",
                    value = "类型: 非必填， 不填查询全部",
                    dataType = "String",
                    required = false,
                    paramType = "query")
    })
    @ResponseBody
    public String getLinkInfoList(@RequestParam(value = "channel", required = true) Integer channel,
                              @RequestParam(value = "linkType", required = false) String linkType){
        String result = bcLinkInfoService.getLinkInfoList(channel, linkType);
        return result;
    }
}
