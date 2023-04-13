package com.kytokvinily.vinyls.domain;

public class VinylAlreadyExistsException extends RuntimeException {

    public VinylAlreadyExistsException(String title, int year) {
        super("A vinyl with such " + title + " and publish year " + year + " already exists.");
    }
}
