import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AikidoTrackerTest {

    @Test
    void addSession() {
        AikidoTracker tracker = new AikidoTracker();
        tracker.addSession("2025-03-01", 60);
        assertEquals(1, tracker.getTotalSessions());
    }

    @Test
    void getTotalSessions() {
        AikidoTracker tracker = new AikidoTracker();
        tracker.addSession("2025-03-01", 60);
        tracker.addSession("2025-03-02", 90);
        assertEquals(2, tracker.getTotalSessions());
    }

    @Test
    void getTotalPracticeTime() {
        AikidoTracker tracker = new AikidoTracker();
        tracker.addSession("2025-03-01", 60);
        tracker.addSession("2025-03-02", 90);
        assertEquals(150, tracker.getTotalPracticeTime());
    }

    @Test
    void checkGraduationEligibilityBySessionCount() {
        AikidoTracker tracker = new AikidoTracker();
        for (int i = 0; i < 100; i++) {
            tracker.addSession("2025-03-01", 60);
        }
        assertTrue(tracker.checkGraduationEligibility());
    }

    @Test
    void checkGraduationEligibilityByDate() {
        AikidoTracker tracker = new AikidoTracker();
        tracker.setStartDate("2024-05-01");
        tracker.addSession("2025-03-01", 60);
        assertTrue(tracker.checkGraduationEligibility());
    }

    @Test
    void checkGraduationEligibilityNotMet() {
        AikidoTracker tracker = new AikidoTracker();
        tracker.addSession("2025-03-01", 60);  // 仅1次记录，不满100次，不满6个月
        assertFalse(tracker.checkGraduationEligibility());
    }
}
