package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
@RequestMapping(value = "index")  
public class IndexController {  
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param model
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "index.ftl")
    public String indexFTL(Model model){  
        model.addAttribute("hello", "你好");  
        return "index.ftl";
    }  
    @RequestMapping(value = "index.do")  
    public String indexJSP(Model model){  
        model.addAttribute("hello", "你好");  
        return "index.jsp";
    }  
    @RequestMapping(value = "index.vm")
    public String indexVM(Model model){  
        model.addAttribute("hello", "你好");  
        return "index.vm";
    }  
}  