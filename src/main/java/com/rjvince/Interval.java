package com.rjvince;

public enum Interval {
    UNISON(0, "unison"), // 1
    MINOR_SECOND(1, "minor second"), // 2
    MAJOR_SECOND(2, "major second"), // 3
    MINOR_THIRD(3, "minor third"), // 5
    MAJOR_THIRD(4, "major third"), // 7
    PERFECT_FOURTH(5, "perfect fourth"), // 11
    TRITONE(6, "tritone"), // 13
    PERFECT_FIFTH(7, "perfect fifth"), // 17
    MINOR_SIXTH(8, "minor sixth"), // 19
    MAJOR_SIXTH(9, "major sixth"), // 23
    MINOR_SEVENTH(10, "minor seventh"), // 29
    MAJOR_SEVENTH(11, "major seventh"), // 31
    OCTAVE(12, "octave"); // 1

    // Jotting this down:
    // If we assign 1 to unison and octave, and prime numbers to the other intervals,
    // chords can be looked up as a product of primes regardless of order
    //
    // Major Chord = MAJOR_THIRD:7 * PERFECT_FIFTH:17 = 119
    // Minor Chord = MINOR_THIRD:3 * PERFECT_FIFTH:17 = 51
    // Dominant 7th = MAJOR_THIRD:7 * PERFECT_FIFTH:17 * MINOR_SEVENTH:29 = 3451
    // Sus4 = PERFECT_FOURTH:5 * PERFECT_FIFTH:17 = 85
    // Add9 = MAJOR_THIRD:7 * PERFECT_FIFTH:17 * MAJOR_SECOND:3 = 357
    //
    // Notes -> Chord identification:
    //   - subtract to find all intervals
    //   - multiply prime factors
    //   - match result
    //   - unison and octaves won't matter (they are 1s)
    //      -- but doubles will, we should call these something else anyway

    private final int steps;
    private String name;

    Interval(int steps, String name) {
        this.steps = steps;
    }

    Note findFrom(Note n) {
        return n.shift(this.steps);
    }

    Interval findByValue(int steps) {
        for (Interval i : values()) {
            if (i.steps == steps) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid interval value: " + steps);
    }
}
