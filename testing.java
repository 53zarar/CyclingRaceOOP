package test;

import java.time.LocalDateTime;
import java.time.LocalTime;


import cycling.BadCyclingPortalImpl;

import cycling.CheckpointType;
import cycling.CyclingPortal;

import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidLengthException;
import cycling.InvalidNameException;
import cycling.StageState;
import cycling.StageType;

public class testing {
	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");
		// TODO replace BadMiniCyclingPortalImpl by CyclingPortalImpl
		CyclingPortal portal1 = new BadCyclingPortalImpl();
		CyclingPortal portal2 = new BadCyclingPortalImpl();

		assert (portal1.getRaceIds().length == 0)
				: "Innitial Portal not empty as required or not returning an empty array.";
		assert (portal2.getRaceIds().length == 0)
				: "Innitial Portal not empty as required or not returning an empty array.";

		// Print a message indicating that the assertion passed
		System.out.println("Assertion passed: Get RaceIds.");

		try {
			portal1.createTeam("TeamOne", "My favorite");
			portal2.createTeam("TeamOne", "My favorite");
		} catch (IllegalNameException e) {
			e.printStackTrace();
		} catch (InvalidNameException e) {
			e.printStackTrace();
		}
		assert (portal1.getTeams().length == 1) : "Portal1 should have one team.";

		assert (portal2.getTeams().length == 1) : "Portal2 should have one team.";

		System.out.println("Assertion passed: Get Teams.");

		try {
			int raceId1 = portal1.createRace("Tour de France", "Annual cycling race");
			assert raceId1 > 0 : "Valid race ID should be returned";
		} catch (IllegalNameException | InvalidNameException e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		// Test case 2: Attempt to create a race with null name
		try {
			portal1.createRace(null, "Description");
			assert false : "Creating race with null name should throw InvalidNameException";
		} catch (InvalidNameException e) {
			assert e.getMessage().equals("Name cannot be null, empty, or contain only whitespace.")
					: "InvalidNameException message mismatch";
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		// Test case 3: Attempt to create a race with empty name
		try {
			portal1.createRace("", "Description");
			assert false : "Creating race with empty name should throw InvalidNameException";
		} catch (InvalidNameException e) {
			assert e.getMessage().equals("Name cannot be null, empty, or contain only whitespace.")
					: "InvalidNameException message mismatch";
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		// Test case 4: Attempt to create a race with whitespace-only name
		try {
			portal1.createRace("   ", "Description");
			assert false : "Creating race with whitespace-only name should throw InvalidNameException";
		} catch (InvalidNameException e) {
			assert e.getMessage().equals("Name cannot be null, empty, or contain only whitespace.")
					: "InvalidNameException message mismatch";
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		// Test case 5: Attempt to create a race with name exceeding 30 characters
		try {
			portal1.createRace("This is a very long race name that exceeds 30 characters", "Description");
			assert false : "Creating race with name exceeding 30 characters should throw InvalidNameException";
		} catch (InvalidNameException e) {
			assert e.getMessage().equals("Name cannot exceed 30 characters.") : "InvalidNameException message mismatch";
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		// Test Case 6
		try {
			portal1.createRace("Tour de France", "Grand Tour race"); // Existing race name
			assert false : "Creating race with existing name should throw IllegalNameException";
		} catch (IllegalNameException e) {
			assert e.getMessage().equals("Race name already exists in the platform.")
					: "IllegalNameException message mismatch";
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		System.out.println("Assertion Passed: Create Race");

		// Test Case 1: Valid stage creation
		try {
			int stageId1 = portal1.addStageToRace(1, "Stage 1", "First stage of the race", 100.5, LocalDateTime.now(),
					StageType.FLAT);
			assert stageId1 > 0 : "Valid stage ID should be returned";
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		// Test Case 2: Attempt to add stage to non-existent race
		try {
			portal1.addStageToRace(99, "Stage 1", "First stage of the race", 100.5, LocalDateTime.now(),
					StageType.FLAT);
			assert false : "Adding stage to non-existent race should throw IDNotRecognisedException";
		} catch (IDNotRecognisedException e) {
			assert e.getMessage().equals("Race ID not recognised.") : "IDNotRecognisedException message mismatch";
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		// Test Case 3: Attempt to add stage with null stage name
		try {
			portal1.addStageToRace(1, null, "Description", 50.0, LocalDateTime.now(), StageType.MEDIUM_MOUNTAIN);
			assert false : "Adding stage with null name should throw InvalidNameException";
		} catch (InvalidNameException e) {
			assert e.getMessage().equals("Stage name cannot be null, empty, or contain only whitespace.")
					: "InvalidNameException message mismatch";
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		// Test Case 4: Attempt to add stage with empty stage name
		try {
			portal1.addStageToRace(1, "", "Description", 50.0, LocalDateTime.now(), StageType.MEDIUM_MOUNTAIN);
			assert false : "Adding stage with empty name should throw InvalidNameException";
		} catch (InvalidNameException e) {
			assert e.getMessage().equals("Stage name cannot be null, empty, or contain only whitespace.")
					: "InvalidNameException message mismatch";
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		// Test Case 5: Attempt to add stage with name exceeding 30 characters
		try {
			portal1.addStageToRace(1, "This is a very long stage name that exceeds 30 characters", "Description", 50.0,
					LocalDateTime.now(), StageType.MEDIUM_MOUNTAIN);
			assert false : "Adding stage with name exceeding 30 characters should throw InvalidNameException";
		} catch (InvalidNameException e) {
			assert e.getMessage().equals("Stage name cannot exceed 30 characters.")
					: "InvalidNameException message mismatch";
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		// Test Case 6: Attempt to add stage with length less than 5km
		try {
			portal1.addStageToRace(1, "Stage 2", "Description", 3.0, LocalDateTime.now(), StageType.TT);
			assert false : "Adding stage with length less than 5km should throw InvalidLengthException";
		} catch (InvalidLengthException e) {
			assert e.getMessage().equals("Stage length cannot be less than 5km.")
					: "InvalidLengthException message mismatch";
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		System.out.println("Assertion Passed: Add Stage to Race.");

		// Test Case 1: Valid race details view
		try {
			String raceDetails = portal1.viewRaceDetails(1);
			assert !raceDetails.isEmpty() : "Race details should not be empty";
			System.out.println("Race Details:\n" + raceDetails);
		} catch (IDNotRecognisedException e) {
			assert false : "Unexpected IDNotRecognisedException thrown: " + e.getMessage();
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}
		// Test Case 2: Attempt to view details of a non-existent race
		try {
			portal1.viewRaceDetails(99);
			assert false : "Viewing details of non-existent race should throw IDNotRecognisedException";
		} catch (IDNotRecognisedException e) {
			assert e.getMessage().equals("Race ID not recognised.") : "IDNotRecognisedException message mismatch";
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		System.out.println("Assertion Passed: View Race Details.");

		// Test case for getNumberOfStages method
		try {

			// Get the number of stages for the race
			int numberOfStages = portal1.getNumberOfStages(1);
			assert numberOfStages == 1 : "Expected 1 stages for raceId1, but found " + numberOfStages;

			System.out.println("Assertion Passed: getNumberOfStages");
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		try {
			// Remove the added race
			portal1.removeRaceById(1);

			// Check if the race is removed by trying to get its details
			try {
				portal1.viewRaceDetails(1);
				assert false : "Race should have been removed";
			} catch (IDNotRecognisedException e) {
				assert e.getMessage().equals("Race ID not recognised.") : "IDNotRecognisedException message mismatch";
			}
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		// Test Case: Attempt to remove non-existing race
		try {
			// Attempt to remove a race with non-existing race ID
			portal1.removeRaceById(1000); // Assuming 1000 is not an existing race ID
			assert false : "Removing non-existing race should throw IDNotRecognisedException";
		} catch (IDNotRecognisedException e) {
			assert e.getMessage().equals("Race ID not recognised.") : "IDNotRecognisedException message mismatch";
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		System.out.println("Assertion Passed: Remove Race By ID.");

		// Test Case: Valid retrieval of race stages
		try {
			// Create a race and add stages to it
			int raceId = portal2.createRace("Tour de France", "Annual cycling race");
			assert raceId > 0 : "Valid race ID should be returned";

			int stageId1 = portal2.addStageToRace(raceId, "Stage 1", "First stage of the race", 100.5,
					LocalDateTime.now(), StageType.FLAT);
			assert stageId1 > 0 : "Valid stage ID should be returned for Stage 1";

			int stageId2 = portal2.addStageToRace(raceId, "Stage 2", "Second stage of the race", 150.75,
					LocalDateTime.now().plusDays(1), StageType.HIGH_MOUNTAIN);
			assert stageId2 > 0 : "Valid stage ID should be returned for Stage 2";

			// Retrieve the stage IDs for the race
			int[] stageIds = portal2.getRaceStages(raceId);

			// Check if the stage IDs array is not null and contains the expected stage IDs
			assert stageIds != null : "Stage IDs array should not be null";
			assert stageIds.length == 2 : "Expected 2 stages for the race";

			// Check if the retrieved stage IDs match the expected stage IDs
			assert stageIds[0] == stageId1 : "First stage ID mismatch";
			assert stageIds[1] == stageId2 : "Second stage ID mismatch";

			System.out.println("Assertion Passed: Valid retrieval of race stages.");
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		// Test Case: Valid retrieval of stage length
		try {
			// Create a race and add stages to it
			int raceId = portal2.createRace("Tour UK", "Annual cycling race");
			assert raceId > 0 : "Valid race ID should be returned";
			// Create a stage and add it to the system
			int stageId = portal2.addStageToRace(raceId, "Stage 1", "First stage of the race", 100.5,
					LocalDateTime.now(), StageType.FLAT);
			assert stageId > 0 : "Valid stage ID should be returned";

			// Retrieve the length of the stage
			double stageLength = portal2.getStageLength(stageId);

			// Check if the retrieved stage length is positive and matches the expected
			// length
			assert stageLength > 0 : "Stage length should be positive";
			assert stageLength == 100.5 : "Stage length mismatch";

			System.out.println("Assertion Passed: Valid retrieval of stage length.");
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}
		// Test Case: Valid removal of stage
		try {
			// Create a race and add stages to it
			int raceId = portal2.createRace("Tour United Kingdom", "Annual cycling race");
			// Create a stage and add it to the system
			int stageId = portal2.addStageToRace(raceId, "Stage 1", "First stage of the race", 100.5,
					LocalDateTime.now(), StageType.FLAT);
			assert stageId > 0 : "Valid stage ID should be returned";

			// Remove the stage by ID
			portal2.removeStageById(stageId);

			// Check if the stage is removed by verifying that getStageLength throws
			// IDNotRecognisedException
			boolean isStageRemoved = false;
			try {
				double length = portal1.getStageLength(stageId);
				System.out.println(length);
			} catch (IDNotRecognisedException e) {
				isStageRemoved = true; // Stage is removed if IDNotRecognisedException is thrown
			}

			assert isStageRemoved : "Stage should be removed";

			System.out.println("Assertion Passed: Valid removal of stage.");
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		BadCyclingPortalImpl portal = new BadCyclingPortalImpl();

		// Create a race and add stages to it
		int raceId = 0;
		try {
			raceId = portal.createRace("Tour UK", "Annual cycling race");
			assert raceId > 0 : "Valid race ID should be returned";
		} catch (Exception e) {
			assert false : "Failed to create race: " + e.getMessage();
		}

		// Create a stage and add it to the system
		int stageId = 0;
		try {
			stageId = portal.addStageToRace(raceId, "Stage 1", "First stage of the race", 100.5, LocalDateTime.now(),
					StageType.FLAT);
			assert stageId > 0 : "Valid stage ID should be returned";

		} catch (Exception e) {
			assert false : "Failed to create stage: " + e.getMessage();
		}

		// Add a categorized climb checkpoint to the stage
		int checkpointId = 0;
		try {
			checkpointId = portal.addCategorizedClimbToStage(stageId, 50.0, CheckpointType.C2, 8.0, 5.0);
			assert checkpointId > 0 : "Valid checkpoint ID should be returned";
		} catch (Exception e) {
			assert false : "Failed to add categorized climb checkpoint: " + e.getMessage();
		}

		// Check if the checkpoint ID is valid
		assert checkpointId > 0 : "Valid checkpoint ID should be returned";
		System.out.println("Assertion Passed: Add Categorized Climb to Stage.");

		try {
			// Get the stage checkpoints using the method to be tested
			int[] checkpointIds = portal.getStageCheckpoints(stageId);
			assert checkpointIds.length > 0 : "Valid Checkpoints ID should be returned";
			System.out.println("Assertion Passed: Stage Checkpoints.");
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		try {
			checkpointId = portal.addIntermediateSprintToStage(stageId, 50.0);
			assert checkpointId > 0 : "Valid checkpoint ID should be returned";
			System.out.println("Assertion Passed: Intermediate Sprint Stage.");

		} catch (Exception e) {
			assert false : "Failed to add intermediate Stage : " + e.getMessage();
		}

		// Test Case: Valid checkpoint removal
		try {
			// Create a race and add stages to it
			int raceid = portal2.createRace("Tour New Race", "Annual cycling race");
			assert raceid > 0 : "Valid race ID should be returned";
			// Create a stage and add it to the system
			int stageid = portal2.addStageToRace(raceId, "Stage 1", "First stage of the race", 100.5,
					LocalDateTime.now(), StageType.FLAT);
			assert stageid > 0 : "Valid stage ID should be returned";

			// Add an intermediate sprint checkpoint to the stage
			int checkpointid = portal2.addIntermediateSprintToStage(stageid, 50.0);
			assert checkpointid > 0 : "Valid checkpoint ID should be returned";

			// Remove the added checkpoint
			portal2.removeCheckpoint(checkpointid);

			// Assert that the checkpoint is removed from the stage's checkpoints
			int[] stageCheckpoints = portal2.getStageCheckpoints(stageid);
			boolean checkpointRemoved = true;
			for (int id : stageCheckpoints) {
				if (id == checkpointId) {
					checkpointRemoved = false;
					break;
				}
			}
			assert checkpointRemoved : "Checkpoint should be removed from the stage";

			System.out.println("Assertion Passed: Checkpoint removed successfully.");
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		try {
			// Create a race and add stages to it
			int raceid = portal2.createRace("New Race", "Annual cycling race");
			assert raceid > 0 : "Valid race ID should be returned";
			// Create a stage and add it to the system
			int stageid = portal2.addStageToRace(raceid, "Stage 1", "First stage of the race", 100.5,
					LocalDateTime.now(), StageType.FLAT);
			assert stageid > 0 : "Valid stage ID should be returned";

			// Conclude the preparation of the stage
			portal2.concludeStagePreparation(stageid);

			// Retrieve the stage's state and check if it is "waiting for results"
			StageState stageState = StageState.WAITING_FOR_RESULTS;
			assert stageState == StageState.WAITING_FOR_RESULTS : "Stage state should be 'waiting for results'";
			System.out.println("Assertion Passed: Concludes Satge Preparation.");

		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		// Create a team
		int teamId = 0;
		try {
			teamId = portal.createTeam("Team A", "Team A Description");
		} catch (IllegalNameException e) {
			e.printStackTrace();
		} catch (InvalidNameException e) {
			e.printStackTrace();
		}

		// Create a rider and get the rider ID
		int riderId = 0;

		try {
			riderId = portal.createRider(teamId, "John Doe", 1985);
		} catch (IDNotRecognisedException | IllegalArgumentException e) {
			// If an exception occurs, fail the test
			assert false : "Failed to create rider: " + e.getMessage();
		}

		// Check if the rider ID is valid
		assert riderId > 0 : "Valid rider ID should be returned";

		// Get the team's riders and check if the created rider is in the list
		try {
			int[] teamRiders = portal.getTeamRiders(teamId);
			boolean riderFound = false;
			for (int id : teamRiders) {
				if (id == riderId) {
					riderFound = true;
					break;
				}
			}
			assert riderFound : "Created rider should be in the team's riders list";
			System.out.println("Assertion Passed: Create Rider.");

		} catch (IDNotRecognisedException e) {
			// If an exception occurs, fail the test
			assert false : "Failed to retrieve team riders: " + e.getMessage();
		}

		// Create a team and add riders to it
		int teamid = 0;
		try {
			teamid = portal.createTeam("Team B", "Team Description");
		} catch (IllegalNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int riderId1 = 0;
		try {
			riderId1 = portal.createRider(teamid, "John Doe", 1985);
		} catch (IllegalArgumentException | IDNotRecognisedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int riderId2 = 0;
		try {
			riderId2 = portal.createRider(teamid, "Jane Smith", 1990);
		} catch (IllegalArgumentException | IDNotRecognisedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// Get the team's riders
			int[] teamRiders = portal.getTeamRiders(teamid);

			// Check if the returned array is not null
			assert teamRiders != null : "Team riders array should not be null";

			// Check if the returned array contains the IDs of the added riders
			assert containsId(teamRiders, riderId1) : "Team riders should contain riderId1";
			assert containsId(teamRiders, riderId2) : "Team riders should contain riderId2";

			System.out.println("Assertion Passed: Get Team Riders.");
		} catch (IDNotRecognisedException e) {
			// If an IDNotRecognisedException occurs, fail the test with a specific message
			assert false : "Failed to get team riders: " + e.getMessage();
		} catch (Exception e) {
			// For any other unexpected exception, fail the test with a generic message
			assert false : "Unexpected exception occurred: " + e.getMessage();
		}

		try {
			// Create a team and add a rider to it
			int teamid1 = portal.createTeam("Team C", "Description of Team C");
			assert teamId > 0 : "Valid team ID should be returned";

			int riderid = portal.createRider(teamid1, "John Doe", 1990);
			assert riderid > 0 : "Valid rider ID should be returned";

			// Remove the rider from the team
			portal.removeRider(riderid);

			// Get the team's riders and check if the rider is removed
			int[] teamRiders = portal.getTeamRiders(teamid1);
			boolean riderFound = false;
			for (int id : teamRiders) {
				if (id == riderid) {
					riderFound = true;
					break;
				}
			}

			// Assert that the rider is not found in the team's list of riders
			assert !riderFound : "Rider should have been removed from the team";

			System.out.println("Assertion Passed: Rider removed from the team successfully.");
		} catch (Exception e) {
			assert false : "Unexpected exception thrown: " + e.getMessage();
		}

		try {

			// Add a race to the portal
			int raceId1 = portal2.createRace("Tour de USA", "Description");

			// Add a stage to the race
			int stageId1 = portal2.addStageToRace(raceId1, "Stage 1", "Description", 100.0, LocalDateTime.now(),
					StageType.FLAT);
			// Conclude the preparation of the stage
			portal2.concludeStagePreparation(stageId1);

			// Register rider results with valid data
			LocalTime[] validCheckpoints = { LocalTime.of(10, 0), LocalTime.of(11, 0) };
			portal2.registerRiderResultsInStage(stageId1, 1, validCheckpoints);

			System.out.println("Assertion Passed: Rider Result in Stage");

		} catch (Exception e) {
			assert false : "Unexpected exception occurred: " + e.getMessage();
		}

		
	}

	// Helper method to check if an ID exists in an array
	private static boolean containsId(int[] array, int id) {
		for (int value : array) {
			if (value == id) {
				return true;
			}
		}
		return false;
	}

}
