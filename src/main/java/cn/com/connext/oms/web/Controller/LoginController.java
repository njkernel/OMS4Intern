package cn.com.connext.oms.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>Title: LoginController</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/8 14:27
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "pages/login/loading";
    }

}
