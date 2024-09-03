package com.example.libraryproject.controller.v1;


import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.service.report.ReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TestController {
     @GetMapping("/test")
    public BaseResponse<Void> test() {
        return BaseResponse.created();
    }
}
