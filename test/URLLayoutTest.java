import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.varia.NullAppender;
import org.junit.Before;
import org.junit.Test;
import com.mashape.unirest.http.HttpResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;


public class URLLayoutTest {


    private Layout urlAdventureTest;
    private HttpResponse<String> response;

    private final int STATUS_OK = 200;

    @Before
    public void setUp() throws Exception {

        BasicConfigurator.configure(new NullAppender());

        String url = "https://pastebin.com/raw/Cpym16X4";

        new URL(url);

        try {
            response = Unirest.get(url).asString();
        } catch (UnirestException e) {
            throw e;
        }

        if (response.getStatus() == STATUS_OK) {
            String json = response.getBody();
            Gson gson = new Gson();
            urlAdventureTest = gson.fromJson(json, Layout.class);
            urlAdventureTest.setStatus("ok");

        }
    }


    // TESTING URL ADVENTURE JSON SERIALIZED

    @Test
    public void getOKStatus() throws Exception {
        assertEquals("ok", urlAdventureTest.getStatus());
    }


    @Test (expected = MalformedURLException.class)
    public void badMalformedURLException() throws Exception {
        Adventure.httpRequest("badUrl");
    }

    @Test (expected = UnirestException.class)
    public void badURLUnirestException() throws Exception {
        Adventure.httpRequest("https://courses.engr.illinois");
    }

    @Test
    public void getURLStartingRoom() throws Exception {
        assertEquals("Evelyn's Room", urlAdventureTest.getStartingRoom());
    }

    @Test
    public void getURLEndingRoom() {
        assertEquals("Bathroom", urlAdventureTest.getEndingRoom());
    }

    @Test
    public void getURLRoomName() {
        assertEquals("Evelyn's Room", urlAdventureTest.getRoomName(urlAdventureTest.getStartingRoom()));
    }

    @Test
    public void getURLRoomDescription() {
        assertEquals("You are heading down to the basement. You can continue.", urlAdventureTest.getRoomDescription("Basement Door"));
    }

    @Test
    public void getURLDirectionNames() {
        List<String> directions = new LinkedList<>();
        directions.add("Right");
        directions.add("Left");
        directions.add("Back");

        assertEquals(directions, urlAdventureTest.getDirectionNames("Living Room"));
    }

    @Test
    public void getURLDirectionRoom() {
        assertEquals("Parents' Room", urlAdventureTest.getDirectionRoom("Upstairs Hallway", "Right"));
    }

}
