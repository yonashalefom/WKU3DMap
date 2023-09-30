package mainModel;

import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import renderer.DisplayHandler;

/**
 * Created by Yonas Halefom on 1/22/2017.
 */
public class MovableObject extends TransformableModel {
    private static float RUN_SPEED = 40;
    private static final float TURN_SPEED = 160;

    private float currentSpped = 0;
    private float currentTurnSpped = 0;

    public MovableObject(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }

    public void move() {
        checkInputs();
        super.increaseRotation(0, currentTurnSpped * DisplayHandler.getFrameTimeSeconds(), 0);
        float distance = currentSpped * DisplayHandler.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
        float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
        super.increasePosition(dx, 0, dz);
    }

    public void checkInputs() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            this.currentSpped = -RUN_SPEED;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            this.currentSpped = RUN_SPEED;
        } else {
            this.currentSpped = 0;
        }


        if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
            RUN_SPEED = 10;
            System.out.println("Current Spped: " + RUN_SPEED + " m/s");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
            RUN_SPEED = 20;
            System.out.println("Current Spped: " + RUN_SPEED + " m/s");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
            RUN_SPEED = 30;
            System.out.println("Current Spped: " + RUN_SPEED + " m/s");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
            RUN_SPEED = 40;
            System.out.println("Current Spped: " + RUN_SPEED + " m/s");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_5)) {
            RUN_SPEED = 50;
            System.out.println("Current Spped: " + RUN_SPEED + " m/s");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_6)) {
            RUN_SPEED = 60;
            System.out.println("Current Spped: " + RUN_SPEED + " m/s");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_7)) {
            RUN_SPEED = 70;
            System.out.println("Current Spped: " + RUN_SPEED + " m/s");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_8)) {
            RUN_SPEED = 80;
            System.out.println("Current Spped: " + RUN_SPEED + " m/s");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_9)) {
            RUN_SPEED = 90;
            System.out.println("Current Spped: " + RUN_SPEED + " m/s");
        }else if (Keyboard.isKeyDown(Keyboard.KEY_0)) {
            RUN_SPEED = 150;
            System.out.println("Current Spped: " + RUN_SPEED + " m/s");
        }


        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            this.currentTurnSpped = -TURN_SPEED;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            this.currentTurnSpped = TURN_SPEED;
        } else {
            this.currentTurnSpped = 0;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            super.increasePosition(0, 0.25f, 0);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            super.increasePosition(0, -0.25f, 0);
        }
    }
}
