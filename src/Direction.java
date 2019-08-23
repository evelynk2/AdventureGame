import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.LinkedList;


public class Direction {


    private boolean enabledBoolean;

    @SerializedName("directionName")
    private String directionName;


    @SerializedName("room")
    private String room;



    @SerializedName("enabled")
    private String enabled;



    @SerializedName("validKeyNames")
    private String[] validKeyNames;



    public void setEnabledBoolean() {

        if (!enabled.isEmpty()) {

            if (enabled.equals("false")) {

                enabledBoolean = false;

            } else {

                enabledBoolean = true;

            }

        }

    }


    public String getDirectionName() {

        return directionName;

    }


    public String getRoom() {

        return room;

    }


    public boolean getEnabled() {

        return enabledBoolean;

    }


    public String[] getValidKeyNames() {

        return validKeyNames;

    }

    public void unlockRoom() {

        enabledBoolean = !enabledBoolean;

    }



}
