package app.br.psicontroleapi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SessionStatusTest {

    /**
     * Tests for the {@link SessionStatus#canTransitionTo(SessionStatus)} method.
     * This method checks if a session status can transition to a given target status
     * based on the allowedNext rules.
     */

    @Test
    void testCanTransitionFromScheduledToInProgress() {
        // Arrange
        SessionStatus initialStatus = SessionStatus.SCHEDULED;
        SessionStatus targetStatus = SessionStatus.IN_PROGRESS;

        // Act
        boolean canTransition = initialStatus.canTransitionTo(targetStatus);

        // Assert
        assertTrue(canTransition, "SCHEDULED should be able to transition to IN_PROGRESS.");
    }

    @Test
    void testCanTransitionFromScheduledToRescheduled() {
        // Arrange
        SessionStatus initialStatus = SessionStatus.SCHEDULED;
        SessionStatus targetStatus = SessionStatus.RESCHEDULED;

        // Act
        boolean canTransition = initialStatus.canTransitionTo(targetStatus);

        // Assert
        assertTrue(canTransition, "SCHEDULED should be able to transition to RESCHEDULED.");
    }

    @Test
    void testCanTransitionFromScheduledToCancelled() {
        // Arrange
        SessionStatus initialStatus = SessionStatus.SCHEDULED;
        SessionStatus targetStatus = SessionStatus.CANCELLED;

        // Act
        boolean canTransition = initialStatus.canTransitionTo(targetStatus);

        // Assert
        assertTrue(canTransition, "SCHEDULED should be able to transition to CANCELLED.");
    }

    @Test
    void testCannotTransitionFromScheduledToFinished() {
        // Arrange
        SessionStatus initialStatus = SessionStatus.SCHEDULED;
        SessionStatus targetStatus = SessionStatus.FINISHED;

        // Act
        boolean canTransition = initialStatus.canTransitionTo(targetStatus);

        // Assert
        assertFalse(canTransition, "SCHEDULED should not be able to transition to FINISHED.");
    }

    @Test
    void testCanTransitionFromRescheduledToScheduled() {
        // Arrange
        SessionStatus initialStatus = SessionStatus.RESCHEDULED;
        SessionStatus targetStatus = SessionStatus.SCHEDULED;

        // Act
        boolean canTransition = initialStatus.canTransitionTo(targetStatus);

        // Assert
        assertTrue(canTransition, "RESCHEDULED should be able to transition to SCHEDULED.");
    }

    @Test
    void testCannotTransitionFromRescheduledToFinished() {
        // Arrange
        SessionStatus initialStatus = SessionStatus.RESCHEDULED;
        SessionStatus targetStatus = SessionStatus.FINISHED;

        // Act
        boolean canTransition = initialStatus.canTransitionTo(targetStatus);

        // Assert
        assertFalse(canTransition, "RESCHEDULED should not be able to transition to FINISHED.");
    }

    @Test
    void testCanTransitionFromInProgressToFinished() {
        // Arrange
        SessionStatus initialStatus = SessionStatus.IN_PROGRESS;
        SessionStatus targetStatus = SessionStatus.FINISHED;

        // Act
        boolean canTransition = initialStatus.canTransitionTo(targetStatus);

        // Assert
        assertTrue(canTransition, "IN_PROGRESS should be able to transition to FINISHED.");
    }

    @Test
    void testCannotTransitionFromInProgressToScheduled() {
        // Arrange
        SessionStatus initialStatus = SessionStatus.IN_PROGRESS;
        SessionStatus targetStatus = SessionStatus.SCHEDULED;

        // Act
        boolean canTransition = initialStatus.canTransitionTo(targetStatus);

        // Assert
        assertFalse(canTransition, "IN_PROGRESS should not be able to transition to SCHEDULED.");
    }

    @Test
    void testCannotTransitionFromFinishedToAnyOtherStatus() {
        // Arrange
        SessionStatus initialStatus = SessionStatus.FINISHED;

        // Act & Assert
        for (SessionStatus targetStatus : SessionStatus.values()) {
            boolean canTransition = initialStatus.canTransitionTo(targetStatus);
            assertFalse(canTransition, "FINISHED should not be able to transition to any other status.");
        }
    }

    @Test
    void testCannotTransitionFromCancelledToAnyOtherStatus() {
        // Arrange
        SessionStatus initialStatus = SessionStatus.CANCELLED;

        // Act & Assert
        for (SessionStatus targetStatus : SessionStatus.values()) {
            boolean canTransition = initialStatus.canTransitionTo(targetStatus);
            assertFalse(canTransition, "CANCELLED should not be able to transition to any other status.");
        }
    }

    @Test
    void testCannotTransitionFromNoShowToAnyOtherStatus() {
        // Arrange
        SessionStatus initialStatus = SessionStatus.NO_SHOW;

        // Act & Assert
        for (SessionStatus targetStatus : SessionStatus.values()) {
            boolean canTransition = initialStatus.canTransitionTo(targetStatus);
            assertFalse(canTransition, "NO_SHOW should not be able to transition to any other status.");
        }
    }
}