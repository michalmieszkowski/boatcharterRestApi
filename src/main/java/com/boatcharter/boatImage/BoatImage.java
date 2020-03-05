package com.boatcharter.boatImage;

import lombok.Data;

@Data
public class BoatImage {

    private String imageName;

    private byte[] imageByte;

    public BoatImage(String imageName, byte[] imageByte) {
        this.imageName = imageName;
        this.imageByte = imageByte;
    }
}
