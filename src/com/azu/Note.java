package com.azu;

public class Note {
    public double freq;
    public double octave;
    public double toneNumber;

    public Note(double octave, double toneNumber) {
        this.octave = octave;
        this.toneNumber = toneNumber;
        freq = calcFreq(octave, toneNumber);
    }

    //A4
    public static double refNote = 440;
    public static double refNoteOctave = 4;
    public static double refNoteToneNumber = 9;

    public static double calcFreq(double octave, double toneNumber) {
        double a = Math.pow(2, 1d / 12d);
        double stepsToRef = getStepsToRef(octave, toneNumber);
        double freq = refNote * Math.pow(a, stepsToRef);
        return freq;
    }

    private static double getStepsToRef(double octave, double toneNumber) {
        double refTone = refNoteOctave * 12 + refNoteToneNumber;
        double tone = octave * 12 + toneNumber;
        return tone  - refTone;
    }

    public static Note getNoteByName(String name) {
        int octaveNumber = Integer.parseInt(name.replaceAll("[a-zA-Z#]", ""));
        String noteName = name.replaceAll("[0-9]", "").toUpperCase();

        int toneNumber = 0;
        switch (noteName) {
            case "A":
                toneNumber = 9;
                break;
            case "BB":
                toneNumber = 10;
                break;
            case "B":
                toneNumber = 11;
                break;
            case "C":
                toneNumber = 0;
                break;
            case "DB":
                toneNumber = 1;
                break;
            case "D":
                toneNumber = 2;
                break;
            case "EB":
                toneNumber = 3;
                break;
            case "E":
                toneNumber = 4;
                break;
            case "F":
                toneNumber = 5;
                break;
            case "GB":
                toneNumber = 6;
                break;
            case "G":
                toneNumber = 7;
                break;
            case "AB":
                toneNumber = 8;
                break;

        }
        return new Note(octaveNumber, toneNumber);

    }
}
