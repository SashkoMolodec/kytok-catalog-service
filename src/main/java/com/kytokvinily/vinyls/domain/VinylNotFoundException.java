package com.kytokvinily.vinyls.domain;

public class VinylNotFoundException extends RuntimeException {

    public VinylNotFoundException(String title, int year) {
        super("The vinyl with title " + title + " and publish year " + year + " was not found.");

    }
}
