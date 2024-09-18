package cycling;


public class Main {

	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");
		// TODO replace BadMiniCyclingPortalImpl by CyclingPortalImpl
		MiniCyclingPortal portal1 = new BadMiniCyclingPortalImpl();
		MiniCyclingPortal portal2 = new BadMiniCyclingPortalImpl();
		assert (portal1.getRaceIds().length == 0)
				: "Innitial Portal not empty as required or not returning an empty array.";
		assert (portal2.getTeams().length == 0)
				: "Innitial Portal not empty as required or not returning an empty array.";

	}

}
