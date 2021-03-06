package com.yourheadline;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerRouter {
    @GetMapping("/")
    public String getIndexPage()
    {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage()
    {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage()
    {
        return "register";
    }

    @GetMapping("/article")
    public String getArticlePage()
    {
        return "article";
    }

    @GetMapping("/userinfo")
    public String getUserinfoPage()
    {
        return "userinfo";
    }

    @GetMapping("/authorinfo")
    public String getAuthorinfoPage()
    {
        return "authorinfo";
    }

    @GetMapping("/register-author")
    public String getRegisterAuthorPage() {return "register-author"; }

    @GetMapping("/article-editor")
    public String getNewArticleEditor() {return "article-editor"; }

    @GetMapping("/author-confirm")
    public String getAuthorApplyPage() {return "apply-files"; }

    @GetMapping("/all-apply")
    public String getAllApplyPage() {return "all-apply"; }

}
