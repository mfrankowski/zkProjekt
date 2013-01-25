package zkProjekt;

import java.util.ArrayList;
import java.util.List;

import zkProjekt.Samochod;

public class SamochodyData {
	private static List<Samochod> allSamochody = new ArrayList<Samochod>();
    public static List<Samochod> getAllSamochody() {
        return allSamochody;
    }
}