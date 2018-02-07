import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

/**
 * Class die de GUI creeërt.
 */
public class VirusGui extends JFrame implements ActionListener {
    private JTextField tekstfieldBestand;
    private JLabel labelBestand, labelDropDownViral, labelDropDownHost, labelTextAreaVirusLijst, labelTextAreaVirusLijst1, labelDropDownSortering, labelTextAreaOvereenkomst;
    private JComboBox dropDownViral, dropDownHost, dropDownHost1, dropDownSortering;
    private JButton buttonSearch, buttonOpen, buttonVirusId, buttonViral;
    private JTextArea textAreaVirusLijst, textAreaVirusLijst1, textAreaOvereenkomst;
    private JScrollPane scrollPane, scrollPane1, scrollPane2;
    JFileChooser fileChooser;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        VirusGui frame = new VirusGui();
        frame.getContentPane();
        frame.setSize(800, 500);
        frame.setTitle("Virus applicatie");
        frame.createGUI();
        frame.setVisible(true);
    }

    /**
     * Eigen gemaakte GUI.
     */
    public void createGUI() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container window = this.getContentPane();
        setLocationRelativeTo(null);
        window.setLayout(null);

        tekstfieldBestand = new JTextField();
        tekstfieldBestand.setBounds(200, 30, 200, 20);
        labelBestand = new JLabel("File of URL");
        labelBestand.setBounds(50, 30, 150, 20);
        window.add(labelBestand);
        window.add(tekstfieldBestand);

        buttonSearch = new JButton("Search");
        buttonSearch.setBounds(420, 30, 100, 20);
        buttonSearch.addActionListener(this);
        window.add(buttonSearch);

        buttonOpen = new JButton("Open");
        buttonOpen.setBounds(540, 30, 100, 20);
        buttonOpen.addActionListener(this);
        window.add(buttonOpen);

        buttonVirusId = new JButton("Show virus Id's");
        buttonVirusId.setBounds(630, 120, 120, 20);
        buttonVirusId.addActionListener(this);
        window.add(buttonVirusId);

        buttonViral = new JButton("Select classification");
        buttonViral.setBounds(420, 60, 170, 20);
        buttonViral.addActionListener(this);
        window.add(buttonViral);

        dropDownViral = new JComboBox();
        dropDownViral.setBounds(200, 60, 200, 20);
        dropDownViral.addActionListener(this);
        labelDropDownViral = new JLabel("Viral Classification");
        labelDropDownViral.setBounds(50, 60, 120, 20);
        window.add(dropDownViral);
        window.add((labelDropDownViral));

        String[] dropListSortering = {"Id", "Classification", "Aantal hosts"};
        dropDownSortering = new JComboBox(dropListSortering);
        dropDownSortering.setBounds(30, 150, 120, 20);
        labelDropDownSortering = new JLabel("Sortering");
        labelDropDownSortering.setBounds(30, 130, 120, 20);
        dropDownSortering.addActionListener(this);
        window.add(dropDownSortering);
        window.add(labelDropDownSortering);

        dropDownHost = new JComboBox();
        dropDownHost1 = new JComboBox();
        dropDownHost.setBounds(200, 90, 200, 20);
        dropDownHost.addActionListener(this);
        dropDownHost1.setBounds(420, 90, 200, 20);
        dropDownHost1.addActionListener(this);
        labelDropDownHost = new JLabel("Host ID");
        labelDropDownHost.setBounds(50, 90, 100, 20);
        window.add(dropDownHost);
        window.add(dropDownHost1);
        window.add((labelDropDownHost));

        textAreaVirusLijst = new JTextArea();
        textAreaVirusLijst1 = new JTextArea();
        textAreaVirusLijst.setBounds(200, 150, 200, 100);
        textAreaVirusLijst1.setBounds(420, 150, 200, 100);
        labelTextAreaVirusLijst = new JLabel("VirusLijst");
        labelTextAreaVirusLijst.setBounds(200, 130, 200, 20);
        labelTextAreaVirusLijst1 = new JLabel("VirusLijst");
        labelTextAreaVirusLijst1.setBounds(420, 130, 200, 20);

        scrollPane = new JScrollPane(textAreaVirusLijst);
        scrollPane.setBounds(200, 150, 200, 100);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane1 = new JScrollPane(textAreaVirusLijst1);
        scrollPane1.setBounds(420, 150, 200, 100);
        scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        window.add(scrollPane);
        window.add(scrollPane1);

        window.add(labelTextAreaVirusLijst);
        window.add(labelTextAreaVirusLijst1);

        textAreaOvereenkomst = new JTextArea();
        textAreaOvereenkomst.setBounds(300, 300, 220, 100);
        labelTextAreaOvereenkomst = new JLabel("Overeenkomst");
        labelTextAreaOvereenkomst.setBounds(300, 280, 220, 20);

        scrollPane2 = new JScrollPane(textAreaOvereenkomst);
        scrollPane2.setBounds(300, 300, 220, 100);
        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        window.add(scrollPane2);

        window.add(labelTextAreaOvereenkomst);
    }

    /**\
     * voert een actie uit door op de button te klikken
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        int Path;
        File selectedFile;

        if (event.getSource() == buttonSearch) {
            fileChooser = new JFileChooser();
            Path = fileChooser.showSaveDialog(this);
            if (Path == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                tekstfieldBestand.setText(selectedFile.getAbsolutePath());
            }
        }

        if (event.getSource() == buttonOpen) {
            VirusLogica zoekId = new VirusLogica(tekstfieldBestand.getText());
            Map<Object, Object> viralMap = zoekId.getViralMap();

            dropDownViral.addItem("none");
            for (Map.Entry<Object, Object> inter : viralMap.entrySet()) {
                dropDownViral.addItem(inter.getKey());
            }
        }

        if (event.getSource() == buttonViral) {
            Map<Object, Object> viralMap = VirusLogica.getViralMap();
            Map<Object, Object> map = VirusLogica.getMap();

            dropDownHost.removeAllItems();
            dropDownHost1.removeAllItems();
            if (dropDownViral.getSelectedItem().equals("none")) {
                for (Map.Entry<Object, Object> inter : map.entrySet()) {
                    dropDownHost.addItem(inter);
                    dropDownHost1.addItem(inter);
                }
            } else {
                for (Map.Entry<Object, Object> inter : viralMap.entrySet()) {
                    dropDownViral.addItem(inter.getKey());
                    if (dropDownViral.getSelectedItem().equals(inter.getKey())) {
                        String[] value2 = inter.getValue().toString().split(";");
                        for (Map.Entry<Object, Object> inter1 : map.entrySet()) {
                            for (int i = 0; i < value2.length; i++) {
                                if (value2[i].equals(inter1.getKey())) {
                                    dropDownHost.addItem(inter1);
                                    dropDownHost1.addItem(inter1);
                                }
                            }
                        }
                    }
                }
            }
        }

        if (event.getSource() == buttonVirusId) {
            Map<Object, Object> virusInfo = VirusLogica.getVirusInfo();
            textAreaVirusLijst.setText("");
            textAreaVirusLijst1.setText("");
            String[] obj = dropDownHost.getSelectedItem().toString().split("=");
            String value = (String) virusInfo.get(obj[0]);
            value = value.replace(";", "\n");
            textAreaVirusLijst.setText(value);

            String[] obj1 = dropDownHost1.getSelectedItem().toString().split("=");
            String value1 = (String) virusInfo.get(obj1[0]);
            value1 = value1.replace(";", "\n");
            textAreaVirusLijst1.setText(value1);

            HashSet<Object> overeenkomst, valuesLijst1, valueLijst2;
            valuesLijst1 = new HashSet<>(Arrays.asList(textAreaVirusLijst.getText().split("\n")));
            valueLijst2 = new HashSet<>(Arrays.asList(textAreaVirusLijst1.getText().split("\n")));
            overeenkomst = new HashSet<>(valuesLijst1);
            overeenkomst.retainAll(valueLijst2);

            textAreaOvereenkomst.setText(String.valueOf(overeenkomst).replace("[", "").replace(",", "\n").replace("]", ""));
        }
    }
}



