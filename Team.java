package cycling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Team {
	private int id;
	private String name;
	private String description;
	private List<Rider> riders;

	public Team(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.riders = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Rider> getRiders() {
        return riders;
    }

    public void setRiders(List<Rider> riders) {
        this.riders = riders;
    }

    public void addRider(Rider rider) {
        riders.add(rider);
    }
    public boolean containsRider(int riderId) {
        for (Rider rider : riders) {
            if (rider.getId() == riderId) {
                return true;
            }
        }
        return false;
    }
    public void removeRider(int riderId) {
        Iterator<Rider> iterator = riders.iterator();
        while (iterator.hasNext()) {
            Rider rider = iterator.next();
            if (rider.getId() == riderId) {
                iterator.remove();
                break; // Exit the loop after removing the rider
            }
        }
    }

}
