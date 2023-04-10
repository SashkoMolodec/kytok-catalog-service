package com.kytokvinily.vinyls.exception;

import com.kytokvinily.vinyls.entity.keys.VinylId;

public class VinylNotFoundException extends RuntimeException {

    public VinylNotFoundException(VinylId vinylId) {
        super("The vinyl with title " + vinylId.getTitle() + " and publish year " + vinylId.getYear() + " was not found.");

    }
}
