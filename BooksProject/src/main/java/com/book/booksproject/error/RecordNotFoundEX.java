package com.book.booksproject.error;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



public class RecordNotFoundEX extends RuntimeException{
    public RecordNotFoundEX() {
        super();
        // TODO Auto-generated constructor stub
    }

    public RecordNotFoundEX(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }
}
