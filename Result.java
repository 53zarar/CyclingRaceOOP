package cycling;

import java.time.LocalTime;
import java.util.Arrays;

public class Result {
	private int riderId;
	private LocalTime[] checkpointTimes;
	private LocalTime elapsedTime;

	public Result(int riderId, LocalTime[] checkpointTimes) {
		this.riderId = riderId;
		this.checkpointTimes = checkpointTimes;
		this.elapsedTime = calculateElapsedTime();
	}

	public int getRiderId() {
		return riderId;
	}

	public LocalTime[] getCheckpointTimes() {
		return checkpointTimes;
	}

	public LocalTime getElapsedTime() {
		return elapsedTime;
	}

	private LocalTime calculateElapsedTime() {
		if (checkpointTimes.length >= 2) {
			long secondsElapsed = checkpointTimes[checkpointTimes.length - 1].toSecondOfDay()
					- checkpointTimes[0].toSecondOfDay();
			return LocalTime.ofSecondOfDay(secondsElapsed);
		} else {
			return null; // Return null if there are not enough checkpoint times for calculation
		}
	}

	public long getGapWithPreviousRider(int riderId) {
		// Assuming checkpointTimes are sorted in ascending order
		int index = Arrays.binarySearch(checkpointTimes, riderId);
		if (index > 0) {
			long gapInSeconds = checkpointTimes[index].toSecondOfDay() - checkpointTimes[index - 1].toSecondOfDay();
			return Math.abs(gapInSeconds);
		} else {
			return -1; // Return -1 if riderId not found or if the rider is the first in
						// checkpointTimes
		}
	}

	// Define the getAdjustedElapsedTime method
	public LocalTime getAdjustedElapsedTime() {
		if (checkpointTimes.length < 2) {
			// No checkpoints or only one checkpoint, return zero adjusted time
			return LocalTime.of(0, 0, 0);
		}

		// Calculate the difference between the finish time and start time
		LocalTime startTime = checkpointTimes[0];
		LocalTime finishTime = checkpointTimes[checkpointTimes.length - 1];
		long elapsedSeconds = finishTime.toSecondOfDay() - startTime.toSecondOfDay();

		// Check for adjustments based on your business logic
		// For example, adjust the time if finish time is less than one second slower
		// than previous rider

		return LocalTime.ofSecondOfDay(elapsedSeconds);
	}

	public long getElapsedSeconds() {
		if (checkpointTimes.length >= 2) {
			long secondsElapsed = checkpointTimes[checkpointTimes.length - 1].toSecondOfDay()
					- checkpointTimes[0].toSecondOfDay();
			return secondsElapsed;
		} else {
			return -1; // Return -1 if there are not enough checkpoint times for calculation
		}
	}

}
