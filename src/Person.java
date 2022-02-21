public abstract class Person {
    private final String gender;
    private String fullName;
    private int age;
    private String profession;
    private String preferredGender;
    private String dominantFeature;
    private int maxPreferredAge;
    private String preferredProfession;
    private String preferredDominantFeature;

    public Person(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPreferredGender() {
        return preferredGender;
    }

    public void setPreferredGender(String preferredGender) {
        this.preferredGender = preferredGender;
    }

    public String getDominantFeature() {
        return dominantFeature;
    }

    public void setDominantFeature(String dominantFeature) {
        this.dominantFeature = dominantFeature;
    }

    public int getMaxPreferredAge() {
        return maxPreferredAge;
    }

    public void setMaxPreferredAge(int maxPreferredAge) {
        this.maxPreferredAge = maxPreferredAge;
    }

    public String getPreferredProfession() {
        return preferredProfession;
    }

    public void setPreferredProfession(String preferredProfession) {
        this.preferredProfession = preferredProfession;
    }

    public String getPreferredDominantFeature() {
        return preferredDominantFeature;
    }

    public void setPreferredDominantFeature(String preferredDominantFeature) {
        this.preferredDominantFeature = preferredDominantFeature;
    }
}