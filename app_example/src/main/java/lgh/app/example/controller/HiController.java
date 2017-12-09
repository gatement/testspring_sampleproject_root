package lgh.app.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lgh.app.example.dto.ResponseResult;
import lgh.app.example.dto.SampleRequestBody;

@RestController
@RequestMapping("/hi")
@Api(value = "测试API2", tags = "test")
public class HiController {
	@ApiOperation(value = "我的测试api", notes = "第一个测试api", produces = "application/json")
	@ApiResponse(code = 200, message = "success", response = ResponseResult.class)
	@PostMapping("/test")
	public ResponseResult test1(@RequestHeader("Authorization") String auth, @RequestBody SampleRequestBody requestBody) {
		return new ResponseResult(System.currentTimeMillis(), requestBody.getName() + "(" + auth + ")");
	}
}
