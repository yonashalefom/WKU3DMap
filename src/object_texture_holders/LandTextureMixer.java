package object_texture_holders;

/**
 * Created by Yonas Halefom on 1/22/2017.
 */
public class LandTextureMixer {
    private LandTexture backgroundTexture;
    private LandTexture redTexture;
    private LandTexture greenTexture;
    private LandTexture blueTexture;

    public LandTextureMixer(LandTexture backgroundTexture, LandTexture redTexture, LandTexture greenTexture, LandTexture blueTexture) {
        this.backgroundTexture = backgroundTexture;
        this.redTexture = redTexture;
        this.greenTexture = greenTexture;
        this.blueTexture = blueTexture;
    }

    public LandTexture getBackgroundTexture() {
        return backgroundTexture;
    }

    public LandTexture getRedTexture() {
        return redTexture;
    }

    public LandTexture getGreenTexture() {
        return greenTexture;
    }

    public LandTexture getBlueTexture() {
        return blueTexture;
    }
}
