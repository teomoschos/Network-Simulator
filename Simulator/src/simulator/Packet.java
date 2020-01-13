/* Η συγκεκριμένη κλάση ,κλάση Packet,περιέχει όλες τις βασικές ιδιότητες 
   και λειτουργίες ,τις οποίες πραγματοποιεί ένα πακέτο στη συγκεκριμένη προσομείωση .
   Η κλάση αρχικά περιλαμβάνει δήλωση ορισμένων μεταβλητών ,στη συνέχεια έναν κατασκευαστή
   στον οποίον πραγματοποιούνται αρχικοποιήσεις των μεταβλητών καθώς και ορισμένες συναρτήσεις 
   ,ως προς την διαχείρηση των πακέτων.
 */
package simulator;

/**
 * @author Μόσχος Θεόδωρος ΑΕΜ:2980
 */
public class Packet {

    //Δήλωση μεταβλητών ,της κλάσης
    public double arrivalTime;      // χρόνος άφιξης ενός πακέτου
    public double departmentTime;   //χρόνος αναχώρησης ενός πακέτου
    public int NodeSorce;           //προέλευση πακέτου 
    public int NodeServer;     //προορισμός πακέτου

    //Ακολουθεί ο κατασκευαστής της κλάσης ,κατασκευαστής Packet
    public Packet(int Node, int Server) {        //Δήλωση μίας επιπλέον μεταβλητής ,μεταβλητή Node
        NodeSorce = Node;             //Αρχικοποίηση της μεταβλητής Node
        NodeServer = Server;             //Aρχικοποίηση της μεταβλητής Server

    }

    //Ακολουθούν  ορισμένες συναρτήσεις ,της σγκεκριμένης κλάσης 
    public int getDestination() {             // H συνάρτηση αυτή,μας δίνει τον κόμβο προορισμού του κάθε πακέτου  
        return NodeServer;
    }

    public void setarrivalTime(double timeslota) {  //Δήλωση μιας επιπλέον μεταβλητής ,μεταβλητή timeslot
        arrivalTime = timeslota;
                               // H συνάρτηση αυτή,πραγματοποιεί την δήλωση της  χρονικής στιγμής , κατά 
                            //την οποία φτάνει το κάθε πακέτο του δικτύου
    }

    public double getarrivalTime() {                   //Η συνάρτηση αυτή μας δίνει την χρονική στιγμή άφιξης του πακέτου
        return arrivalTime;
    }

    public void setdepartementTime(double timeslotd) {  //Δήλωση μιας επιπλέον μεταβλητής ,μεταβλητή timeslot
        departmentTime = timeslotd;
                            //H συνάρτηση αυτή ,πραγματοποιεί την δήλωση της  χρονικής στιγμής,κατά 
                         //την οποία αναχωρεί ένα πακέτο
    }

    public double getdepartmentTime() {             //Η συνάρτηση αυτή μας δίνει την χρονική στιγμή αναχώρησης ενός  πακέτου
        return departmentTime;
    }

    public double getdelayTime() {
        return (departmentTime - arrivalTime);     //H συνάρτηση αυτή,μας δίνει τον χρόνο καθυστέρησης ,ο οποίος είναι ο χρόνος 
                                         //αναχώρησης μείων τον χρόνο άφιξης 
    }
}
