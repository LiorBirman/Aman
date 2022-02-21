package Matchmaking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Matchmaking {
    private ArrayList<Person> people;
    private ArrayList<Match> matches ;


    public Matchmaking() {
        people = new ArrayList<>();
        matches = new ArrayList<>();
    }

    public void matchMake(String filePath) throws Exception {
        // Initialize people list
        parseFile(filePath);


        int index = 0;

        for (Person p : people) {
            // Get list of current person's preferred gender
            String preferredGender = p.getPreferredGender();
            List<Person> potentialMatchesList = people.stream()
                    .filter(person -> person.getGender().
                            equals(preferredGender)).collect(Collectors.toList());

            // Increment current match score for each mutual feature & preferred feature respectively
            for (Person potentialPerson : potentialMatchesList) {
                if (!p.getFullName().equals(potentialPerson.getFullName())
                        && potentialPerson.getPreferredGender().equals(p.getGender())) {
                    matches.add(index, new Match());
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

    private void printMatches() {
        System.out.println(matches.stream()
                .sorted(Comparator.comparing(Match::getScore).reversed())
                .collect(Collectors.toList()).toString());
    }

    // This method assumes the input is correct
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

    private void addPerson(String line) {
        String[] personFeatures = line.split(" ");
        String gender = "";
        String fullName = "";
        int index = 0;

        for (String feature : personFeatures) {
            index++;
            if ((feature.equals("male") || feature.equals("female")) && gender.equals("")) {
                gender += feature;
                break;
            }

            if (gender.equals(""))
                fullName += feature + " ";
        }

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

    }
}