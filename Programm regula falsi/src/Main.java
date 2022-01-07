public class Main{

    RegulaFalsi nullstellenberechnung;

    public static void main(String[] args) {
        if (args.length == 4) {
            new Main(args);
        } else if (args.length == 0) {
        	GUI.guiStart();
        } else {
            System.out.println("Die Syntax scheint falsch angegeben worden zu sein.");
            System.out.println("Die richtige Syntax waere:");
            System.out.println("java -jar RegulaFalsi.jar \"<Funktion>\" <x0> <x1> <Iterationen>");
        }
    }

    public Main(String[] args) {
        try {
            RegulaFalsi.getRegulaFalsi(args);
        //Hier wird das Verfahren auf moegliche Exceptions Ueberprueft. 
        } catch (Exception e) {
            //Sollte beim Aufsplitten der Funktion ein Fehler passiert sein
            //wird dieser hier Aufgehalten. Vermutlich wurde in diesem Fall die Funktion falsch angegeben
            if (e.getMessage().equals("Die Funktion wurde nicht richtig angegeben")) {
                funktionFalschAngegebenException();
            } else {
                e.printStackTrace();
            }
        }
    }

    private static void funktionFalschAngegebenException() {
        System.out.println("Vermutlich wurde die Funktion falsch angegeben.");
        System.out.println("Bitte denke daran, dass diese in Anfuehrungsstriche (\"\") gesetzt werden muss.");
        System.out.println("Die Funktion muss auf folgende Art und Weise aufgebaut sein:");
        System.out.println("Jeder Summand wird durch ein + oder - von dem naechsten Summanden getrennt.");
        System.out.println("Die Summanden sind so aufgebaut:");
        System.out.println("<Vorfaktor>*x^<Exponent> - Beispiel: 3*x^5");
        System.out.println("Sollte kein x vorhanden sein, muss der Exponent als x^0 angegeben werden. - Beispiel: 5*x^0");
    }
}