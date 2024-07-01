package com.rjvince;

import java.util.ArrayList;
import java.util.List;

public class ChordDiagram {
    private Note root;
    private String chordType;
    private String name;
    private int startFret;
    private List<Integer> fingers;
    private List<String> noteNames;
    private Note[] tuning = {Note.G, Note.C, Note.E, Note.A};
    private boolean sharps;

    public ChordDiagram(String row) {
        String[] tokens = row.split(",");
        root = Note.valueOf(tokens[0]);
        chordType = tokens[1];
        startFret = Integer.parseInt(tokens[2].trim());

        fingers = new ArrayList<>(4);
        for (String finger : tokens[3].trim().split("-")) {
            fingers.add(Integer.parseInt(finger));
        }

        sharps = false;
        if (tokens.length == 5) {
            sharps = tokens[4].trim().equals("sharps");
        }

        int offset = startFret == 0 ? 0 : startFret - 1;
        noteNames = new ArrayList<>(4);
        noteNames.add(formatNote(tuning[0].shiftUp(offset + fingers.get(0))));
        noteNames.add(formatNote(tuning[1].shiftUp(offset + fingers.get(1))));
        noteNames.add(formatNote(tuning[2].shiftUp(offset + fingers.get(2))));
        noteNames.add(formatNote(tuning[3].shiftUp(offset + fingers.get(3))));

        name = formatNote(root) + chordType;
    }

    private String formatNote(Note n) {
        if (sharps) {
            return n.useSharps().replace('s', '♯');
        } else {
            return n.useFlats().replace('f', '♭');
        }
    }

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
}

// C, 0, 0-0-0-1/