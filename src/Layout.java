import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.LinkedList;

public class Layout {

    private String status;

    @SerializedName("startingRoom")
    private String startingRoom;

    @SerializedName("endingRoom")
    private String endingRoom;

    @SerializedName("rooms")
    private Room[] rooms;

    @SerializedName("player")
    private Player player;


    public void setUp() {
        player.setAlteredItems();

        for (Room r : rooms) {

            r.setItemsInRoom();

            r.setTransportableItems();

            for (Direction d : r.getDirections()) {

                d.setEnabledBoolean();

            }

        }

    }


    public String getStartingRoom() {

        return startingRoom;

    }


    public String getEndingRoom() {

        return endingRoom;

    }

    public Room[] getRooms() {

        return rooms;

    }

    public Player getPlayer() {
        return player;
    }


    public String getRoomName(String currentRoom) {

        return currentRoom;

    }


    public String getRoomDescription(String currentRoom) {
        for (Room r : getRooms()) {

            if (r.getName().equalsIgnoreCase(currentRoom)) {

                return r.getDescription();

            }
        }

        return null;

    }



    public List<String> getDirectionNames(String currentRoom) {
        List<String> directionNames = new LinkedList<>();

        for (Room r : getRooms()) {

            if (r.getName().equalsIgnoreCase(currentRoom)) {

                Direction[] directions = r.getDirections();

                for (Direction d : directions) {

                    directionNames.add(d.getDirectionName());

                }

            }
        }


        return directionNames;

    }


    public String getDirectionRoom(String currentRoom, String directionName) {

        for (Room r : getRooms()) {

            if (r.getName().equals(currentRoom)) {

                Direction [] directions = r.getDirections();

                for (Direction d : directions) {

                    if (d.getDirectionName().equalsIgnoreCase(directionName)) {

                        return d.getRoom();

                    }

                }
            }
        }

        return null;

    }



    public boolean isEnabled(String currentRoom, String directionName) {
        boolean isEnabled = true;


        OUTER_LOOP:
        for (Room r : getRooms()) {


            if (r.getName().equals(currentRoom)) {


                Direction[] directions = r.getDirections();

                for (Direction d : directions) {

                    if (d.getDirectionName().equalsIgnoreCase(directionName)) {

                        if (!d.getEnabled()) {

                            isEnabled = false;
                            break OUTER_LOOP;
                        }

                    }

                }
            }
        }

        return isEnabled;
    }


    public boolean isKeyItem(String keyItem, String currentRoom, String directionName) {
        boolean isKeyItem = false;

        OUTER_LOOP:
        for (Room r : getRooms()) {

            if (r.getName().equals(currentRoom)) {

                Direction [] directions = r.getDirections();

                for (Direction d : directions) {

                    if (d.getDirectionName().equalsIgnoreCase(directionName)) {

                        for (String s : d.getValidKeyNames()) {

                            if (s.equalsIgnoreCase(keyItem)) {

                                isKeyItem = true;
                                break OUTER_LOOP;
                            }

                        }

                    }

                }
            }
        }

        return isKeyItem;

    }



    public List<String> getItemsToPickup(String currentRoom) {
        List<String> items = new LinkedList<>();

        for (Room r : getRooms()) {

            if (r.getName().equals(currentRoom)) {

                if (r.getItems() != null) {

                    items = r.getItems();

                }

            }

        }

        return items;
    }



    public boolean isPickupItem(String item, String currentRoom) {
        boolean isPickupItem = false;

        OUTER_LOOP:
        for (Room r : getRooms()) {

            if (r.getName().equals(currentRoom)) {

                for (String s : getItemsToPickup(currentRoom)) {

                    if (s.equalsIgnoreCase(item)) {

                        isPickupItem = true;
                        break OUTER_LOOP;

                    }

                }

            }
        }

        return isPickupItem;
    }


    public boolean isTransportItem(String item) {
        boolean isTransportItem = false;


        OUTER_LOOP :
        for (Room r : getRooms()) {

            for (String s : r.getTransportableItems()) {

                if (item.equalsIgnoreCase(s)) {

                    isTransportItem = true;
                    break OUTER_LOOP;
                }

            }

        }

        return isTransportItem;
    }



    public void addItem(String item) {

        player.addItems(item);

    }

    public void addTransportItem(String item) {

        player.addTransportItem(item);
    }


    public void removeItem(String item, String currentRoom) {

        for (Room r : rooms) {

            if (r.getName().equals(currentRoom)) {

                r.removeItemInRoom(item);

            }

        }

    }


    public void getItemsInRoomDescription(String currentRoom) {

        if (getItemsToPickup(currentRoom) != null && !getItemsToPickup(currentRoom).isEmpty()) {

            System.out.println("The items you can pickup are: " + getItemsToPickup(currentRoom));

        }


    }



    public List<String> getCurrentItems() {

        return player.getItems();
    }




    public void unlockRoom(String currentRoom, String directionWord) {

        for (Room r : getRooms()) {

            if (r.getName().equalsIgnoreCase(currentRoom)) {

                for (Direction d : r.getDirections()) {

                    if (d.getDirectionName().equalsIgnoreCase(directionWord)) {

                        d.unlockRoom();

                    }

                }

            }

        }


    }


    public List<String> getAllRooms() {
        List<String> allRooms = new LinkedList<>();

        for (Room r : getRooms()) {

            allRooms.add(r.getName());

        }

     return allRooms;

    }


    public String getTrivia(String item, String currentRoom) {
        String trivia = "";

        OUTER_LOOP :
        for (Room r : getRooms()) {

            if (r.getName().equalsIgnoreCase(currentRoom)) {

                for (String s : getItemsToPickup(currentRoom)) {

                    if (s.equalsIgnoreCase(item)) {

                        trivia = r.getTrivia(s);
                        break OUTER_LOOP;
                    }

                }

            }

        }

        return trivia;
    }



    public boolean isValidTriviaAnswer(String answer, String item, String currentRoom) {
        boolean isValidAnswer = false;

        OUTER_LOOP :
        for (Room r: getRooms()) {

            if (r.getName().equalsIgnoreCase(currentRoom)) {

                for (String s : getItemsToPickup(currentRoom)) {

                    if (s.equalsIgnoreCase(item)) {

                        for (String a : r.getValidAnswers(item)) {

                            if (a.equalsIgnoreCase(answer)) {

                                isValidAnswer = true;
                                break OUTER_LOOP;
                            }

                        }

                    }
                }
            }
        }
        return isValidAnswer;
    }




    public void setStatus(String s) {

        status = s;

    }



    public String getStatus() {
        return status;
    }


}
