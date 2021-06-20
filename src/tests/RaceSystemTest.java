package tests;

import book.person.Race;
import book.person.RaceSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RaceSystemTest {

/////////////////////////////////////////////////////////////////////////////

    @Test
    public void isRaceSystemActive_initiallyReturnsFalse() {
        RaceSystem subject = new RaceSystem();

        assertFalse(subject.isRaceSystemActivated());
    }

    @Test
    public void isRaceSystemActive_forActivatedSystem_returnsTrue() {
        RaceSystem subject = new RaceSystem();
        subject.activateRaceSettings(true);

        assertTrue(subject.isRaceSystemActivated());
    }

/////////////////////////////////////////////////////////////////////////////

    @Test
    public void activateRaceSettings_activateForInactive_activatesSystem() {
        RaceSystem subject = new RaceSystem();
        subject.activateRaceSettings(true);

        assertTrue(subject.isRaceSystemActivated());
    }

    @Test
    public void activateRaceSettings_deactivateForInactive_systemStaysInactive() {
        RaceSystem subject = new RaceSystem();
        subject.activateRaceSettings(false);

        assertFalse(subject.isRaceSystemActivated());
    }

    @Test
    public void activateRaceSettings_activateForActive_systemStaysActive() {
        RaceSystem subject = new RaceSystem();
        subject.activateRaceSettings(true);
        assertTrue(subject.isRaceSystemActivated());

        subject.activateRaceSettings(true);
        assertTrue(subject.isRaceSystemActivated());
    }

    @Test
    public void activateRaceSettings_deactivateForNotEmptyAndActive_deactivatesSystem() {
        RaceSystem subject = new RaceSystem();
        subject.activateRaceSettings(true);
        assertTrue(subject.isRaceSystemActivated());
        subject.addRace(new Race("MyRaceName", "No Notes"));

        subject.activateRaceSettings(false);
        assertTrue(subject.isRaceSystemActivated());
    }

    @Test
    public void activateRaceSettings_deactivateForEmptyAndActive_doesNotDeactivatesSystem() {
        RaceSystem subject = new RaceSystem();
        subject.activateRaceSettings(true);
        assertTrue(subject.isRaceSystemActivated());

        subject.activateRaceSettings(false);
        assertFalse(subject.isRaceSystemActivated());
    }

/////////////////////////////////////////////////////////////////////////////

    @Test
    public void canChangeActivationOfSystem_forInactiveSystem_returnsTrue() {
        RaceSystem subject = new RaceSystem();

        assertTrue(subject.canChangeActivationOfSystem());
    }

    @Test
    public void canChangeActivationOfSystem_forActiveAndNotEmptySystem_returnsFalse() {
        RaceSystem subject = new RaceSystem();
        subject.activateRaceSettings(true);
        subject.addRace(new Race("MyRaceName", "No Notes"));

        assertFalse(subject.canChangeActivationOfSystem());
    }

    @Test
    public void canChangeActivationOfSystem_forActiveAndEmptySystem_returnsTrue() {
        RaceSystem subject = new RaceSystem();
        subject.activateRaceSettings(true);

        assertTrue(subject.canChangeActivationOfSystem());
    }
}
