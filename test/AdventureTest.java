import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;


public class AdventureTest {

    private OutputStream out;
    private InputStream in;

    private PrintStream systemOut;

    @Before
    public void setUpSystem() {
        out = new ByteArrayOutputStream();
        systemOut = new PrintStream(out);
        System.setOut(systemOut);
    }

    @After
    public void restoreSystem() {
        System.setIn(in);
        System.setOut(systemOut);
    }



//----------------------------------------------------- TESTING DEFAULT GAMEPLAY ------------------------------------------------------------------//

    @Test
    public void testDefaultGameplayExit() throws IOException {
        // tests 'exit' keyword
        String testString = "default" + System.lineSeparator() + "go east ok" + System.lineSeparator() + "exit";

        in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "\n" +
                "\n" +
                "Your adventure will start now.\n" +
                "Some hints: \n" +
                "You may pick up items which may help you open locked doors, \n" +
                "and at any time in the game, you may say 'my items' and the items you currently have will be displayed to you. \n" +
                "Enjoy! \n" +
                "\n" +
                "Your journey begins here.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "I can't go east ok\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "Exiting...End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());
    }


    @Test
    public void testDefaultGameplayQuit() throws IOException {
        // tests 'quit' keyword
        String testString = "default" + System.lineSeparator() + "go east" + System.lineSeparator() + "quit";

        InputStream in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "\n" +
                "\n" +
                "Your adventure will start now.\n" +
                "Some hints: \n" +
                "You may pick up items which may help you open locked doors, \n" +
                "and at any time in the game, you may say 'my items' and the items you currently have will be displayed to you. \n" +
                "Enjoy! \n" +
                "\n" +
                "Your journey begins here.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "I can't go east\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "Exiting...End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());
    }


    @Test
    public void testDefaultGameplayRandomWords() throws IOException {
        // tests random words that provoke "I don't understand" or "I can't go to"
        String testString = "default\n" + "go forwar\n" + " \n" + "go\n" + "go TO HECK\n" + "goPHer\n" + "quit";

        InputStream in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "\n" +
                "\n" +
                "Your adventure will start now.\n" +
                "Some hints: \n" +
                "You may pick up items which may help you open locked doors, \n" +
                "and at any time in the game, you may say 'my items' and the items you currently have will be displayed to you. \n" +
                "Enjoy! \n" +
                "\n" +
                "Your journey begins here.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "I can't go forwar\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "I don't understand ' '\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "I don't understand 'go'\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "I can't go TO HECK\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "I don't understand 'goPHer'\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "Exiting...End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());
    }


    @Test
    public void testDefaultGameplayWin() throws IOException {
        //reachs the default final destination and wins the monster battle
        String testString = "default\n" + "pickup cat treat\n" + "philosophy\n" + "go forward\n" + "use cat treat with forward\n" + "go down\n" +
                "go forwards\n" + "go left\n" + "pickup hammer\n" + "2\n" +  "go forward\n" + "go forwards\n" + "go forward\n" + "go forward\n" +
                "use hammer with right\n" + "pickup key\n" + "princess\n" + "go forward\n" + "go back\n" + "go back\n" + "go back\n" +
                "go back\n" + "use key with right\n" + "duck\n" + "run\n" + "duck\n" + "hit\n" + "hit";

        InputStream in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "\n" +
                "\n" +
                "Your adventure will start now.\n" +
                "Some hints: \n" +
                "You may pick up items which may help you open locked doors, \n" +
                "and at any time in the game, you may say 'my items' and the items you currently have will be displayed to you. \n" +
                "Enjoy! \n" +
                "\n" +
                "Your journey begins here.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "To pickup cat treat you must answer the following trivia question correctly: \n" +
                "What is Evelyn's major?\n" +
                "Correct!\n" +
                "Picked up item: cat treat\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal]\n" +
                "From here, you can go: [Forward]\n" +
                "You are in the upstairs hallway of your house. You hear your sister praying for hot cheetos in her room to the left, \n" +
                "and you turn to see your cat laying on the stairs to the first floor in front of you, \n" +
                "and your parents' room on your right.\n" +
                "From here, you can go: [Left, Forward, Right, Back]\n" +
                "You have successfully unlocked this entrance!\n" +
                "You are on the stairs and see your cat sprawled on one of the steps. To be allowed to pass, \n" +
                "you give her a cat treat and continue on. You can go up to the upstairs hallway or down to the downstairs hallway.\n" +
                "From here, you can go: [Up, Down]\n" +
                "You are in the downstairs hallway. You can see the living room in front of you and the stairs to go upstairs behind you.\n" +
                "From here, you can go: [Forwards, Back]\n" +
                "You are in the living room. To your right is the bathroom and to your left is the kitchen. Behind you is the downstairs hallway.\n" +
                "The items you can pickup are: [tv remote]\n" +
                "From here, you can go: [Right, Left, Back]\n" +
                "You are in the kitchen. You can go forward to the dining table or behind you back to the living room.\n" +
                "The items you can pickup are: [hammer]\n" +
                "From here, you can go: [Forward, Back]\n" +
                "To pickup hammer you must answer the following trivia question correctly: \n" +
                "How many sisters does Evelyn have?\n" +
                "Correct!\n" +
                "Picked up item: hammer\n" +
                "You are in the kitchen. You can go forward to the dining table or behind you back to the living room.\n" +
                "From here, you can go: [Forward, Back]\n" +
                "You are by the dining table. You can see the pantry to your right, \n" +
                "the door leading to the basement in front of you, \n" +
                "and the kitchen behind you.\n" +
                "The items you can pickup are: [knife]\n" +
                "From here, you can go: [Right, Forwards, Back]\n" +
                "You are heading down to the basement. You can continue.\n" +
                "From here, you can go: [Forward]\n" +
                "You are in the entrance of the basement. You can go back to the dining table through the basement door or go right to the TV room or forward to the ping pong table.\n" +
                "From here, you can go: [Back, Right, Forward]\n" +
                "You are by the Ping Pong Table. You hit the ball against the paddle a few times and watch as your cat, \n" +
                "who has followed you, \n" +
                "jumps after the ball. Getting bored, \n" +
                "you look to your right and see the closet and look back and see the basement entrance.\n" +
                "The items you can pickup are: [paddle]\n" +
                "From here, you can go: [Right, Back]\n" +
                "You have successfully unlocked this entrance!\n" +
                "After hammering down the door, \n" +
                "you find yourself in the closet. You look forward to see the ping pong table.\n" +
                "The items you can pickup are: [key]\n" +
                "From here, you can go: [Forward]\n" +
                "To pickup key you must answer the following trivia question correctly: \n" +
                "What is Evelyn's cats' name?\n" +
                "Correct!\n" +
                "Picked up item: key\n" +
                "After hammering down the door, \n" +
                "you find yourself in the closet. You look forward to see the ping pong table.\n" +
                "From here, you can go: [Forward]\n" +
                "You are by the Ping Pong Table. You hit the ball against the paddle a few times and watch as your cat, \n" +
                "who has followed you, \n" +
                "jumps after the ball. Getting bored, \n" +
                "you look to your right and see the closet and look back and see the basement entrance.\n" +
                "The items you can pickup are: [paddle]\n" +
                "From here, you can go: [Right, Back]\n" +
                "You are in the entrance of the basement. You can go back to the dining table through the basement door or go right to the TV room or forward to the ping pong table.\n" +
                "From here, you can go: [Back, Right, Forward]\n" +
                "You are by the dining table. You can see the pantry to your right, \n" +
                "the door leading to the basement in front of you, \n" +
                "and the kitchen behind you.\n" +
                "The items you can pickup are: [knife]\n" +
                "From here, you can go: [Right, Forwards, Back]\n" +
                "You are in the kitchen. You can go forward to the dining table or behind you back to the living room.\n" +
                "From here, you can go: [Forward, Back]\n" +
                "You are in the living room. To your right is the bathroom and to your left is the kitchen. Behind you is the downstairs hallway.\n" +
                "The items you can pickup are: [tv remote]\n" +
                "From here, you can go: [Right, Left, Back]\n" +
                "You have successfully unlocked this entrance!\n" +
                "You have reached the ending room... and so has THE MONSTER. To enter, you have to win the next battle.\n" +
                "If you lose, you will be transported back to the starting room.\n" +
                "He will attack, and you will have to defend yourself with the right sequence of moves until he gets tired.\n" +
                "To defend yourself, simply type in 'duck', 'hit', or 'run'. \n" +
                "May the best fighter win!\n" +
                "...\n" +
                "THE MONSTER unveils his sharp teeth and begins to run towards you with his arms raised.\n" +
                "What do you do?\n" +
                "THE MONSTER begins to sway from side to side, and then pulls out a knife.\n" +
                "What do you do?\n" +
                "Losing grip of the knife, THE MONSTER then turns and decides to throw a punch.\n" +
                "What do you do?\n" +
                "Seeing that you are still undefeated, THE MONSTER begins to pant from exhaustion and momentarily does not attack.\n" +
                "What do you do?\n" +
                "With almost no energy left to go on, THE MONSTER falls to the ground and attemps to kick you.\n" +
                "What do you do?\n" +
                "You have beat THE MONSTER!\n" +
                "Opening up the door to the bathroom, \n" +
                "you enter and turn to face the mirror. And suddenly, \n" +
                "after this entire adventure, \n" +
                "you finally realize why this is the ending room. Smiling at the mirror, \n" +
                "you realize that YOU are the real prize.\n" +
                "End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());

    }




//----------------------------------------------------- TESTING NEW URL GAMEPLAY ------------------------------------------------------------------//

    @Test
    public void testBadURLGameplay() throws IOException {
        // tests a good, but invalid (not Json), URL
        String testString = "URL https://courses.engr.illinois.edu\n" + "go foRWARD\n" + "exit";

        InputStream in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "Invalid URL. Setting adventure to: 'Default'.\n" +
                "\n" +
                "\n" +
                "Your adventure will start now.\n" +
                "Some hints: \n" +
                "You may pick up items which may help you open locked doors, \n" +
                "and at any time in the game, you may say 'my items' and the items you currently have will be displayed to you. \n" +
                "Enjoy! \n" +
                "\n" +
                "Your journey begins here.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "You are in the upstairs hallway of your house. You hear your sister praying for hot cheetos in her room to the left, \n" +
                "and you turn to see your cat laying on the stairs to the first floor in front of you, \n" +
                "and your parents' room on your right.\n" +
                "From here, you can go: [Left, Forward, Right, Back]\n" +
                "Exiting...End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());


    }







//----------------------------------------------------- TESTING GENERAL GAMEPLAY ------------------------------------------------------------------//

    @Test
    public void testImmediateGameplayExit() throws IOException {
        // tests an immediate call to exit
        String testString = "exit";

        InputStream in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "Exiting...\n" +
                "End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());
    }





//----------------------------------------------------- TESTING NEW FEATURES ------------------------------------------------------------------//




    @Test
    public void testParsingFileName() throws IOException {
        // tests a call to parse a file
        String testString = "filename adventure_advanced_json\n" + "exit";

        InputStream in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "\n" +
                "\n" +
                "Your adventure will start now.\n" +
                "Some hints: \n" +
                "You may pick up items which may help you open locked doors, \n" +
                "and at any time in the game, you may say 'my items' and the items you currently have will be displayed to you. \n" +
                "Enjoy! \n" +
                "\n" +
                "Your journey begins here.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "Exiting...End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());
    }



    @Test
    public void testDefaultGameplayNewRandomWords() throws IOException {
        // tests random words that provoke "picked up" or "you have unlocked the room" or "wrong answer to trivia question" or "my items"
        String testString = "default\n" + "pickup cat treat\n" + "biology\n" +
                "go forward\n" + "go left\n" + "use hot cheetos with left\n" + "my items\n" + "quit";

        InputStream in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "\n" +
                "\n" +
                "Your adventure will start now.\n" +
                "Some hints: \n" +
                "You may pick up items which may help you open locked doors, \n" +
                "and at any time in the game, you may say 'my items' and the items you currently have will be displayed to you. \n" +
                "Enjoy! \n" +
                "\n" +
                "Your journey begins here.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "To pickup cat treat you must answer the following trivia question correctly: \n" +
                "What is Evelyn's major?\n" +
                "Sorry, 'biology' is not the correct answer. You may pickup the item and try again.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "You are in the upstairs hallway of your house. You hear your sister praying for hot cheetos in her room to the left, \n" +
                "and you turn to see your cat laying on the stairs to the first floor in front of you, \n" +
                "and your parents' room on your right.\n" +
                "From here, you can go: [Left, Forward, Right, Back]\n" +
                "This entrance is LOCKED. You may use one of your items to try to enter.\n" +
                "Hint: you may say 'my items' to view your current items.\n" +
                "You are in the upstairs hallway of your house. You hear your sister praying for hot cheetos in her room to the left, \n" +
                "and you turn to see your cat laying on the stairs to the first floor in front of you, \n" +
                "and your parents' room on your right.\n" +
                "From here, you can go: [Left, Forward, Right, Back]\n" +
                "You have successfully unlocked this entrance!\n" +
                "You are in your sister's room. As promised, \n" +
                "you give the sister some hot cheetos. In front of you, \n" +
                "you see the entrance back into the hallway\n" +
                "The items you can pickup are: [painting, laptop]\n" +
                "From here, you can go: [Forward]\n" +
                "The items you currently have are: [hot cheetos]\n" +
                "Exiting...End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());
    }



    @Test
    public void testGameplayTeleportationItem() throws IOException {
        // tests to see if the correct item is a transportation item
        String testString = "default\n" + "go forward\n" + "use hot cheetos with left\n" + "pickup painting\n" + "yes\n" + "exit";

        InputStream in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "\n" +
                "\n" +
                "Your adventure will start now.\n" +
                "Some hints: \n" +
                "You may pick up items which may help you open locked doors, \n" +
                "and at any time in the game, you may say 'my items' and the items you currently have will be displayed to you. \n" +
                "Enjoy! \n" +
                "\n" +
                "Your journey begins here.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "You are in the upstairs hallway of your house. You hear your sister praying for hot cheetos in her room to the left, \n" +
                "and you turn to see your cat laying on the stairs to the first floor in front of you, \n" +
                "and your parents' room on your right.\n" +
                "From here, you can go: [Left, Forward, Right, Back]\n" +
                "You have successfully unlocked this entrance!\n" +
                "You are in your sister's room. As promised, \n" +
                "you give the sister some hot cheetos. In front of you, \n" +
                "you see the entrance back into the hallway\n" +
                "The items you can pickup are: [painting, laptop]\n" +
                "From here, you can go: [Forward]\n" +
                "To pickup painting you must answer the following trivia question correctly: \n" +
                "Does Evelyn have a cat?\n" +
                "Correct!\n" +
                "Congratulations, you picked up a TELEPORTATION item!\n" +
                "You can now transport to any room by saying 'use painting with (the room).'\n" +
                "You are in your sister's room. As promised, \n" +
                "you give the sister some hot cheetos. In front of you, \n" +
                "you see the entrance back into the hallway\n" +
                "The items you can pickup are: [laptop]\n" +
                "From here, you can go: [Forward]\n" +
                "Exiting...End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());
    }


    @Test
    public void testTeleportation() throws IOException {
        // tests to see if the transportation item works
        String testString = "default\n" + "go forward\n" + "use hot cheetos with left\n" + "pickup painting\n" + "yes\n" + "go forward\n" +
                "use paINting with tv room\n" + "exit";

        InputStream in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "\n" +
                "\n" +
                "Your adventure will start now.\n" +
                "Some hints: \n" +
                "You may pick up items which may help you open locked doors, \n" +
                "and at any time in the game, you may say 'my items' and the items you currently have will be displayed to you. \n" +
                "Enjoy! \n" +
                "\n" +
                "Your journey begins here.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "You are in the upstairs hallway of your house. You hear your sister praying for hot cheetos in her room to the left, \n" +
                "and you turn to see your cat laying on the stairs to the first floor in front of you, \n" +
                "and your parents' room on your right.\n" +
                "From here, you can go: [Left, Forward, Right, Back]\n" +
                "You have successfully unlocked this entrance!\n" +
                "You are in your sister's room. As promised, \n" +
                "you give the sister some hot cheetos. In front of you, \n" +
                "you see the entrance back into the hallway\n" +
                "The items you can pickup are: [painting, laptop]\n" +
                "From here, you can go: [Forward]\n" +
                "To pickup painting you must answer the following trivia question correctly: \n" +
                "Does Evelyn have a cat?\n" +
                "Correct!\n" +
                "Congratulations, you picked up a TELEPORTATION item!\n" +
                "You can now transport to any room by saying 'use painting with (the room).'\n" +
                "You are in your sister's room. As promised, \n" +
                "you give the sister some hot cheetos. In front of you, \n" +
                "you see the entrance back into the hallway\n" +
                "The items you can pickup are: [laptop]\n" +
                "From here, you can go: [Forward]\n" +
                "You are in the upstairs hallway of your house. You hear your sister praying for hot cheetos in her room to the left, \n" +
                "and you turn to see your cat laying on the stairs to the first floor in front of you, \n" +
                "and your parents' room on your right.\n" +
                "From here, you can go: [Left, Forward, Right, Back]\n" +
                "You have successfully transported to: tv room\n" +
                "You are in the TV Room. You use the TV Remote to turn on the TV, \n" +
                "but then realize the TV is broken. You turn around and look ahead to the basement entrance.\n" +
                "From here, you can go: [Forward]\n" +
                "Exiting...End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());

    }


    @Test
    public void testFailedTeleportation() throws IOException {
        // tests using a non-teleportation item to try to teleport
        String testString = "default\n" + "go forward\n" + "use hot cheetos with left\n" + "pickup painting\n" + "yes\n" + "go forward\n" +
                "use hot cheetos with baTHroom\n" + "exit";

        InputStream in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "\n" +
                "\n" +
                "Your adventure will start now.\n" +
                "Some hints: \n" +
                "You may pick up items which may help you open locked doors, \n" +
                "and at any time in the game, you may say 'my items' and the items you currently have will be displayed to you. \n" +
                "Enjoy! \n" +
                "\n" +
                "Your journey begins here.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "You are in the upstairs hallway of your house. You hear your sister praying for hot cheetos in her room to the left, \n" +
                "and you turn to see your cat laying on the stairs to the first floor in front of you, \n" +
                "and your parents' room on your right.\n" +
                "From here, you can go: [Left, Forward, Right, Back]\n" +
                "You have successfully unlocked this entrance!\n" +
                "You are in your sister's room. As promised, \n" +
                "you give the sister some hot cheetos. In front of you, \n" +
                "you see the entrance back into the hallway\n" +
                "The items you can pickup are: [painting, laptop]\n" +
                "From here, you can go: [Forward]\n" +
                "To pickup painting you must answer the following trivia question correctly: \n" +
                "Does Evelyn have a cat?\n" +
                "Correct!\n" +
                "Congratulations, you picked up a TELEPORTATION item!\n" +
                "You can now transport to any room by saying 'use painting with (the room).'\n" +
                "You are in your sister's room. As promised, \n" +
                "you give the sister some hot cheetos. In front of you, \n" +
                "you see the entrance back into the hallway\n" +
                "The items you can pickup are: [laptop]\n" +
                "From here, you can go: [Forward]\n" +
                "You are in the upstairs hallway of your house. You hear your sister praying for hot cheetos in her room to the left, \n" +
                "and you turn to see your cat laying on the stairs to the first floor in front of you, \n" +
                "and your parents' room on your right.\n" +
                "From here, you can go: [Left, Forward, Right, Back]\n" +
                "The item: 'hot cheetos' cannot be used to teleport to this room.\n" +
                "You are in the upstairs hallway of your house. You hear your sister praying for hot cheetos in her room to the left, \n" +
                "and you turn to see your cat laying on the stairs to the first floor in front of you, \n" +
                "and your parents' room on your right.\n" +
                "From here, you can go: [Left, Forward, Right, Back]\n" +
                "Exiting...End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());
    }


    @Test
    public void testCorrectTriviaAnswer() throws IOException {
        // tests answering correctly to a trivia question
        String testString = "default\n" + "pickup hairbrush\n" + "green\n" + "exit";

        InputStream in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "\n" +
                "\n" +
                "Your adventure will start now.\n" +
                "Some hints: \n" +
                "You may pick up items which may help you open locked doors, \n" +
                "and at any time in the game, you may say 'my items' and the items you currently have will be displayed to you. \n" +
                "Enjoy! \n" +
                "\n" +
                "Your journey begins here.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "To pickup hairbrush you must answer the following trivia question correctly: \n" +
                "What is Evelyn's favorite color?\n" +
                "Correct!\n" +
                "Picked up item: hairbrush\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "Exiting...End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());


    }


    @Test
    public void testWrongTriviaAnswer() throws IOException {
        // tests answering wrong to a trivia question
        String testString = "default\n" + "pickup participation medal\n" + "panda express\n" + "exit";

        InputStream in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "\n" +
                "\n" +
                "Your adventure will start now.\n" +
                "Some hints: \n" +
                "You may pick up items which may help you open locked doors, \n" +
                "and at any time in the game, you may say 'my items' and the items you currently have will be displayed to you. \n" +
                "Enjoy! \n" +
                "\n" +
                "Your journey begins here.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "To pickup participation medal you must answer the following trivia question correctly: \n" +
                "What is Evelyn's favorite fast food?\n" +
                "Sorry, 'panda express' is not the correct answer. You may pickup the item and try again.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "Exiting...End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());
    }



    @Test
    public void testWinMonster() throws IOException {
        // tests winning the monster and getting into the ending room
        String testString = "default\n" + "go forward\n" + "use hot cheetos with left\n" + "pickup painting\n" +
                "yes\n" + "use painting with bathroom\n" + "jnFSON\n" + "run\n" + "duck\n" + "HiT\n" + "HIT";

        InputStream in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "\n" +
                "\n" +
                "Your adventure will start now.\n" +
                "Some hints: \n" +
                "You may pick up items which may help you open locked doors, \n" +
                "and at any time in the game, you may say 'my items' and the items you currently have will be displayed to you. \n" +
                "Enjoy! \n" +
                "\n" +
                "Your journey begins here.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "You are in the upstairs hallway of your house. You hear your sister praying for hot cheetos in her room to the left, \n" +
                "and you turn to see your cat laying on the stairs to the first floor in front of you, \n" +
                "and your parents' room on your right.\n" +
                "From here, you can go: [Left, Forward, Right, Back]\n" +
                "You have successfully unlocked this entrance!\n" +
                "You are in your sister's room. As promised, \n" +
                "you give the sister some hot cheetos. In front of you, \n" +
                "you see the entrance back into the hallway\n" +
                "The items you can pickup are: [painting, laptop]\n" +
                "From here, you can go: [Forward]\n" +
                "To pickup painting you must answer the following trivia question correctly: \n" +
                "Does Evelyn have a cat?\n" +
                "Correct!\n" +
                "Congratulations, you picked up a TELEPORTATION item!\n" +
                "You can now transport to any room by saying 'use painting with (the room).'\n" +
                "You are in your sister's room. As promised, \n" +
                "you give the sister some hot cheetos. In front of you, \n" +
                "you see the entrance back into the hallway\n" +
                "The items you can pickup are: [laptop]\n" +
                "From here, you can go: [Forward]\n" +
                "You have successfully transported to: bathroom\n" +
                "You have reached the ending room... and so has THE MONSTER. To enter, you have to win the next battle.\n" +
                "If you lose, you will be transported back to the starting room.\n" +
                "He will attack, and you will have to defend yourself with the right sequence of moves until he gets tired.\n" +
                "To defend yourself, simply type in 'duck', 'hit', or 'run'. \n" +
                "May the best fighter win!\n" +
                "...\n" +
                "THE MONSTER unveils his sharp teeth and begins to run towards you with his arms raised.\n" +
                "What do you do?\n" +
                "THE MONSTER begins to sway from side to side, and then pulls out a knife.\n" +
                "What do you do?\n" +
                "Losing grip of the knife, THE MONSTER then turns and decides to throw a punch.\n" +
                "What do you do?\n" +
                "Seeing that you are still undefeated, THE MONSTER begins to pant from exhaustion and momentarily does not attack.\n" +
                "What do you do?\n" +
                "With almost no energy left to go on, THE MONSTER falls to the ground and attemps to kick you.\n" +
                "What do you do?\n" +
                "You have beat THE MONSTER!\n" +
                "Opening up the door to the bathroom, \n" +
                "you enter and turn to face the mirror. And suddenly, \n" +
                "after this entire adventure, \n" +
                "you finally realize why this is the ending room. Smiling at the mirror, \n" +
                "you realize that YOU are the real prize.\n" +
                "End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());

    }

    @Test
    public void testLoseMonster() throws IOException {
        // tests losing to the monster and being teleported back to the starting room
        String testString = "default\n" + "go forward\n" + "use hot cheetos with left\n" + "pickup painting\n" +
                "yes\n" + "use painting with bathroom\n" + "jnFSON\n" + "run\n" + "hit\n" + "run\n" + "exit";

        InputStream in = new ByteArrayInputStream(testString.getBytes());
        System.setIn(in);

        String expectedOutput = "Do you have an adventure URL or a filename? \n" +
                "Type URL or filename followed by a space and then the respective URL or filename. \n" +
                "If you have neither, type 'default'\n" +
                "\n" +
                "\n" +
                "Your adventure will start now.\n" +
                "Some hints: \n" +
                "You may pick up items which may help you open locked doors, \n" +
                "and at any time in the game, you may say 'my items' and the items you currently have will be displayed to you. \n" +
                "Enjoy! \n" +
                "\n" +
                "Your journey begins here.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "You are in the upstairs hallway of your house. You hear your sister praying for hot cheetos in her room to the left, \n" +
                "and you turn to see your cat laying on the stairs to the first floor in front of you, \n" +
                "and your parents' room on your right.\n" +
                "From here, you can go: [Left, Forward, Right, Back]\n" +
                "You have successfully unlocked this entrance!\n" +
                "You are in your sister's room. As promised, \n" +
                "you give the sister some hot cheetos. In front of you, \n" +
                "you see the entrance back into the hallway\n" +
                "The items you can pickup are: [painting, laptop]\n" +
                "From here, you can go: [Forward]\n" +
                "To pickup painting you must answer the following trivia question correctly: \n" +
                "Does Evelyn have a cat?\n" +
                "Correct!\n" +
                "Congratulations, you picked up a TELEPORTATION item!\n" +
                "You can now transport to any room by saying 'use painting with (the room).'\n" +
                "You are in your sister's room. As promised, \n" +
                "you give the sister some hot cheetos. In front of you, \n" +
                "you see the entrance back into the hallway\n" +
                "The items you can pickup are: [laptop]\n" +
                "From here, you can go: [Forward]\n" +
                "You have successfully transported to: bathroom\n" +
                "You have reached the ending room... and so has THE MONSTER. To enter, you have to win the next battle.\n" +
                "If you lose, you will be transported back to the starting room.\n" +
                "He will attack, and you will have to defend yourself with the right sequence of moves until he gets tired.\n" +
                "To defend yourself, simply type in 'duck', 'hit', or 'run'. \n" +
                "May the best fighter win!\n" +
                "...\n" +
                "THE MONSTER unveils his sharp teeth and begins to run towards you with his arms raised.\n" +
                "What do you do?\n" +
                "THE MONSTER begins to sway from side to side, and then pulls out a knife.\n" +
                "What do you do?\n" +
                "Losing grip of the knife, THE MONSTER then turns and decides to throw a punch.\n" +
                "What do you do?\n" +
                "Seeing that you are still undefeated, THE MONSTER begins to pant from exhaustion and momentarily does not attack.\n" +
                "What do you do?\n" +
                "Sorry, you have made 3 wrong moves and THE MONSTER has won! You may try to fight him again.\n" +
                "However, since you have lost, you will be starting from the starting room.\n" +
                "You are looking out the window in your room, \n" +
                "seeing the beautiful view of a park. You turn around and see the hallway in front of you.\n" +
                "The items you can pickup are: [hairbrush, participation medal, cat treat]\n" +
                "From here, you can go: [Forward]\n" +
                "Exiting...End of Test";

        Adventure.main(new String[0]);
        assertEquals(expectedOutput, out.toString());

    }



}