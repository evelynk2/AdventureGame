import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.LinkedList;



public class Player {

    private List<String> alteredItems = new LinkedList<>();
    private List<String> transportableItems = new LinkedList<>();


    @SerializedName("items")
    private Item[] items;


    public List<String> getItems() {

        return alteredItems;

    }

    public void setAlteredItems() {

        if (items != null) {

            for (Item i : items) {

                alteredItems.add(i.getName());

            }

        }

    }


    public void addItems(String item) {

        alteredItems.add(item);

    }

    public void addTransportItem(String item) {

        transportableItems.add(item);

    }

    public List<String> getTransportableItems() {

        return transportableItems;
    }



}
