package com.britecloud.marketingcloud.console.action.api;

import com.britecloud.marketingcloud.service.BcOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/org")
@Api(value = "OrgAction", tags = "查询微警务集群API", description = "查询微警务集群")
public class BcOrgApiAction {

    @Autowired
    private BcOrgService bcOrgService;

    @RequestMapping(value = "getOrgList", method = RequestMethod.GET)
    @ApiOperation(value = "query", httpMethod = "GET", notes = "查询某一组织及其下属组织微警务集群", produces = "application/json", response = String.class)
    @ApiImplicitParams({
          @ApiImplicitParam(name = "channel",
                 value = "渠道 01-PC端、02-微信端",
                 dataType = "Integer",
                 required = true,
                 paramType = "query"),

          @ApiImplicitParam(name = "orgNo",
                    value = "组织编码，非必填，不填写默认是省公安",
                    dataType = "String",
                    required = false,
                    paramType = "query")
    })
    @ResponseBody
    public String getOrgList(@RequestParam(value = "channel", required = true) Integer channel,
                             @RequestParam(value = "orgNo", required = false) String orgNo){
        String result = bcOrgService.getOrgAreaNameList(channel, orgNo);
        return result;
    }
}
