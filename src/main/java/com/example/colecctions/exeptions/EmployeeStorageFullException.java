package com.example.colecctions.exeptions;

public class EmployeeStorageFullException extends RuntimeException {
    public EmployeeStorageFullException(String message) {
        super(message);
    }
}
