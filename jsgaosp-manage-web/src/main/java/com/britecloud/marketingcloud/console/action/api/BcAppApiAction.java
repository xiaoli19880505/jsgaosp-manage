package com.britecloud.marketingcloud.console.action.api;

import com.britecloud.marketingcloud.model.Pageable;
import com.britecloud.marketingcloud.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/application")
@Api(value = "BcAppApiAction", tags = "应用列表API", description = "应用列表")
public class BcAppApiAction {

    @Autowired
    private ApplicationService applicationService;

    /**
     * 查询某一组织及其下属部门的应用
     * @param channel
     * @return
     */
    @RequestMapping(value = "getApproves", method = RequestMethod.GET)
    @ApiOperation(value = "query", httpMethod = "GET", notes = "省级页应用列表", produces = "application/json", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "channel",
                    value = "渠道 01-PC端、02-微信端",
                    dataType = "Integer",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "pageNo",
                    value = "当前页",
                    dataType = "Integer",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "pageSize",
                    value = "每页条数",
                    dataType = "Integer",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "sysType",
                    value = "系统类型 01-旗舰店、02-微警务、99-其他",
                    dataType = "String",
                    required = true,
                    paramType = "query")
    })
    @ResponseBody
    public String getApproves(@RequestParam(value = "channel", required = true) Integer channel,
                              @RequestParam(value = "pageNo", required = true) Integer pageNo,
                              @RequestParam(value = "pageSize", required = true) Integer pageSize,
                              @RequestParam(value = "sysType", required = true) String sysType){
        Pageable page = new Pageable();
        page.setPage(pageNo);
        page.setSize(pageSize);
        String result = applicationService.getApplicationList(channel,sysType, page);
        return result;
    }

    /**
     * 查询收藏列表
     * @param channel
     * @param page
     * @return
     */
    @RequestMapping(value = "getCustomize", method = RequestMethod.GET)
    @ApiOperation(value = "query", httpMethod = "GET", notes = "查询用户应用收藏列表", produces = "application/json", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "channel",
                    value = "渠道 01-PC端、02-微信端",
                    dataType = "Integer",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "pageNo",
                    value = "当前页",
                    dataType = "Integer",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "pageSize",
                    value = "每页条数",
                    dataType = "Integer",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "sysType",
                    value = "系统类型 01-旗舰店、02-微警务、99-其他",
                    dataType = "String",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "idcardNo",
                    value = "用户身份证号码",
                    dataType = "String",
                    required = true,
                    paramType = "query")
    })
    @ResponseBody
    public String getCustomize(@RequestParam(value = "channel", required = true) Integer channel,
                               @RequestParam(value = "sysType", required = true) String sysType,
                               @RequestParam(value = "idcardNo", required = true) String idcardNo,
                               @RequestParam(value = "pageNo", required = true) Integer pageNo,
                               @RequestParam(value = "pageSize", required = true) Integer pageSize){
        Pageable page = new Pageable();
        page.setPage(pageNo);
        page.setSize(pageSize);
        String result = applicationService.getCustomizeList(channel,sysType, idcardNo, page);
        return result;
    }

    /**
     * 添加收藏列表
     * @param channel
     * @param page
     * @return
     */
    @RequestMapping(value = "addCustomize", method = RequestMethod.POST)
    @ApiOperation(value = "insert", httpMethod = "POST", notes = "添加应用收藏", produces = "application/json", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "channel",
                    value = "渠道 01-PC端、02-微信端",
                    dataType = "Integer",
                    required = true,
                    paramType = "insert"),
            @ApiImplicitParam(name = "appId",
                    value = "应用ID",
                    dataType = "String",
                    required = true,
                    paramType = "insert"),
            @ApiImplicitParam(name = "idcardNo",
                    value = "用户身份证号码",
                    dataType = "String",
                    required = true,
                    paramType = "insert")
    })
    @ResponseBody
    public String addCustomize(@RequestParam(value = "channel", required = true) Integer channel,
                               @RequestParam(value = "idcardNo", required = true) String idcardNo,
                               @RequestParam(value = "appId", required = true) String appId){
        String result = applicationService.addCustomize(channel, idcardNo, appId);
        return result;
    }

    /**
     * 应用检索
     * @param channel
     * @return
     */
    @RequestMapping(value = "queryApplication", method = RequestMethod.GET)
    @ApiOperation(value = "query", httpMethod = "GET", notes = "应用检索", produces = "application/json", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "channel",
                    value = "渠道 01-PC端、02-微信端",
                    dataType = "Integer",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "pageNo",
                    value = "当前页",
                    dataType = "Integer",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "pageSize",
                    value = "每页条数",
                    dataType = "Integer",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "sysType",
                    value = "系统类型 01-旗舰店、02-微警务、99-其他",
                    dataType = "String",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "keyWord",
                    value = "关键字内容",
                    dataType = "String",
                    required = false,
                    paramType = "query"),
            @ApiImplicitParam(name = "ywType",
                    value = "业务类型",
                    dataType = "String",
                    required = false,
                    paramType = "query"),
            @ApiImplicitParam(name = "xzType",
                    value = "行政类型",
                    dataType = "String",
                    required = false,
                    paramType = "query"),
            @ApiImplicitParam(name = "blType",
                    value = "办理类型",
                    dataType = "String",
                    required = false,
                    paramType = "query"),
            @ApiImplicitParam(name = "serverType",
                    value = "服务类型",
                    dataType = "String",
                    required = false,
                    paramType = "query"),
            @ApiImplicitParam(name = "areaNo",
                    value = "行政区编码",
                    dataType = "String",
                    required = false,
                    paramType = "query")
    })
    @ResponseBody
    public String queryApplication(@RequestParam(value = "channel", required = true) Integer channel,
                                   @RequestParam(value = "pageNo", required = true) Integer pageNo,
                                   @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                   @RequestParam(value = "sysType", required = true) String sysType,
                                   @RequestParam(value = "keyWord", required = false) String keyWord,
                                   @RequestParam(value = "ywType", required = false) String ywType,
                                   @RequestParam(value = "xzType", required = false) String xzType,
                                   @RequestParam(value = "blType", required = false) String blType,
                                   @RequestParam(value = "serverType", required = false) String serverType,
                                   @RequestParam(value = "areaNo", required = false) String areaNo){
        Pageable page = new Pageable();
        page.setPage(pageNo);
        page.setSize(pageSize);
        String result = applicationService.queryApplications(channel, page, sysType, keyWord, ywType, xzType, blType, serverType, areaNo);
        return result;
    }
}
