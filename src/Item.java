import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Item {


    private boolean canTransport;

    @SerializedName("name")
    private String name;

    @SerializedName("teleportation")
    private String teleportation;

    @SerializedName("trivia")
    private String trivia;

    @SerializedName("validAnswers")
    private String[] validAnswers;

    public String getName() {

        return name;

    }

    public String getTeleportation() {

        return teleportation;
    }

    public String getTrivia() {

        return trivia;
    }

    public String[] getValidAnswers() {

        return validAnswers;
    }


    public List<String> getValidAnswersList() {
        List<String> answers = new LinkedList<>();

        if (validAnswers != null) {

            answers.addAll(Arrays.asList(validAnswers));

        }

        return answers;
    }


    public void setCanTransport() {

        if (teleportation != null) {

            canTransport = teleportation.equals("true");

        }

    }


    public boolean canTransport() {

        return canTransport;
    }


}
