package com.pkelbas.forumservice;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test-controller")
public class TestController {

  @GetMapping(path = "/test")
  public ResponseEntity<String> test(){
    return ResponseEntity.ok("success!");
  }
}