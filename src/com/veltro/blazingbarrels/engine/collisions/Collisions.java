package com.veltro.blazingbarrels.engine.collisions;

import java.util.ArrayList;

/**
 * This class is entirely static, and deals with collisions of objects
 * @author deager4
 *
 */
public enum Collisions 
{
	
	Collisions()
	{
		
	}

	/**
	 * Determines if a coordinate already contains an object by searching through all occupied coordinates on the same y plane
	 * @param x x-Coordinate
	 * @param y y-Coordinate
	 * @param z z-Coordinate
	 * @param occupiedList an ArrayList<double[3]> of all the occupied coordinates on that y plane
	 * @return isOccupied If true, the area that the object is attempting to move to is occupied by another object
	 * @author deager4
	 */
	public boolean isCoordOccupied(double x, double y, double z, ArrayList<double[]> occupiedList)
	{
		boolean isOccupied;
		for(int count = 0; count < occupiedList.size(); count ++)
		{
			double[] list = {occupiedList.get(count)[1], occupiedList.get(count)[2], occupiedList.get(count)[3]};
			if((list[1] == x) && (list[2] == y) && (list[3] == z))
			{
				isOccupied = true;
				return isOccupied;
			}
		}
		isOccupied = false;
		return isOccupied;
	}
	
	
}
