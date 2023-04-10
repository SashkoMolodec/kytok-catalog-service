package com.kytokvinily.vinyls.exception;

import com.kytokvinily.vinyls.entity.keys.VinylId;

public class VinylAlreadyExistsException extends RuntimeException {

    public VinylAlreadyExistsException(VinylId vinylId) {
        super("A vinyl with such " + vinylId.getTitle() + " and publish year " + vinylId.getYear() + " already exists.");
    }
}
