package com.book.booksproject.controller;

import com.book.booksproject.error.FileStorageEX;
import com.book.booksproject.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/file")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileService fileService;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam Long id, @RequestParam MultipartFile file,@RequestParam boolean isAuthorImage){

        String filePath=fileService.storeImage(file,id,isAuthorImage);

        return ResponseEntity.ok(filePath);
    }
    @ExceptionHandler(FileStorageEX.class)
    public ResponseEntity<FileStorageEX> handleStorageFileNotFound(FileStorageEX exc) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc);
    }
}
