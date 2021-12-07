package main.java.mygame;

import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.shape.Box;

/**
 *
 * @author balanton
 */
public class Physics {
    private static Vector3f intersectPoint(Vector3f rayVector, Vector3f rayPoint, Vector3f planeNormal, Vector3f planePoint) {
        Vector3f diff = rayPoint.subtract(planePoint);
        float prod1 = diff.dot(planeNormal);
        float prod2 = rayVector.dot(planeNormal);
        float prod3 = prod1 / prod2;
        return rayPoint.subtract(rayVector.mult(prod3));
    }
      
    public static Vector3f calcPosition(Camera cam, Box plane) {
        Vector3f direction = cam.getDirection();
        Vector3f position = cam.getLocation();
        
        Vector3f norm = new Vector3f(0, 0, 1);

        return intersectPoint(direction, position, norm, plane.getCenter());
    }
}
