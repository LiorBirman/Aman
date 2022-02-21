package Matchmaking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Matchmaking {
    private List<Person> people;
    private List<Match> matches ;


    public Matchmaking() {
        people = new ArrayList<>();
        matches = new ArrayList<>();
    }

    // Parse the csv file into a List
    // For each person, calculate match score per potential match
    public void matchMake(String filePath) throws Exception {
        // Initialize people list
        parseFile(filePath);

        int index = 0;
        for (Person p : people) {
            // Get list of current person's preferred gender
            List<Person> potentialMatchesList = people.stream()
                    .filter(person -> person.getGender().
                            equals(p.getPreferredGender())).collect(Collectors.toList());

            // Increment current match score for each mutual feature & preferred feature respectively
            for (Person potentialPerson : potentialMatchesList) {
                // Validate if current person is not potential person
                if (!p.getFullName().equals(potentialPerson.getFullName())
                        // Validate if potential person's preferred gender is current person's gender
                        && potentialPerson.getPreferredGender().equals(p.getGender())) {
                    matches.add(index, new Match());

                    // Match score calculation
                    if (p.getPreferredDominantFeature().equals(potentialPerson.getDominantFeature()))
                        matches.get(index).incrementScore();
                    if (p.getPreferredProfession().equals(potentialPerson.getProfession()))
                        matches.get(index).incrementScore();
                    if (p.getMaxPreferredAge() >= potentialPerson.getAge())
                        matches.get(index).incrementScore();
                    matches.get(index).setName_1(p.getFullName());
                    matches.get(index).setName_2(potentialPerson.getFullName());

                    index++;
                }
            }
        }

        printMatches();

    }

    // Print all stored matches sorted in ascending order by score
    private void printMatches() {
        System.out.println(matches.stream()
                .sorted(Comparator.comparing(Match::getScore).reversed())
                .collect(Collectors.toList()).toString());
    }

    // This method assumes the input is correct \\
    // Read CSV & store in a list
    private void parseFile(String filePath) throws Exception {
        // Read CSV file from 'src' folder
        BufferedReader reader = new BufferedReader(new FileReader(
                filePath));
        String line = null;
        Scanner scanner = null;

        // Read line by line
        while ((line = reader.readLine()) != null) {
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            StringBuilder currentLine = new StringBuilder();
            while (scanner.hasNext()) {
                currentLine.append(scanner.next());
            }

            // Add current person to list
            addPerson(currentLine.toString());
        }
    }

    // Parse a line of features
    // Store the correct type (male or female) into a list with the parsed features
    private void addPerson(String line) {
        String[] personFeatures = line.split(" ");
        String gender = "";
        String fullName = "";
        int index = 0;

        // index variable will be incremented for each word in full name (supports more than two words names)
        // After incrementing, index will hold the first position of non name or gender features
        // Features format after index:
        // String[] features = {
        // age, profession, dominantFeature, preferredGender, preferredMaxAge,
        // preferredProfession, preferredDominantFeature}
        for (String feature : personFeatures) {
            index++;
            if ((feature.equals("male") || feature.equals("female")) && gender.equals("")) {
                gender += feature;
                break;
            }

            if (gender.equals(""))
                fullName += feature + " ";
        }

        index = storePerson(gender, fullName, personFeatures, index);

    }

    // Create & store the correct object type to list
    private int storePerson(String gender, String fullName, String[] personFeatures, int index) {
        if (gender.equals("male")) {
            Male male = new Male();
            male.setFullName(fullName);
            male.setAge(Integer.parseInt(personFeatures[index++]));
            male.setProfession(personFeatures[index++]);
            male.setDominantFeature(personFeatures[index++]);
            male.setPreferredGender(personFeatures[index++]);
            male.setMaxPreferredAge(Integer.parseInt(personFeatures[index++]));
            male.setPreferredProfession(personFeatures[index++]);
            male.setPreferredDominantFeature(personFeatures[index++]);
            male.setHeight(Integer.parseInt(personFeatures[index++]));

            people.add(male);
        }

        else {
            Female female = new Female();
            female.setFullName(fullName);
            female.setAge(Integer.parseInt(personFeatures[index++]));
            female.setProfession(personFeatures[index++]);
            female.setDominantFeature(personFeatures[index++]);
            female.setPreferredGender(personFeatures[index++]);
            female.setMaxPreferredAge(Integer.parseInt(personFeatures[index++]));
            female.setPreferredProfession(personFeatures[index++]);
            female.setPreferredDominantFeature(personFeatures[index++]);
            female.setHairColor(personFeatures[index++]);

            people.add(female);
        }

        return index;
    }
}
