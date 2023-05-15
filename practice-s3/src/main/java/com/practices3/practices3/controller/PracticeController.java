package com.practices3.practices3.controller;


import com.practices3.practices3.service.ReadDataService;
import com.practices3.practices3.service.RenameObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PracticeController {

    @Autowired
    private ReadDataService read;

    @Autowired
    private RenameObjectService rename;

    @GetMapping("/test")
    public ResponseEntity practice() {
        return ResponseEntity.ok(read.fetchFile());
    }

    @GetMapping("/rename")
    public ResponseEntity practiceRename(@RequestParam String key) {
        // key -> folder/folder/../filename e.g key = "v1/short/images/some.jpg";
        rename.renameObject(key);
        return ResponseEntity.ok("renaming is done");
    }

}
