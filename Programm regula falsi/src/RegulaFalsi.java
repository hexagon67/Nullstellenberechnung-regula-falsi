import java.util.ArrayList;

public class RegulaFalsi {

    private double[][] tabelle;
    private double x0;
    private double x1;
    private int iterationen;

    // Die Funktion muss auf folgende Art und Weise aufgebaut sein:
    // Jeder Summand wird durch ein + oder - von dem naechsten Summanden getrennt.
    // Die Summanden sind so aufgebaut:
    // <Vorfaktor>*x^<Exponent> - Beispiel: 3*x^5
    // Sollte kein x vorhanden sein, muss der Exponent als x^0 angegeben werden. - Beispiel: 5*x^0
    public static Object[] getRegulaFalsi(String[] args) throws Exception {
        RegulaFalsi nullstellenberechnung = new RegulaFalsi(args);
        return nullstellenberechnung.nullstelleBerechnen();
    }

    public RegulaFalsi(String[] args) throws Exception {
        tabelle = FunktionSplitten.funktionSplitten(args[0]);
        x0 = Double.parseDouble(args[1]);
        x1 = Double.parseDouble(args[2]);
        iterationen = Integer.parseInt(args[3]);

        if (x0 < x1) {
            double temp = x1;
            x1 = x0;
            x0 = temp;
        }
        
        Object[] ergebnis = nullstelleBerechnen();
        if (!(boolean)ergebnis[1]) {
            System.out.println("Die Berechnung konnte im Ramen der angegeben Iterationen nicht zu einem Ergebnis kommen.");
            System.out.println("Deswegen wird hier nur das Ergebnis der letzten Iteration wiedergeben.");
            System.out.println("Dies ist keine Nullstelle, sollte aber naeher an der Nullstelle der eigentliche Wert liegen.");
        }    
        System.out.println("Ergebnis: " + ergebnis[0]);
    }

    public static double funktionBerechnen(double x, double [][] tabelle) {
        double ergebnis = 0;
        for (double[] ls : tabelle) {
            double zwischenergebnis = 0;
            zwischenergebnis = Math.pow(x, ls[1]);
            zwischenergebnis *= ls[0];

            ergebnis += zwischenergebnis;
        }
        return ergebnis;
    }

    private Object[] nullstelleBerechnen() {
        // Hier sollen zwei Werte zurueckgegeben werden. "ergebnis[0]" enthaelt das Ergebnis und 
        //"ergebis[1]" enthaelt true, wenn die Berechnung zu einem Ende gekommen ist. Ansonnsten steht dort false.
    	ArrayList<Double[]> zwischenergebnissetemp= new ArrayList<Double[]>();
    	
        Object[] ergebnis = new Object[3]; 

        double x0 = this.x0;
        double x1 = this.x1;
        double x2 = 0;

        for (int i=0; i<iterationen; i++) {
            double tempx2;
            tempx2 = (x0*funktionBerechnen(x1, tabelle) - x1*funktionBerechnen(x0, tabelle)) / (funktionBerechnen(x1, tabelle) - funktionBerechnen(x0, tabelle));

            if (Double.isNaN(tempx2)) {
            	ergebnis[1] = true;
                break;
            } else {
                x2 = tempx2;
            }

            System.out.println(i + ". Iteration: " + "x0=" +x0 + ", x1=" + x1 + ", x2=" +x2);
            zwischenergebnissetemp.add(new Double[] {x0, x1, x2});
            x0 = x1;
            x1 = x2;
        }
        
        Double[][] zwischenergebnisse = zwischenergebnissetemp.toArray(new Double[zwischenergebnissetemp.size()][3]);
        
        ergebnis[0] = x2;
        if (ergebnis[1] == null) ergebnis[1] = false;
        ergebnis[2] = zwischenergebnisse;
        
        return ergebnis; 
    }
}
