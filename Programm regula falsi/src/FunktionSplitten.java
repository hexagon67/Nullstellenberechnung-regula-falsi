import java.util.ArrayList;

public class FunktionSplitten {

    private double[][] tabelle;

    public static double[][] funktionSplitten(String funktion) throws Exception {
        FunktionSplitten splitter = new FunktionSplitten(funktion);
        return splitter.getFunktion();
    }

    public FunktionSplitten (String funktion) throws Exception {
        String[] summanden;
        summanden = getSummanden(funktion);
        tabelle = SummandenToTabelle(summanden);
    }

    private String[] getSummanden(String funktion) {
        ArrayList<String> summanden = new ArrayList<String>();
        String summand = "";
        for (char character : funktion.toCharArray()) {
            if (character == '+' && !(character == funktion.toCharArray()[0])) {
                summanden.add(summand);
                summand = "+";
            } else if (character == '-' && !(character == funktion.toCharArray()[0])) {
                summanden.add(summand);
                summand = "-";
            } else if (character == ' ') {
                //Leerzeichen werden ignoriert
            } else {
                summand += character;
            }
        }
        summanden.add(summand);

        return summanden.toArray(new String[summanden.size()]);
    }

    private double[][] SummandenToTabelle(String[] summanden) throws Exception {
        double[][] tabelle = new double[summanden.length][2];
        for (int i=0; i<summanden.length; i++) {
            try {
                tabelle[i][0] = Double.parseDouble(summanden[i].split("\\*")[0]);
                tabelle[i][1] = Double.parseDouble(summanden[i].split("\\^")[1]);
            } catch (NumberFormatException e) {
                throw new Exception("Die Funktion wurde nicht richtig angegeben", e);
            }
        }
        return tabelle;
    }


    public double[][] getFunktion() {
        return tabelle;
    }
}
