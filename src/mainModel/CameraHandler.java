package mainModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Yonas Halefom on 1/19/2017.
 */
public class CameraHandler {
    private float distanceFromPlayer = 50;
    private float angleAroundPlayer = 180;

    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;

    private MovableObject movableObject;

    public CameraHandler(MovableObject movableObject) {
        this.movableObject = movableObject;
    }

    public void move() {
        if (Keyboard.isKeyDown(Keyboard.KEY_I)) {
            position.z -= 0.075f;
//            position.z -= 5.075f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_K)) {
            position.z += 0.075f;
//            position.z += 5.075f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
            position.x += 0.075f;
//            position.x += 5.075f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_J)) {
            position.x -= 0.075f;
//            position.x -= 5.075f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            position.y += 0.075f;
//            position.y += 5.075f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            position.y -= 0.075f;
//            position.y -= 5.075f;
        }
        calculateZoom();
        calculatePitch();
        calculateAngleAroundPlayer();
        float horizontalDistance = calculateHorizontalDistance();
        float verticalDistance = calculateVerticalDistance();
        calculateCameraPosition(horizontalDistance, verticalDistance);
        this.yaw = 180 - (movableObject.getRotY() + angleAroundPlayer);
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }

    private void calculateCameraPosition(float horizontalDistance, float verticalDistance) {
        float theta = movableObject.getRotY() + angleAroundPlayer;
        float offsetX = (float) (horizontalDistance * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float) (horizontalDistance * Math.cos(Math.toRadians(theta)));
        position.x = movableObject.getPosition().x - offsetX;
        position.z = movableObject.getPosition().z - offsetZ;
        position.y = movableObject.getPosition().y + verticalDistance;
    }

    private float calculateHorizontalDistance() {
        return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
    }

    private float calculateVerticalDistance() {
        return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
    }

    private void calculateZoom() {
        float zoomLevel = Mouse.getDWheel() * 0.1f;
        distanceFromPlayer -= zoomLevel;
    }

    private void calculatePitch() {
        if (Mouse.isButtonDown(1)) {

        }
    }

    private void calculateAngleAroundPlayer() {
        if (Mouse.isButtonDown(0)) {
            float angleChange = Mouse.getDX() * 0.3f;
            angleAroundPlayer -= angleChange;
            float pitchChange = Mouse.getDY() * 0.1f;
            pitch -= pitchChange;
        }
    }
}
