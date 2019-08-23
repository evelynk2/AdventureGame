import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.varia.NullAppender;

import java.net.URL;

import java.util.Scanner;
import java.lang.System;

public class Adventure {


    private static String currentRoom;

    private static int countOfPlays;

    public static Scanner scanner;

    private static HttpResponse<String> response;

    private static final int STATUS_OK = 200;

    private static Layout urlAdventure;

    private static String fileData;


    public Adventure() {

        fileData = "";
        countOfPlays = 0;
        scanner = new Scanner(System.in);

        System.out.println("Do you have an adventure URL or a filename? \nType URL or filename followed by a space and " +
                "then the respective URL or filename. \nIf you have neither, type 'default'");

        determineURLOrFile();

    }


//----------------------------------------------- PARSING URL or FILENAME ------------------------------------------------------------------//

    public void determineURLOrFile() {

        String response = getInput();

        if (response.equalsIgnoreCase("quit") || response.equalsIgnoreCase("exit")) {

            System.out.println("Exiting...");
            endTestSuite();

        } else if (response.equalsIgnoreCase("default")) {

            setToDefault();

        } else {

            String[] responseArray = response.toLowerCase().split(" ");
            String commandWord = responseArray[0];


            if (commandWord.equalsIgnoreCase("URL")) {


                analyzeURLInput(response.substring(4));


            } else if (commandWord.equalsIgnoreCase("filename")) {


                fileData = getFileContentsAsString(response.substring(9));


                if (fileData != null) {


                    Gson gson = new Gson();
                    urlAdventure = gson.fromJson(fileData, Layout.class);

                    currentRoom = urlAdventure.getStartingRoom();
                    urlAdventure.setUp();
                    gameplay(currentRoom);


                } else {

                    setToDefault();

                }

            } else {

                System.out.println("Invalid. Setting adventure to: 'Default'.");

                setToDefault();

            }

        }


    }


    public static String getFileContentsAsString(String filename) {

        final Path path = FileSystems.getDefault().getPath("data", filename);

        try {

            return new String(Files.readAllBytes(path));

        } catch (IOException e) {

            System.out.println("Couldn't find file: " + filename + "\nSetting adventure to Default.");

            return null;
        }

    }




    public void analyzeURLInput(String URL) {

        try {

            httpRequest(URL);

            gameplay(currentRoom);

        } catch (UnirestException | MalformedURLException | JsonSyntaxException e) {

            System.out.println("Invalid URL. Setting adventure to: 'Default'.");

            setToDefault();

        }

    }


    public void setToDefault() {


        try {

            httpRequest("https://pastebin.com/raw/Cpym16X4");

            gameplay(currentRoom);


        } catch (UnirestException | MalformedURLException ex) {

            System.out.println("Sorry. Exiting...");
            endTestSuite();

        }


    }




    // different URL's to try
    // https://courses.engr.illinois.edu/cs126/adventure/circular.json
    // https://courses.engr.illinois.edu/cs126/sp2019/adventure/student_submissions/CustomAdventureGame.json
    // https://pastebin.com/raw/Cpym16X4

    public static void httpRequest(String url) throws UnirestException, MalformedURLException, JsonSyntaxException {

        BasicConfigurator.configure(new NullAppender());

        // This will throw MalformedURLException if the url is malformed.
        new URL(url);


        try {

            response = Unirest.get(url).asString();

        } catch (UnirestException e) {

            throw e;
        }

        if (response.getStatus() == STATUS_OK) {

            String json = response.getBody();
            Gson gson = new Gson();
            urlAdventure = gson.fromJson(json, Layout.class);

            currentRoom = urlAdventure.getStartingRoom();
            urlAdventure.setUp();

        }
    }





    //-------------------------------------------------- GAMEPLAY METHODS ---------------------------------------------------------------//




    public void gameplay(String currentRoom) {

        if (currentRoom.equalsIgnoreCase(urlAdventure.getEndingRoom())) {

            if (monsterEnding().equalsIgnoreCase("won")) {

                System.out.println("You have beat THE MONSTER!\n" + urlAdventure.getRoomDescription(currentRoom));
                endTestSuite();

            } else {

                System.out.println("Sorry, you have made 3 wrong moves and THE MONSTER has won! You may try to fight him again." +
                        "\nHowever, since you have lost, you will be starting from the starting room.");
                currentRoom = urlAdventure.getStartingRoom();
                gameplay(currentRoom);

            }



        } else {


            if (countOfPlays == 0 && currentRoom.equals(urlAdventure.getStartingRoom())) {
                System.out.println("\n\nYour adventure will start now.\nSome hints: \nYou may pick up items which may " +
                        "help you open locked doors, \nand at any time" +
                        " in the game, you may say 'my items' and the items " +
                        "you currently have will be displayed to you. \nEnjoy! \n");

                System.out.println("Your journey begins here.");
            }

            System.out.println(urlAdventure.getRoomDescription(currentRoom));

            urlAdventure.getItemsInRoomDescription(currentRoom);

            System.out.println("From here, you can go: " + urlAdventure.getDirectionNames(currentRoom));
            countOfPlays++;

            analyzeInput();

        }

    }



    public static String getInput() {

        return scanner.nextLine();

    }


    // for testing purposes to acknowledge the end of test (when exit or quit keyword not typed)
    public void endTestSuite() {
        System.out.print("End of Test");
    }





    // have to fight a monster to get into the ending room
    public String monsterEnding() {
        String wonOrLost = "won";

        // keeps track of how many wrong moves the player has made; if it reaches 3, the player has lost
        int wrongMoves = 0;

        // giving the player rules on how to engage in the battle
        System.out.println("You have reached the ending room... and so has THE MONSTER. To enter, you have to win the next battle." +
                "\nIf you lose, you will be transported back to the starting room." +
                "\nHe will attack, and you will have to defend yourself with the right sequence of moves until he gets tired." +
                "\nTo defend yourself, simply type in 'duck', 'hit', or 'run'. " +
                "\nMay the best fighter win!");

        System.out.println("...");



        for (int k = 0; k < 5; k++) {

            switch(k) {

                case 0:
                    System.out.println("THE MONSTER unveils his sharp teeth and begins to run towards you with his arms raised." +
                            "\nWhat do you do?");
                    if (!analyzeFightInput(k).equalsIgnoreCase("won")) {
                        wrongMoves++;
                    }
                    break;

                case 1:
                    System.out.println("THE MONSTER begins to sway from side to side, and then pulls out a knife." +
                            "\nWhat do you do?");
                    if (!analyzeFightInput(k).equalsIgnoreCase("won")) {
                        wrongMoves++;
                    }
                    break;

                case 2:
                    System.out.println("Losing grip of the knife, THE MONSTER then turns and decides to throw a punch." +
                            "\nWhat do you do?");
                    if (!analyzeFightInput(k).equalsIgnoreCase("won")) {
                        wrongMoves++;
                    }
                    break;

                case 3:
                    System.out.println("Seeing that you are still undefeated, THE MONSTER begins to pant from exhaustion and momentarily does not attack." +
                            "\nWhat do you do?");
                    if (!analyzeFightInput(k).equalsIgnoreCase("won")) {
                        wrongMoves++;
                    }
                    break;


                case 4:
                    System.out.println("With almost no energy left to go on, THE MONSTER falls to the ground and attemps to kick you." +
                            "\nWhat do you do?");
                    if (!analyzeFightInput(k).equalsIgnoreCase("won")) {
                        wrongMoves++;
                    }
                    break;
            }


            if (wrongMoves > 2) {

                wonOrLost = "lost";
                break;

            }

        }


        return wonOrLost;
    }



    private String analyzeFightInput(int move) {
        String result = "won";
        String input = getInput().toLowerCase();

        if (move == 0) {

            if (!input.equalsIgnoreCase("duck")) {

                result = "lost";

            }


        } else if (move == 1) {

            if (!input.equalsIgnoreCase("run")) {

                result = "lost";

            }

        } else if (move == 2) {

            if (!input.equalsIgnoreCase("duck")) {

                result = "lost";

            }

        } else if (move == 3) {

            if (!input.equalsIgnoreCase("hit")) {

                result = "lost";

            }


        } else {

            if (!input.equalsIgnoreCase("hit")) {

                result = "lost";

            }
        }

        return result;
    }


    public void analyzeInput() {

        String input = getInput();
        String lowerCaseInput = input.toLowerCase();
        String[] inputArray = lowerCaseInput.split(" ");


        if (lowerCaseInput.trim().isEmpty()) {


            System.out.println("I don't understand '" + input + "'");
            gameplay(currentRoom);


        } else {


            String commandWord = inputArray[0];


            if (commandWord.equals("exit") || commandWord.equals("quit")) {

                System.out.print("Exiting...");
                endTestSuite();

            } else if (commandWord.equals("go")) {

                analyzeGoInput(input);

            } else if (commandWord.equals("pickup")) {

                analyzePickupInput(input);

            } else if (commandWord.equals("use")) {

                analyzeUseInput(input);

            } else if (commandWord.equals("my")) {

                analyzeMyInput(input);

            } else {
                System.out.println("I don't understand '" + input + "'");
                gameplay(currentRoom);
            }



        }



    }





    //----------------------------------------------- HELPER METHODS TO ANALYZE INPUT ----------------------------------------------------//


    // helper method for when commandWord = "go"
    private void analyzeGoInput(String input) {

        String lowerCaseInput = input.toLowerCase();

        if (input.length() < 3) {

            System.out.println("I don't understand '" + input + "'");
            gameplay(currentRoom);

        } else {

            String directionWord = lowerCaseInput.substring(3);

            List<String> listOfDirections= urlAdventure.getDirectionNames(currentRoom);

            boolean currentRoomChange = false;

            for (int k = 0; k < listOfDirections.size(); k++) {

                if (directionWord.equalsIgnoreCase(listOfDirections.get(k))) {

                    if (urlAdventure.isEnabled(currentRoom, directionWord)) {

                        currentRoomChange = true;
                        currentRoom = urlAdventure.getDirectionRoom(currentRoom, listOfDirections.get(k));
                        gameplay(currentRoom);


                    } else {

                        System.out.println("This entrance is LOCKED. You may use one of your items to try to enter." +
                                "\nHint: you may say 'my items' to view your current items.");

                        currentRoomChange = true;
                        gameplay(currentRoom);

                    }

                }
            }

            if (!currentRoomChange) {
                System.out.println("I can't go " + input.substring(3));
                gameplay(currentRoom);
            }

        }

    }


    // helper method for when commandWord = "pickup"
    private void analyzePickupInput(String input) {
        String lowerCaseInput = input.toLowerCase();

        if (input.length() < 7) {

            System.out.println("I don't understand '" + input + "'");
            gameplay(currentRoom);

        } else {

            String item = lowerCaseInput.substring(7);

            if (urlAdventure.isPickupItem(item, currentRoom)) {

                triviaQuestion(item, currentRoom);


            } else {

                System.out.println("I cannot pick up: '" + input.substring(7) + "'");
                gameplay(currentRoom);

            }

        }


    }



    public void triviaQuestion(String item, String currentRoom) {

        System.out.println("To pickup " + item + " you must answer the following trivia question correctly: ");
        System.out.println(urlAdventure.getTrivia(item, currentRoom));

        analyzeAnswerInput(item, currentRoom);
    }



    // helper method for when user is answering a trivia question
    private void analyzeAnswerInput(String item, String currentRoom) {

        String input = getInput();
        String[] inputArray = input.split(" ");

        if (inputArray.length < 1 || inputArray.length > 2) {

            System.out.println("Sorry, '" + input + "' is not the correct answer. You may pickup the item and try again.");

        } else {

            String answer = inputArray[0];

            // answer is two words
            if (inputArray.length == 2) {

                answer += " " + inputArray[1];
            }

            if (urlAdventure.isValidTriviaAnswer(answer, item, currentRoom)) {

                System.out.println("Correct!");


                if (urlAdventure.isTransportItem(item)) {

                    System.out.println("Congratulations, you picked up a TELEPORTATION item!\nYou can now transport to any room by" +
                            " saying 'use " + item + " with (the room).'");

                    urlAdventure.addTransportItem(item);

                } else {

                    System.out.println("Picked up item: " + item);

                }

                urlAdventure.addItem(item);
                urlAdventure.removeItem(item, currentRoom);
                gameplay(currentRoom);

            } else {

                System.out.println("Sorry, '" + input + "' is not the correct answer. You may pickup the item and try again.");
                gameplay(currentRoom);

            }

        }



    }



    // helper method for when commandWord = "use"
    private void analyzeUseInput(String input) {
        String lowerCaseInput = input.toLowerCase();

        if (input.length() < 8) {

            System.out.println("I don't understand '" + input + "'");
            gameplay(currentRoom);

        } else {

            // use ITEM with DIRECTION (ITEM could be one or two words, DIRECTION could be one or two words (if it's a room))
            String[] splitBySpace = lowerCaseInput.split(" ");

            if (splitBySpace.length < 4 || splitBySpace.length > 6) {

                System.out.println("I don't understand '" + input + "'");
                gameplay(currentRoom);

            }

            // index of word "with" in the input
            int with = 0;

            for (int k = 0; k < splitBySpace.length; k++) {
                if (splitBySpace[k].equalsIgnoreCase("with")) {
                    with = k;
                }
            }

            String item = splitBySpace[1];
            String directionWord = splitBySpace[3];


            // two words in ITEM and one word in DIRECTION
            if (with == 3 && splitBySpace.length == 5) {
                item += " " + splitBySpace[2];
                directionWord = splitBySpace[4];
            }

            // two words in ITEM and two words in DIRECTION
            if (with == 3 && splitBySpace.length == 6) {
                item += " " + splitBySpace[2];
                directionWord = splitBySpace[4] + " " + splitBySpace[5];
            }

            // one word in ITEM and two words in DIRECTION
            if (with == 2 && splitBySpace.length == 5) {
                directionWord = splitBySpace[3] + " " + splitBySpace[4];
            }


            if (urlAdventure.isKeyItem(item, currentRoom, directionWord)) {


                System.out.println("You have successfully unlocked this entrance!");
                urlAdventure.unlockRoom(currentRoom, directionWord);

                currentRoom = urlAdventure.getDirectionRoom(currentRoom, directionWord);



            } else if (urlAdventure.isTransportItem(item)) {


                boolean roomChange = false;

                for (String s : urlAdventure.getAllRooms()) {


                    if (directionWord.equalsIgnoreCase(s)) {


                        System.out.println("You have successfully transported to: " + directionWord);
                        currentRoom = directionWord;
                        roomChange = true;

                    }
                }

                if (!roomChange) {

                    System.out.println("There is no such room '" + directionWord + "'");
                }


            } else {

                System.out.println("The item: '" + item + "' cannot be used to teleport to this room.");

            }

            gameplay(currentRoom);

        }

    }


    // helper method for when commandWord = "my"
    private void analyzeMyInput(String input) {
        String lowerCaseInput = input.toLowerCase();


        if (input.length() < 3) {


            System.out.println("I don't understand '" + input + "'");
            gameplay(currentRoom);


        } else {


            String string = lowerCaseInput.substring(3);

            if (string.equals("items")) {


                System.out.println("The items you currently have are: " + urlAdventure.getCurrentItems());
                analyzeInput();


            } else {


                System.out.println("I don't understand '" + input + "'");
                gameplay(currentRoom);

            }


        }


    }



    public static void main(String[] arguments) {

        Adventure adventureMain = new Adventure();

    }

}
