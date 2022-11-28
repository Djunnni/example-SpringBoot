package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    private final Logger logger = LoggerFactory.getLogger(GetController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello() {
        logger.info("hello 메서드가 호출됨.");
        return "hello";
    }
    @GetMapping(value ="/name")
    public String getName() {
        logger.info("getName 메서드가 호출됨.");
        return "daniel";
    }
    /* PathVariable에 변수명을 명시하지 않는다면 전달받는 path와 이름이 동일해야 함. */
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        logger.info("variable : {} 이 들어옴", variable);
        return variable;
    }

    @GetMapping("/variable2/{variable}")
    public String getVariable2(@PathVariable(value = "variable") String var) {
        return var;
    }
    // http://localhost:8080/api/v1/get-api/request1?name=daniel&email=djunnni@gmail.com&organization=SSAFY
    @ApiOperation(value = "GET 메서드 예제",notes = "@RequestParam을 활용한 Get Method")
    @GetMapping("/request1")
    public String getRequestParam1(
            @ApiParam(value="이름", required=true) @RequestParam String name,
            @ApiParam(value="이메일", required=true) @RequestParam String email,
            @ApiParam(value="조직", required=true) @RequestParam String organization
    ) {
        return name + " " + email + " " + organization;
    }

    // http://localhost:8080/api/v1/get-api/request2?name=daniel&email=djunnni@gmail.com&organization=SSAFY
    @GetMapping("/request2")
    public String getRequestParam2(
            @RequestParam Map<String, String> param
    ) {
        StringBuilder sb = new StringBuilder();
        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }
    @GetMapping("/request3")
    public String getRequestParam3(MemberDto member) {
        return member.toString();
    }
}
