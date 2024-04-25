import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListApp {
    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> list;
    private JTextField textField;
    private JButton addButton, editButton, removeButton;

    public ToDoListApp() {
        
        frame = new JFrame("To-Do List App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        frame.add(new JScrollPane(list), BorderLayout.CENTER);

        
        textField = new JTextField();
        frame.add(textField, BorderLayout.NORTH);

       
        JPanel buttonPanel = new JPanel();

        // Add button to add items to the list
        addButton = new JButton("Add");
        buttonPanel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listModel.addElement(textField.getText());
                textField.setText("");
            }
        });

        
        editButton = new JButton("Edit");
        buttonPanel.add(editButton);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!list.isSelectionEmpty()) {
                    String newValue = JOptionPane.showInputDialog(frame, "Edit Task", list.getSelectedValue());
                    if (newValue != null && !newValue.trim().isEmpty()) {
                        listModel.set(list.getSelectedIndex(), newValue);
                    }
                }
            }
        });

        
        removeButton = new JButton("Remove");
        buttonPanel.add(removeButton);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!list.isSelectionEmpty()) {
                    listModel.remove(list.getSelectedIndex());
                }
            }
        });

        frame.add(buttonPanel, BorderLayout.SOUTH);

        
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ToDoListApp();
            }
        });
    }
}
