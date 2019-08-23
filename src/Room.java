import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.LinkedList;

public class Room {

    private List<String> itemsInRoom = new LinkedList<>();
    private List<String> transportableItems = new LinkedList<>();


    @SerializedName("name")
    private String name;


    @SerializedName("description")
    private String description;


    @SerializedName("directions")
    private Direction[] directions;


    @SerializedName("items")
    private Item[] items;




    public String getName() {

        return name;

    }

    public String getDescription() {
        String ret = "";
        String[] descriptionSplit = description.split(", ");
        for (String s : descriptionSplit) {
            if (ret.equals("")) {
                ret += s;
            } else {
                ret += ", \n" + s;
            }
        }

        return ret;

    }

    public Direction[] getDirections() {

        return directions;

    }


    public List<String> getItems() {

        return itemsInRoom;

    }


    public void setItemsInRoom() {

        if (items != null) {

            for (Item i : items) {

                itemsInRoom.add(i.getName());

            }
        }

    }

    public void removeItemInRoom(String item) {

        itemsInRoom.remove(item);
    }


    public boolean canTransport(String item) {

        for (String s : transportableItems) {

            if (item.equalsIgnoreCase(s)) {

                return true;
            }

        }

        return false;

    }


    public void setTransportableItems() {

        if (items != null) {

            for (Item i : items) {

                i.setCanTransport();

                if (i.canTransport()) {

                    transportableItems.add(i.getName());

                }

            }
        }

    }

    public List<String> getTransportableItems() {

        return transportableItems;

    }

    public String getTrivia(String item) {
        String trivia = "";

        if (items != null) {

            for (Item i : items) {

                    if (i.getName().equalsIgnoreCase(item)) {

                        trivia = i.getTrivia();

                    }
            }

        }

        return trivia;
    }


    public List<String> getValidAnswers(String item) {
        List<String> answers = new LinkedList<>();

        if (items != null) {

            for (Item i : items) {

                if (i.getName().equalsIgnoreCase(item)) {

                    answers = i.getValidAnswersList();

                }

            }

        }

        return answers;
    }




}
