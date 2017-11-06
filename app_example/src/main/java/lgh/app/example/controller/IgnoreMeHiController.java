package lgh.app.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lgh.app.example.dto.ResponseResult;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ResponseBody
@RequestMapping("/ignoreme")
@Api(value = "测试API2", tags = "test")
@ApiIgnore
public class IgnoreMeHiController {
	@ApiOperation(value = "我的测试api", notes = "第一个测试api", httpMethod = "GET", produces = "application/json")
	@ApiResponse(code = 200, message = "success", response = ResponseResult.class)
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseResult hello(
			@ApiParam(value = "用户名称", required = true) @RequestParam(name = "myname", defaultValue = "MyDefaultName") String name) {
		return new ResponseResult(System.currentTimeMillis(), name);
	}
}
