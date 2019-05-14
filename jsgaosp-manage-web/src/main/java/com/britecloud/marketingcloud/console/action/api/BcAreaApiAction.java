package com.britecloud.marketingcloud.console.action.api;

import com.britecloud.marketingcloud.service.BcAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/area")
@Api(value = "BcAreaApiAction", tags = "查询行政区", description = "查询行政区")
public class BcAreaApiAction {
    @Autowired
    private BcAreaService bcAreaService;

    @RequestMapping(value = "getAreaList", method = RequestMethod.GET)
    @ApiOperation(value = "query", httpMethod = "GET", notes = "行政区", produces = "application/json", response = String.class)
    @ApiImplicitParam(name = "channel",
            value = "渠道 01-PC端、02-微信端",
            dataType = "Integer",
            required = true,
            paramType = "query",allowableValues = "01,02", allowMultiple = true)
    @ResponseBody
    public String getAreaList(@RequestParam(value = "channel", required = true) Integer channel){
        String result = bcAreaService.getAreaList();
        return result;
    }
}
