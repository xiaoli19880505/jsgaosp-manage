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
@Api(value = "BcAppApiAction", tags = "查询应用列表API", description = "查询应用列表")
public class BcAppApiAction {

    @Autowired
    private ApplicationService applicationService;

    /**
     * 查询某一组织及其下属部门的应用
     * @param channel
     * @param page
     * @return
     */
    @RequestMapping(value = "getApproves", method = RequestMethod.GET)
    @ApiOperation(value = "query", httpMethod = "GET", notes = "省级页应用列表", produces = "application/json", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "channel",
                    value = "渠道 01-PC端、02-微信端",
                    dataType = "Integer",
                    required = true,
                    paramType = "query",allowableValues = "01,02", allowMultiple = true),
            @ApiImplicitParam(name = "page",
                    value = "当前页、每页条数",
                    dataType = "Pageable",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "sysType",
                    value = "系统类型 01-旗舰店、02-微警务、99-其他",
                    dataType = "String",
                    required = true,
                    paramType = "query",allowableValues = "01,02,99", allowMultiple = true)
    })
    @ResponseBody
    public String getOrgList(@RequestParam(value = "channel", required = true) Integer channel,
                             @RequestParam(value = "sysType", required = true) String sysType,
                             @RequestParam(value = "page", required = true) Pageable page){
        String result = applicationService.getApplicationList(channel,sysType, page);
        return result;
    }
}
