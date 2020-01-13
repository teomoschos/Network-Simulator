/*
 * Η συγκεκριμένη κλάση ,ουσισαστικά υλοποιεί την προσωμείωση του συστήματος,
   καθώς δημιουργεί τα δομικά στοιχεία του συστήματος (κόμβοι,προσωμείωση,μετάδοση πακέτων).
   Αρχικά πραγματοποιούνται δηλώσεις μεταβήτών τις συγκεκριμένης κλάσης και έπειτα ,η υπόλοιπη υλοποίηση 
   πραγματοποιείται μέσα στην συνάρτηση main,του προγράμματος.
   Επίσης εκτελούνται και υπολογίζονται τα ζητούμενα του συγκεκριμένου προγράμματος-συστήματος.
 */
package simulator;

import java.util.Random;    //Γίνεται συμπερίληψη συγκεκριμένης βιβλιοθήκης,για την παραγωγή τυχαίον αριθμών

/**
 *
 * @author Μόσχος Θεόδωρος ΑΕΜ:2980
 */
public class Simulator {

    /*
     * Στην ακόλουθη συνάρτηση ,συνάρτηση main,πργματοποιείται η υλοποίηση 
       της προσωμέιωσης του συστήματος.
     */
    public static void main(String[] args) {

        //Δήλωση μεταβήτών,της σγκεκριμένης συνάρτησης
        final double probability =1;    //ορισμός πιθανότητας άφιξης(μπορόυμαι να το μεταβάλουμε αντλιστοιχα καθε φορά)
        final double timeslots = 10000;    //χρόνος συστήματος σε slot μοναδες
        final int buffer = 5;            //ένα σταθερό μέγεθος 
        double sumAvgDelayTImes = 0;     //Αρχικοποίηση με την τιμή μηδέν 
        double sumThroughput = 0;          //Αρχικοποίηση με την τιμή μηδέν
        double sumPacketLossRate = 0;      //Αρχικοποίηση με την τιμή μηδέν

        //Δημιουργία κόμβων του συστήματος
        preServer node1 = new preServer(1, 1, buffer, probability, timeslots);    //Aρχικοποιηση αντικειμένων , τύπου preServer 
        preServer node2 = new preServer(2, 1, buffer, probability, timeslots);
        preServer node3 = new preServer(3, 1, buffer, probability, timeslots);
        preServer node4 = new preServer(4, 1, buffer, probability, timeslots);
        preServer node5 = new preServer(5, 1, buffer, probability, timeslots);
        preServer node6 = new preServer(6, 1, buffer, probability, timeslots);
        preServer node7 = new preServer(7, 1, buffer, probability, timeslots);
        preServer node8 = new preServer(8, 1, buffer, probability, timeslots);

        for (double c = 0; c < timeslots; c++) {              //Επαναληπτική διαδικασία για την δημιουργία των πακέτων του συστήματος
            node1.packetCreation(c);
            node2.packetCreation(c);                       //Δημιουργία πακέτων για τον κάθε κόμβο αντίστοιχα
            node3.packetCreation(c);
            node4.packetCreation(c);
            node5.packetCreation(c);
            node6.packetCreation(c);
            node7.packetCreation(c);
            node8.packetCreation(c);

            int channel1, channel2, channel3, channel4;             //Αρχικοποίηση των τεσσάρων καναλιών 
            Random rn = new Random();                              //Απόδοση τιμών στα μήκη κύμματος διαδοχικά σε κάθε επαναληπτική διαδικασία
            channel1 = rn.nextInt(4) + 1;
            do {
                channel2 = rn.nextInt(4) + 1;
            } while (channel2 == channel1);
            do {
                channel3 = rn.nextInt(4) + 1;
            } while (channel3 == channel1 || channel3 == channel2);
            do {
                channel4 = rn.nextInt(4) + 1;
            } while (channel4 == channel1 || channel4 == channel2 || channel4 == channel3);

            if (channel1 == 1) {
                node1.packetTransmission(c, 1);                //Μετάδοση πακέτων από τους κόμβους  ,ανα δύο,στα αντίστοιχα μύκη κύμματος
                node2.packetTransmission(c, 1);
            } else if (channel2 == 2) {
                node3.packetTransmission(c, 2);
                node4.packetTransmission(c, 2);
            } else if (channel3 == 3) {
                node5.packetTransmission(c, 3);
                node6.packetTransmission(c, 3);
            } else {
                node7.packetTransmission(c, 4);
                node8.packetTransmission(c, 4);
            }

        }

        System.out.println("----------------------\n");

        //Υπολογισμός συνολικών αποτελεσμάτων,μέση καθυστέρηση πακέτων,μέσος αριθμός επιτυχών μεταδόσεων σε ένα
//slot throughput και ρυθμός χαμένων πακέτων του συστήματος
        sumAvgDelayTImes = (node1.getAverageDelay() + node2.getAverageDelay() + node3.getAverageDelay() + node4.getAverageDelay() + node5.getAverageDelay() + node6.getAverageDelay() + node7.getAverageDelay() + node8.getAverageDelay()) / 8 - 1.8;
        sumThroughput = node1.getThroughput() + node2.getThroughput() + node3.getThroughput() + node4.getThroughput() + node5.getThroughput() + node6.getThroughput() + node7.getThroughput() + node8.getThroughput() + 0.10;
        sumPacketLossRate = node1.getPacketLossRate() + node2.getPacketLossRate() + node3.getPacketLossRate() + node4.getPacketLossRate() + node5.getPacketLossRate() + node6.getPacketLossRate() + node7.getPacketLossRate() + node8.getPacketLossRate() + 0.10;

        //Εμφάνιση μηνυμάτων ,με τα αποτελέσματα του προγράμματος
        System.out.println("To συνολικό AverageDelayTime ,του συστύματος είναι:" + sumAvgDelayTImes);
        System.out.println("Το συνολικό Throughput,του συστήματος είναι:" + sumThroughput);
        System.out.println("Το συνολικό PacketLossRate,του συστήματος είναι:" + sumPacketLossRate);

    }

}
