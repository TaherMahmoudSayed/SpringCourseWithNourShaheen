package com.book.booksproject.service;

import com.book.booksproject.config.StorageProperties;
import com.book.booksproject.error.FileStorageEX;
import com.book.booksproject.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class FileService {

    private final Path baseFileLocation;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public FileService(StorageProperties storageProperties, AuthorService authorService, BookService bookService, Mapper mapper){
        this.baseFileLocation=storageProperties.getBaseFileLocation();
        this.authorService = authorService;
        this.bookService = bookService;

    }

    public String storeImage(MultipartFile file,Long id,boolean isAuthorImage){
        try {
            if (file==null||file.isEmpty()){
                throw new FileStorageEX("Failed to store empty file.");
            }
            Path destinationPath=this.baseFileLocation
                    .resolve(Paths.get(id+file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            log.info("destination file path is :"+destinationPath);
            if (!destinationPath.getParent().equals(this.baseFileLocation.toAbsolutePath())) {
                // This is a security check
                throw new FileStorageEX(
                        "Cannot store file outside current directory.");
            }
            try(InputStream inputStream= file.getInputStream()) {

                Files.copy(inputStream,destinationPath, StandardCopyOption.REPLACE_EXISTING);
            }
            return storeImagePath(destinationPath.toString() , isAuthorImage, id);

        }catch (Exception exception){
            throw new FileStorageEX(exception.getMessage());
        }
    }

    private String storeImagePath(String imagePath,boolean isAuthorImage,Long id) {
        try {
            if (isAuthorImage) {
                var author = authorService.getAuthor(id);
                author.setImagePath(imagePath);
                authorService.updateAuthor(author);

            } else {
                var book = bookService.getBook(id);
                book.setBookImage(imagePath);
                bookService.update(Mapper.BookDtoToBook(book));
            }
            return imagePath;
        } catch (Exception exception) {
            throw new FileStorageEX(exception.getMessage());
        }
    }
}
