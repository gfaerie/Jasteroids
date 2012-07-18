package se.faerie.jasteroids.physics;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import se.faerie.jasteroids.graphics.model.GameObject;
import se.faerie.jasteroids.graphics.model.GameObjectType;

public class JavaStandardApiCollisionHandler extends AbstractCollisionHandler {

	public JavaStandardApiCollisionHandler(int numberOfXCells,
			int numberOfYCells) {
		super(numberOfXCells, numberOfYCells);
	}

	@Override
	public boolean isCollision(GameObject objectOne, GameObject objectTwo) {

		// no friendly collisions
		if (objectOne.getData().getAlligiance() == objectTwo.getData().getAlligiance()) {
			return false;
		}

		// no collisions between weapons
		if (objectOne.getData().getType() == GameObjectType.WEAPON
				&& objectTwo.getData().getType() == GameObjectType.WEAPON) {
			return false;
		}
		

		Shape one = getTransformedShape(objectOne);
		Shape two = getTransformedShape(objectTwo);
		
		Rectangle boundingOne = one.getBounds();
		Rectangle boundingTwo = two.getBounds();

		// check bounding boxes first
		if (boundingOne.intersects(boundingTwo)) {
			// now do proper check
			Area areaOne = new Area(one);
			areaOne.intersect(new Area(two));
			return !areaOne.isEmpty();
		} else {
			return false;
		}
	}

	private Shape getTransformedShape(GameObject gameObject) {
		AffineTransform rotateTransform = AffineTransform
				.getRotateInstance(gameObject.getPositionData().getAngle());
		Shape roatateShape = rotateTransform.createTransformedShape(gameObject
				.getData().getGraphics());
		AffineTransform translateTransform = AffineTransform
				.getTranslateInstance(gameObject.getPositionData()
						.getXPosition(), gameObject.getPositionData()
						.getYPosition());
		return translateTransform.createTransformedShape(roatateShape);
	}

}
