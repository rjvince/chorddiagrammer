# Ukulele Chord Diagrammer

Generates SVG diagrams of ukulele chords.

It doesn't calculate the chords themselves, but reads an input file of text notation and generates the SVG. The reason for this being that it lets me
do things like calling 2-0-2-0 a D7 even though there's no D in it (this is frequently preferred on ukulele).

It will, however, tell you what the notes are. I think this is really helpful for learning the fretboard, so it's
switched on by default.

# Operation

Run with:
```
$ java -Dconsole.encoding=UTF-8 -Dfile.encoding=UTF-8 -jar <path to chorddiagrammer.jar> <path to input file>
```

So I don't forget, those encoding arguments are needed if we see '?' instead of sharps and flats.

### Options
```
usage: chord-diagrammer <input-file>
 -c,--clean                 clean output directory first - as in delete
                            everything
 -h,--help                  print this message
 -s,--suppress-note-names   suppress appearance of note names under
                            diagram
 -t,--transpose <arg>       transpose <number of semitones>
 -v,--verbose               verbose mode
```

# File Format
The input file format is:
```
Chord Root, Chord Type (blank for normal major chords), Starting Position, Fingering in 0-0-0-0

Important: Chord Type does a case-sensitive check for 'm' to determine if a chord is minor.
So Maj7 chords must start with that capital M, otherwise the sharp-flat detection will go haywire.
```

Some examples:
```
C,,0,0-0-0-3
G,7,0,0-2-1-2
A,m,0,1-0-0-0
```
For chords that are further up the fretboard, you can specify a starting fret. The fingering is then relative to that.

For example, the 5-5-5-7 variant FMaj7 can be defined as:
```
F,Maj7,5,1-1-1-3
```

# Alternate Tunings
The default tuning is G-C-E-A, which is pretty much standard at my location in spacetime. The `-t | --transpose` argument
can shift the tuning by a number of semitones.

Some examples:
```
// Baritone (D-G-B-E)
java -Dconsole.encoding=UTF-8 -Dfile.encoding=UTF-8 -jar chorddiagrammer.jar -t -5

// D-Tuning (A-D-F#-B)
java -Dconsole.encoding=UTF-8 -Dfile.encoding=UTF-8 -jar chorddiagrammer.jar -t 2
```

# Implementation Notes and Gotchas

Using `startswith("m")` for minor chords isn't great, but it is at least conventional.

The sharps/flats detection isn't very smart. It does its best based on the root note of the chord. This means that
something like E7♭5 is going to show an A♯ instead of a B♭. Until there's a fix for this, at least the SVG
files are easily editable in a text editor.

The [Alata](https://fonts.google.com/specimen/Alata) font is currently hardcoded. I like the way it looks and the SVG template was created with it in mind. If you don't have it, I can't say what will happen.

There are no fancy SVG libs being used here. Instead I used Inkscape to create an SVG with all the elements I would need, and then I made a FreeMarker template out of that. This may not be very elegant, but it had the key advantage
of letting me easily get things to look *exactly* the way I wanted. The implementation time this saved me was wisely spent playing ukulele. If you decide to choose this sort of approach in a project, I highly recommend using
Inkscape and saving as "Plain SVG" when you do it. I also found that Inkscape produced a much more human-friendly document than Serif Affinity v1; though I haven't looked at the output from v2 yet.
