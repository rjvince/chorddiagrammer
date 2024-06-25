package com.rjvince;

import java.util.ArrayList;
import java.util.List;

public class ChordDiagram {
    private String name;
    private int startFret;
    private List<Integer> fingers;
    private List<String> noteNames;
    private Note [] tuning = {Note.G, Note.C, Note.E, Note.A};
    private boolean sharps;
    private String fontFamily = "'Alata-Regular', 'Alata'";

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }


    public ChordDiagram(String row) {
        String [] tokens = row.split(",");
        name = tokens[0].trim();
        startFret = Integer.parseInt(tokens[1].trim());

        fingers = new ArrayList<>(4);
        for( String finger : tokens[2].trim().split("-")) {
            fingers.add(Integer.parseInt(finger));
        }

        sharps = false;
        if (tokens.length == 4) {
            sharps = tokens[3].trim().equals("sharps");
        }

        int offset = startFret == 0 ? 0 : startFret - 1;
        noteNames = new ArrayList<>(4);
        noteNames.add(formatNote(tuning[0].shiftUp(offset + fingers.get(0))));
        noteNames.add(formatNote(tuning[1].shiftUp(offset + fingers.get(1))));
        noteNames.add(formatNote(tuning[2].shiftUp(offset + fingers.get(2))));
        noteNames.add(formatNote(tuning[3].shiftUp(offset + fingers.get(3))));
    }

    private String formatNote(Note n) {
        if (sharps) {
            return n.useSharps().replace('s', '♯');
        }
        else {
            return n.useFlats().replace('f', '♭');
        }
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