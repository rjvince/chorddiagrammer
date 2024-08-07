package com.rjvince;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents a Chord Diagram on a fretboard with four strings.
 */
public class ChordDiagram {
    private Note root;
    private final String chordType;
    private String name;
    private int startFret;
    private List<Integer> fingers;
    private List<String> noteNames;
    private final Note[] tuning = {Note.G, Note.C, Note.E, Note.A};
    private final Set<Note> sharpSet = Set.of(Note.G, Note.D, Note.A, Note.E, Note.B, Note.Fs);
    private boolean suppressNoteNames = false;

    public ChordDiagram(String row) {
        this(row, 0);
    }

    public ChordDiagram(String row, Integer transposeSteps) {
        String[] tokens = row.split(",");
        root = Note.valueOf(tokens[0]);
        chordType = tokens[1];
        startFret = Integer.parseInt(tokens[2].trim());

        if (transposeSteps != 0) {
            transposeChord(transposeSteps);
        }

        fingers = new ArrayList<>(4);
        for (String finger : tokens[3].trim().split("-")) {
            fingers.add(Integer.parseInt(finger));
        }

        int offset = startFret == 0 ? 0 : startFret - 1;
        noteNames = new ArrayList<>(4);
        for (int i = 0; i < fingers.size(); i++) {
            noteNames.add(formatNote(tuning[i].shift(offset + fingers.get(i))));
        }

        name = (formatNote(root) + chordType);
    }

    /**
     * Look up whether the chord should use sharps or flats. This is determined
     * by acting like the root note is the key signature.
     * Caveat: More complex chords might report a (technically) wrong name for some notes,
     * e.g.: a dominant seventh flat five chord might give the wrong name for the flat fifth if
     * the key generally uses sharps.
     *
     * @param root
     * @param chordType
     * @return true if the key would use sharp
     */
    public boolean isSharpKey(Note root, String chordType) {
        Note lookup = root;

        // Save some effort and use the relative minor
        if (chordType.startsWith("m")) {
            lookup = root.shift(3);
        }

        return sharpSet.contains(lookup);
    }

    /**
     * Pitch shift the whole chord
     *
     * @param transposeSteps
     */
    private void transposeChord(Integer transposeSteps) {
        root = root.shift(transposeSteps);
        for (int i = 0; i < tuning.length; i++) {
            tuning[i] = tuning[i].shift(transposeSteps);
        }
    }

    /**
     * Format the note name for display in SVG by converting alphabetical
     * characters to real sharps and flats.
     *
     * @param n
     * @return
     */
    private String formatNote(Note n) {
        if (isSharpKey(this.root, this.chordType)) {
            return n.useSharps().replace('s', '♯');
        } else {
            return n.useFlats().replace('f', '♭');
        }
    }

    /**
     * A string representation of the instrument tuning.
     * E.g.: G-C-E-A, D-G-B-E
     *
     * @return A string representing the tuning
     */
    public String getTuningStr() {
        StringBuilder tuningStr = new StringBuilder();
        for (int i = 0; i < tuning.length; i++) {
            tuningStr.append(formatNote(tuning[i]));
            if (i < tuning.length - 1) {
                tuningStr.append("-");
            }
        }
        return tuningStr.toString();
    }

    @Override
    public String toString() {
        String fmt = "Name: %s%nStart At: %d%nFingering: %d-%d-%d-%d%nVoice: %s";

        return String.format(fmt, this.name, this.startFret,
                this.fingers.get(0), this.fingers.get(1),
                this.fingers.get(2), this.fingers.get(3), String.join(",", this.noteNames));
    }


    //============================================================
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return name.replace('b', '♭').replace('#', '♯');
    }

    public int getStartFret() {
        return startFret;
    }

    public void setStartFret(int startFret) {
        this.startFret = startFret;
    }

    public List<Integer> getFingers() {
        return fingers;
    }

    public void setFingers(List<Integer> fingers) {
        this.fingers = fingers;
    }

    public List<String> getNoteNames() {
        return noteNames;
    }

    public void setNoteNames(List<String> noteNames) {
        this.noteNames = noteNames;
    }

    public boolean isSuppressNoteNames() {
        return suppressNoteNames;
    }

    public void setSuppressNoteNames(boolean suppressNoteNames) {
        this.suppressNoteNames = suppressNoteNames;
    }
}
