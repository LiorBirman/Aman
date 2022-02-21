package Matchmaking;

public class Female extends Person {
    private String hairColor;

    public Female(){
        super("female");
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    @Override
    public String toString() {
        return "Matchmaking.Female{" +
                ", fullName='" + getFullName() + '\'' +
                ", age=" + getAge() +
                ", profession='" + getProfession() + '\'' +
                ", preferredGender='" + getPreferredGender() + '\'' +
                ", dominantFeature='" + getDominantFeature() + '\'' +
                ", maxPreferredAge=" + getMaxPreferredAge() +
                ", preferredProfession='" + getPreferredProfession() + '\'' +
                ", preferredDominantFeature='" + getPreferredDominantFeature() + '\'' +
                ", hairColor=" + hairColor +
                '}';
    }
}
