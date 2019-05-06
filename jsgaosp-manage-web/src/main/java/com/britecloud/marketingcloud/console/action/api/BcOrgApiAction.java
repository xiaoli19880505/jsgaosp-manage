package com.britecloud.marketingcloud.console.action.api;

import com.britecloud.marketingcloud.service.BcOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/org")
@Api(value = "OrgAction", tags = "查询组织信息API", description = "查询行政区属信息")
public class BcOrgApiAction {

    @Autowired
    private BcOrgService bcOrgService;

    @RequestMapping(value = "getOrgList", method = RequestMethod.GET)
    @ApiOperation(value = "query", httpMethod = "GET", notes = "组织", produces = "application/json", response = String.class)
    @ApiImplicitParam(name = "channel",
            value = "渠道 01-PC端、02-微信端",
            dataType = "Integer",
            required = true,
            paramType = "query")
    @ResponseBody
    public String getOrgList(@RequestParam(value = "channel", required = true) Integer channel){
        String result = bcOrgService.getOrgAreaNameList();
        return result;
    }
}
