package cycling;

public class RiderPoints {
	private int riderId;
	private int totalPoints;

	public RiderPoints(int riderId, int totalPoints) {
		this.riderId = riderId;
		this.totalPoints = totalPoints;
	}

	public int getRiderId() {
		return riderId;
	}

	public int getTotalPoints() {
		return totalPoints;
	}
	

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

}
