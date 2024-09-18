package cycling;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.ArrayList; 
import java.util.HashMap;// Import ArrayList from java.util package

public class Stage {
	private int id;
	private int raceId;
	private String name;
	private String description;
	private double length;
	private LocalDateTime startTime;
	private StageType type;
	private StageState state;
	private List<Checkpoint> checkpoints;
	private Map<Integer, Result> results;

	public Stage(int id, int raceId, String name, String description, double length, LocalDateTime startTime,
			StageType type) {
		this.id = id;
		this.raceId = raceId;
		this.name = name;
		this.description = description;
		this.length = length;
		this.startTime = startTime;
		this.type = type;
		this.checkpoints = new ArrayList<>();
		this.results = new HashMap<>();
		
	}

	// Getters and setters for attributes

	public Stage(int stageId, String string, StageType flat, double d) {
		this.id=stageId;
		this.name = string;
		this.type = flat;
		this.length = d;
		
		
	}

	public Stage(int i, String string, StageState waitingForResults) {
		this.id=i;
		this.name = string;
		this.state=waitingForResults;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRaceId() {
		return raceId;
	}

	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public StageType getType() {
		return type;
	}

	public StageState getState() {
		return state;
	}

	public void setState(StageState state) {
		this.state = state;
	}

	public void setType(StageType type) {
		this.type = type;
	}

	public static double calculateTotalLengthForRace(int raceId, List<Stage> stages) {
		double totalLength = 0;
		for (Stage stage : stages) {
			if (stage.getRaceId() == raceId) {
				totalLength += stage.getLength();
			}
		}
		return totalLength;
	}

	public static int getNumberOfStagesForRaceId(int raceId, List<Stage> stages) {
		int count = 0;
		for (Stage stage : stages) {
			if (stage.getRaceId() == raceId) {
				count++;
			}
		}
		return count;
	}

	public void addCheckpoint(Checkpoint checkpoint) {
		checkpoints.add(checkpoint);
	}

	public void setCheckpoints(List<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}

	public List<Checkpoint> getCheckpoints() {
		return checkpoints;
	}

	public boolean hasResultForRider(int riderId) {
		return results.containsKey(riderId);
	}

	public void addResult(Result result) {
		results.put(result.getRiderId(), result);
	}
	public Map<Integer, Result> getResults() {
        return results;
    }
	public void printCheckpoints() {
        System.out.println("Checkpoints for Stage " + id + ":");
        for (Checkpoint checkpoint : checkpoints) {
            System.out.println(checkpoint);
        }
    }
}
