package cycling;

public class RiderMountainPoints {
	private int riderId;
	private int mountainPoints;

	public RiderMountainPoints(int riderId, int mountainPoints) {
		this.riderId = riderId;
		this.mountainPoints = mountainPoints;
	}

	public int getRiderId() {
		return riderId;
	}

	public int getMountainPoints() {
		return mountainPoints;
	}
}