package book.person;

import book.content.Book;
import global.ObjectID;

import java.util.ArrayList;

public class RaceSystem {

    private boolean isAFantasyStory;

    private final ArrayList<Race> my_races;

    public RaceSystem() {
        my_races = new ArrayList<>();
    }

    public boolean canChangeActivationOfSystem() {
        return !isAFantasyStory || canDeactivateActiveRaceSystem();
    }

    public void activateRaceSettings(boolean activate) {
        if(!activate && !canDeactivateActiveRaceSystem()) {
            return;
        }

        isAFantasyStory = activate;
        Book.getInstance().save();
    }
    private boolean canDeactivateActiveRaceSystem() {
        return isAFantasyStory && my_races.isEmpty();
    }

    public boolean isRaceSystemActivated() {
        return isAFantasyStory;
    }

    public ArrayList<Race> getRaces() {
        return my_races;
    }

    public void addRace(Race race) {
        my_races.add(race);
        Book.getInstance().save();
    }

    public Race getRace(ObjectID raceID) {
        for(Race race : my_races) {
            if(race.getID().equals(raceID)) {
                return race;
            }
        }
        return null;
    }
}
