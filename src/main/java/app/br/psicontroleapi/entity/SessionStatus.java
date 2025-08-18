package app.br.psicontroleapi.entity;

import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Enum representing the various statuses a session can be in.
 * <p>
 * Each status is defined with a unique code, a description, and a terminal state indicator.
 * It also supports transitions between certain statuses based on predefined allowed transitions.
 */
public enum SessionStatus {

    SCHEDULED("SCHEDULED", "Scheduled – waiting to start", false),
    IN_PROGRESS("IN_PROGRESS", "Session in progress", false),
    FINISHED("FINISHED", "Session finished", true),
    CANCELLED("CANCELLED", "Session was cancelled", true),
    RESCHEDULED("RESCHEDULED", "Session was rescheduled", false),
    NO_SHOW("NO_SHOW", "Client dit not attend", true);

    private final String code;
    private final String description;
    private final boolean terminal;
    private EnumSet<SessionStatus> allowedNext;   // populated in static block

    SessionStatus(String code, String description, boolean terminal) {
        this.code = code;
        this.description = description;
        this.terminal = terminal;
    }

    /**
     * Map <code> -> enum constant for fast lookup (e.g. when reading from DB).
     */
    private static final Map<String, SessionStatus> BY_CODE =
            EnumSet.allOf(SessionStatus.class)
                    .stream()
                    .collect(Collectors.toUnmodifiableMap(SessionStatus::getCode, s -> s));

    static {
        SCHEDULED.allowedNext = EnumSet.of(IN_PROGRESS, RESCHEDULED, CANCELLED);
        RESCHEDULED.allowedNext = EnumSet.of(SCHEDULED, CANCELLED);
        IN_PROGRESS.allowedNext = EnumSet.of(FINISHED, CANCELLED);
        FINISHED.allowedNext = EnumSet.noneOf(SessionStatus.class);
        CANCELLED.allowedNext = EnumSet.noneOf(SessionStatus.class);
        NO_SHOW.allowedNext = EnumSet.noneOf(SessionStatus.class);
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public EnumSet<SessionStatus> allowedNext() {
        return allowedNext;
    }

    /**
     * Returns {@code true} if the current status is allowed to transition to {@code target}.
     */
    public boolean canTransitionTo(SessionStatus target) {
        return allowedNext.contains(target);
    }

    /**
     * Resolve an enum constant by its persisted code.
     *
     * @throws IllegalArgumentException if the code is unknown
     */
    public static SessionStatus fromCode(String code) {
        SessionStatus status = BY_CODE.get(code);
        if (status == null) {
            throw new IllegalArgumentException("Unknown SessionStatus code: " + code);
        }
        return status;
    }
}
