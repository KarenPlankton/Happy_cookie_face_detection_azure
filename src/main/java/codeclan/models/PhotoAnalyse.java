package codeclan.models;

import javax.persistence.*;

@Entity
@Table(name = "photoanalysis")
public class PhotoAnalyse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "photoFilename")
    private String photoFilename;
    @Column(name = "age")
    private double age;
    @Column(name = "anger")
    private double anger;
    @Column(name = "contempt")
    private double contempt;
    @Column(name = "disgust")
    private double disgust;
    @Column(name = "fear")
    private double fear;
    @Column(name = "happiness")
    private double happiness;
    @Column(name = "neutral")
    private double neutral;
    @Column(name = "sadness")
    private double sadness;
    @Column(name = "surprise")
    private double surprise;
    @Column(name = "gender")
    private String gender;
    @Column(name = "eyeMakeUp")
    private boolean eyeMakeUp;
    @Column(name = "lipMakeUp")
    private boolean lipMakeUp;
    @Column(name = "glasses")
    private String glasses;

    public PhotoAnalyse() {

    }

    public PhotoAnalyse(String photoFilename, double age, double anger, double contempt, double disgust, double fear,
                        double happiness, double neutral, double sadness, double surprise, String gender,
                        boolean eyeMakeUp, boolean lipMakeUp, String glasses) {
        this.photoFilename = photoFilename;
        this.age = age;
        this.anger = anger;
        this.contempt = contempt;
        this.disgust = disgust;
        this.fear = fear;
        this.happiness = happiness;
        this.neutral = neutral;
        this.sadness = sadness;
        this.surprise = surprise;
        this.gender = gender;
        this.eyeMakeUp = eyeMakeUp;
        this.lipMakeUp = lipMakeUp;
        this.glasses = glasses;

    }

    public Long getId() {
        return this.id;
    }

    public String getGlasses() {
        return glasses;
    }

    public boolean isLipMakeUp() {
        return lipMakeUp;
    }

    public boolean isEyeMakeUp() {
        return eyeMakeUp;
    }

    public String getGender() {
        return gender;
    }

    public double getSurprise() {
        return surprise;
    }

    public double getSadness() {
        return sadness;
    }

    public double getNeutral() {
        return neutral;
    }

    public double getHappiness() {
        return happiness;
    }

    public double getFear() {
        return fear;
    }

    public double getDisgust() {
        return disgust;
    }

    public double getContempt() {
        return contempt;
    }

    public double getAnger() {
        return anger;
    }

    public double getAge() {
        return age;
    }

    public String getPhotoFilename() {
        return photoFilename;
    }

    public String toString() {
        return "" + this.photoFilename
                + this.age
                + this.anger
                + this.contempt
                + this.disgust
                + this.fear
                + this.happiness
                + this.neutral
                + this.sadness
                + this.surprise
                + this.gender
                + this.eyeMakeUp
                + this.lipMakeUp
                + this.glasses;
    }
}
