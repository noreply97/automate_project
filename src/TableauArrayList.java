import javax.swing.*;
import java.util.ArrayList;

public class TableauArrayList {

    public static void main(String[] args) {
        // Créer les ArrayLists de données
        ArrayList<String> noms = new ArrayList<>();
        noms.add("Alice");
        noms.add("Bob");
        noms.add("Charlie");

        ArrayList<Integer> ages = new ArrayList<>();
        ages.add(20);
        ages.add(25);
        ages.add(30);

        // Créer un Object[][] pour stocker les données
        Object[][] data = new Object[noms.size()][2];
        for (int i = 0; i < noms.size(); i++) {
            data[i][0] = noms.get(i);
            data[i][1] = ages.get(i);
        }

        // Créer les en-têtes du tableau
        String[] entetes = {"Nom", "Âge"};

        // Créer le JTable
        JTable table = new JTable(data, entetes);

        // Afficher le tableau dans une fenêtre
        JFrame frame = new JFrame();
        frame.add(table);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}