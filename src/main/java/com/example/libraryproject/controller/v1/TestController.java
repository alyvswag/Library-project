package com.example.libraryproject.controller.v1;


import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.service.report.ReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TestController {
    @GetMapping("/test")
    public BaseResponse<Void> testNoAuth() {
        System.out.println("Salam dunya not auth");
        return BaseResponse.success();
    }

    @GetMapping("/test1")
    public BaseResponse<String> testAuth() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return BaseResponse.success(userDetails.getUsername());
    }
}
