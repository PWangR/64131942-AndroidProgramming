package com.example.ex_recycleview;

public class LandScape {
    String landIMG;
    String caption;

    public LandScape(String caption, String landIMG) {
        this.caption = caption;
        this.landIMG = landIMG;
    }

    public String getLandIMG() {
        return landIMG;
    }

    public void setLandIMG(String landIMG) {
        this.landIMG = landIMG;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
