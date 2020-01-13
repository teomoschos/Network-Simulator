 /*
 Η συγκεκριμένη κλάση,κλάση preServer,περιλαμβάνει όλες τις ιδιότητες 
 των κόμβων ,οπου ουσιατικά γίνεται η δημιουργία των  πακέτων ,η ανάθεση
 των κόμβων στα κανάλια μετάδοσης και η γενικότερη μετάδοση των πακέτων σε αυτά.
 Περιλαμβάνει δήλωση μεταβλήτων αρχικοποίηση αυτών σε έναν κατασευαστή 
 και συναρτήσεις διαχείρησης της ροής των διαφόρων πακέτων του συστήματος.
 */
package simulator;

import java.util.ArrayList;         //Γίνεται συμπερίληψη συγκεκριμένης βιβλιοθήκης,για χρήση δομής arraylist
import java.util.Random;            //Γίνεται συμπερίληψη συγκεκριμένης βιβλιοθήκης,για την παραγωγή τυχαίον αριθμών

/**
 *
 * @author Μόσχος Θεόδωρος ΑΕΜ:2980
 */
//Δήλωση μεταβλητών ,της κλάσης
public class preServer {

    public double probability;         // Εκφράζει την πιθανότητα δημιουργίας ενός πακέτου
    public ArrayList<Packet> queqe;    // Ουρά αναμονής πακέτων 
    public Packet packet;              // Μία μεταβλητή πακέτου
    public int node;                   //Μετβλητή που εκφράζει έναν κόμβο
    public int server;                 //Mεταβλητή που εκφράζει τον server
    public int queqeSize;              //Μέγεθος της ουράς αναμονής των πακέτων 
    public double timeSlot;            // Χρόνος του κάθε slot -πακέτου
    public double timeDelay;           // Χρόνος καθυστέρησης των πακέτων
    public double counterP;              //Μετρητής επιτυχών σταλμένων πακέτων
    public double counterLost;            //Μετρητής μη  εσταλμένων πακέτων
    public double counterCretaed;          //Μετρτητής πακέτων που δημιουργήθηκαν συνολικά
    public double sum;                 //Αθροισμα συνολικών πακέτων που έχουν δημιουργηθεί

    //Κατασκευαστής της συγκεκρμένης κλάσης,κατασκευαστής preServer
    public preServer(int node, int server, int size, double prob, double timeS) {    //Δήλωση μεταβλητών,n,size,prob και timeS
        queqe = new ArrayList<>();       //Aρχικοποίηση της ουράς αναμονής πακέτων
        this.node = node;                   //Aρχικοποίηση του κάθε κόμβου
        this.server = server;                //Aρχικοποίηση του server
        this.probability = prob;         //Aρχικοποίηση της πιθανότητας δημιουργίας πακέτου
        this.timeSlot = timeS;           //Aρχικοποίηση της χρονικής μονάδας slot
        queqeSize = size;                //Aρχικοποίηση του μεγέθους της ουράς αναμονής
        sum = 0;                         //Aρχικοποίηση αθροίσμαοτς ως μηδέν
        counterP = 0;                    //Aρχικοποίηση αρχικοποίηση μετρητή επιτυχών πακέτων ως μηδέν
        timeDelay = 0;                   //Aρχικοποίηση χρόνου καθυστέρησης ως μηδέν
        counterLost = 0;                 //Aρχικοποίηση μετρητή  μη επιτυχών πακέτων ως μηδέν 
    }

    //Η συγκεκριμένη συνάρτηση ,packetCreation,είναι υπεύθυνη για την δημιουργία και την 
    //υποδοχή πακέτων στους κόμβους
    //Ακολουθούν  ορισμένες συναρτήσεις ,της σγκεκριμένης κλάσης
    public void packetCreation(double counterS) {   //Αρχικοποίηση της μεταβλητής counterS
        Random random = new Random();                     //Aρχικοποίηση ενός random τύπου αντικείμενο,που εκφράζει πιθανότητα μετάδοσης
        double randP = random.nextDouble();
        if (randP < probability && queqe.size() < queqeSize) {
            Packet packet = new Packet(node, server);                  //Πραγματοποιείται συνθήκη ώστε να διαπηστώσουμε εάν πληρούνται οι 
            packet.setarrivalTime(counterS);               //προυποθέσεις για την  προσθήκη ενός πακέτου στην ουρά και εάν ναι,τότε
            queqe.add(packet);                             //το πακέτο πρστίθεται στην ουρά,δηλαδή στη  δομή λίστας  που έχει οριστεί
            counterCretaed += 1;
        } else {
            counterLost += 1;
            System.out.println("The buffer is full!!");    //Σε διαφορετική περίπτωση το πακέτο δεν εισέλχεται στην ουρά και εμφανίζεται σχετικό μήνυμα

        }
    }

    //Η συγκεκριμένη συνάρτηση,packetTransmission,ορίζει ανα 2 τους σταθμόυς να μεταδίδουν στα 
    //αντίσροιχα 4 μήκη κύματος-κανάλια που διαθέτει το σύστημα  
    public void packetTransmission(double countS, int wavelenght) {   //Αρχικοποίηση των μεταβλητών  countS και wavelenght
        //Πραγματοποιούνται διαδοχικά εντολές συνθήκης για να καθορισεί το κανάλι μετάδοσης
        for (int i = 0; i < queqe.size(); i++) {
            if (wavelenght == 1) {       //Αναφορά στο πρώτο κανάλι
                if (node == 1 || node == 2) {      //Αντιστοίχηση των κόμβων στο κανάλι μεταδοσης
                    queqe.get(i).setdepartementTime(countS + 1);
                    timeDelay = queqe.get(i).getdelayTime() + timeDelay;
                    counterP += 1;                                              //Διαδικασίες ορισμού χρόνων αναχώρηησης ,καθυστέρησης,
                    queqe.remove(i);                                           //άυξηση του μετρητή επιτυχώς μεταδιδώμενου πακέτου και αφαίρεση του
                    break;                                               //απο τη  ουρά αναμονής
                } else {
                    System.out.println("Both stations are trying to transmitt,Collision!!");

                    //εμφάνιση σχετικού μηνύματος
                }

            }
            if (wavelenght == 2) {                       //Αναφορά στο δέυτερο κανάλι

                if (node == 3 || node == 4) {    //Αντιστοίχηση των κόμβων στο κανάλι μεταδοσης
                    queqe.get(i).setdepartementTime(countS + 1);
                    timeDelay = queqe.get(i).getdelayTime() + timeDelay;           //Διαδικασίες ορισμού χρόνων αναχώρηησης ,καθυστέρησης,
                    counterP += 1;                             //άυξηση του μετρητή επιτυχώς μεταδιδώμενου πακέτου και αφαίρεση του
                    queqe.remove(i);                                 //απο τη  ουρά αναμονής
                    break;
                } else {
                    System.out.println("Both stations are trying to transmitt,Collision!!");

                    //εμφάνιση σχετικού μηνύματος
                }

            }
            if (wavelenght == 3) {               //Αναφορά στο τρίτο κανάλι

                if (node == 5 || node == 6) {  //Αντιστοίχηση των κόμβων στο κανάλι μεταδοσης
                    queqe.get(i).setdepartementTime(countS + 1);
                    timeDelay = queqe.get(i).getdelayTime() + timeDelay;                   //Διαδικασίες ορισμού χρόνων αναχώρηησης ,καθυστέρησης,
                    counterP += 1;                                         //άυξηση του μετρητή επιτυχώς μεταδιδώμενου πακέτου και αφαίρεση του
                    queqe.remove(i);                                       //απο τη  ουρά αναμονής
                    break;
                } else {
                    System.out.println("Both stations are trying to transmitt,Collision!!");

                    // εμφάνιση σχετικού μηνύματος
                }

            }
            if (wavelenght == 4) {                    //Αναφορά στο τέταρτο κανάλι  

                if (node == 7 || node == 8) {      //Αντιστοίχηση των κόμβων στο κανάλι μεταδοσης
                    queqe.get(i).setdepartementTime(countS + 1);
                    timeDelay = queqe.get(i).getdelayTime() + timeDelay;                  //Διαδικασίες ορισμού χρόνων αναχώρηησης ,καθυστέρησης,
                    counterP += 1;                                       //άυξηση του μετρητή επιτυχώς μεταδιδώμενου πακέτου και αφαίρεση του
                    queqe.remove(i);                                       //απο τη  ουρά αναμονής
                    break;
                } else {
                    System.out.println("Both stations are trying to transmitt,Collision!!");

                    //εμφάνιση σχετικού μηνύματος
                }

            }
        }
    }

    public double gettimeDelay() {
        //Η συγκεκριμένη συνάρτηση μας επιστρέφει τον χρόνο καθυστέρησης
        return timeDelay;
    }

    public double getAverageDelay() {     //Η συνάρτηση αυτή μας δίνι την μέση καθυστέρηση των πακέτων του συστήματος
        return (timeDelay / counterP);
    }

    public double getThroughput() {     //Η συγκεκριμένη συνάρτηση μας δίνει τον  αριθμό επιτυχών μεταδόσεων σε ένα
        //slot throughput
        return (counterP / timeSlot);
    }

    public double getPacketLossRate() {          //Η συνάρτηση αυτή μας δίνει τον ρυθμό χαμένων πακέτων
        return (counterLost / counterCretaed);
    }

}
