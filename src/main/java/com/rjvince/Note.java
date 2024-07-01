package com.rjvince;

public enum Note {
    C(0), Df(1), D(2), Ef(3), E(4),
    F(5), Fs(6), G(7), Af(8), A(9),
    Bf(10), B(11);

    private int index;

    Note(int x) {
        this.index = x % 12;
    }

    public Note shift(int x) {
        return values()[Math.floorMod(this.index + x, 12)];
    }

    public String useSharps() {
        String ret;
        switch(this) {
            case Df:
                ret = "Cs";
                break;
            case Ef:
                ret = "Ds";
                break;
            case Af:
                ret = "Gs";
                break;
            case Bf:
                ret = "As";
                break;
            default:
                ret = this.name();
        }
        return ret;
    }

    public String useFlats() {
        if (this == Fs) {
            return "Gf";
        }
        else {
            return this.name();
        }
    }
}
