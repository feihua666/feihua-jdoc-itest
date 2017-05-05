package com.feihua.doc.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring接口
 * @author lianghao
 *
 * 2017年3月24日
 */

@Controller
@RestController
@RequestMapping("/spring")
public class SpringController {


    /**
     * 测试接口
     * @param userName|昵称|必填
     * @param id|id|必填|string
     * @requParam username|用户名|选填
     * @respBody dto.ItestActionDto
     * @respDataType array
     */
    @RequestMapping("/test5")
    public void test5(String userName,Integer id) {

    }

    /**
     * 又一个测试接口
     * @param userName 昵称
     * @param id id
     * @param username 用户名 必填
     */
    @RequestMapping("/test5")
    public void test5(String userName,Integer id,String username) {

    }
}
