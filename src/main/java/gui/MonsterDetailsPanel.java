package gui;

import data.Monster;
import javax.swing.*;
import java.awt.*;

public class MonsterDetailsPanel extends JScrollPane {
    private final JPanel detailsPanel;

    public MonsterDetailsPanel() {
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        setViewportView(detailsPanel);
    }

//    public void showMonsterDetails(Monster monster) {
//        detailsPanel.removeAll();
//
//        JPanel formPanel = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 5, 5, 5);
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        // Name
//        gbc.gridx = 0; gbc.gridy = 0;
//        formPanel.add(new JLabel("Имя:"), gbc);
//        gbc.gridx = 1;
//        JTextField nameField = new JTextField(monster.getName(), 20);
//        formPanel.add(nameField, gbc);
//
//        // Description
//        gbc.gridx = 0; gbc.gridy++;
//        formPanel.add(new JLabel("Описание:"), gbc);
//        gbc.gridx = 1;
//        JTextArea descriptionArea = new JTextArea(monster.getDescription(), 3, 20);
//        descriptionArea.setLineWrap(true);
//        formPanel.add(new JScrollPane(descriptionArea), gbc);
//
//        // First Mentioned
//        gbc.gridx = 0; gbc.gridy++;
//        formPanel.add(new JLabel("Впервые упомянуто:"), gbc);
//        gbc.gridx = 1;
//        JSpinner firstMentionedSpinner = new JSpinner(
//                new SpinnerNumberModel(monster.getFirstMentioned(), 0, 3000, 1));
//        formPanel.add(firstMentionedSpinner, gbc);
//
//        // Strength
//        gbc.gridx = 0; gbc.gridy++;
//        formPanel.add(new JLabel("Сила:"), gbc);
//        gbc.gridx = 1;
//        JTextField strengthField = new JTextField(monster.getStrength(), 20);
//        formPanel.add(strengthField, gbc);
//
//        // Source
//        gbc.gridx = 0; gbc.gridy++;
//        formPanel.add(new JLabel("Источник:"), gbc);
//        gbc.gridx = 1;
//        formPanel.add(new JLabel(monster.getSource()), gbc);
//
////        // Potion (if exists)
////        if (monster.getPotion() != null) {
////            gbc.gridx = 0; gbc.gridy++;
////            formPanel.add(new JLabel("Ингредиенты зелья:"), gbc);
////
////            StringBuilder potionInfo = new StringBuilder();
////            for (Ingredient ingredient : monster.getPotion().getIngredients()) {
////                potionInfo.append(ingredient.getAmount()).append(" из ").append(ingredient.getName()).append("\n");
////            }
//           if (monster.getPotion() != null) {
//              gbc.gridx = 0; 
//              gbc.gridy++;
//              formPanel.add(new JLabel("Ингредиенты зелья:"), gbc);
//
//          StringBuilder potionInfo = new StringBuilder();
//     for (data.Ingredient ingredient : monster.getPotion().getIngredients()) {
//        potionInfo.append(ingredient.getAmount())
//                 .append(" из ")
//                 .append(ingredient.getName())
//                 .append("\n");
//    }
//            JTextArea potionArea = new JTextArea(potionInfo.toString(), 5, 20);
//            potionArea.setLineWrap(true);
//            potionArea.setEditable(false);
//            gbc.gridx = 1;
//            formPanel.add(new JScrollPane(potionArea), gbc);
//
//            gbc.gridx = 0; gbc.gridy++;
//            formPanel.add(new JLabel("Время варки (минуты):"), gbc);
//            gbc.gridx = 1;
//            formPanel.add(new JLabel(String.valueOf(monster.getPotion().getBrewTimeMinutes())), gbc);
//        }
//
//        // Save button
//        gbc.gridx = 0; gbc.gridy++;
//        gbc.gridwidth = 2;
//        gbc.fill = GridBagConstraints.CENTER;
//        JButton saveButton = new JButton("Сохранить изменения");
//        saveButton.addActionListener(_ -> {
//            monster.setName(nameField.getText());
//            monster.setDescription(descriptionArea.getText());
//            monster.setFirstMentioned((Integer) firstMentionedSpinner.getValue());
//            monster.setStrength(strengthField.getText());
//            JOptionPane.showMessageDialog(this, "Изменения сохранены!");
//        });
//        formPanel.add(saveButton, gbc);
//
//        detailsPanel.add(formPanel, BorderLayout.NORTH);
//        detailsPanel.revalidate();
//        detailsPanel.repaint();
//    }
//}
    public void showMonsterDetails(Monster monster) {
    detailsPanel.removeAll();
    detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

    JPanel formPanel = new JPanel();
    formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
    formPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    // Name
    JPanel namePanel = createLabelFieldPanel("Имя:", new JTextField(monster.getName(), 20));
    formPanel.add(namePanel);
    formPanel.add(Box.createRigidArea(new Dimension(0, 5)));

    // Description
    JTextArea descriptionArea = new JTextArea(monster.getDescription(), 3, 20);
    descriptionArea.setLineWrap(true);
    JPanel descPanel = createLabelFieldPanel("Описание:", new JScrollPane(descriptionArea));
    formPanel.add(descPanel);
    formPanel.add(Box.createRigidArea(new Dimension(0, 5)));

    // First Mentioned
    JSpinner firstMentionedSpinner = new JSpinner(
            new SpinnerNumberModel(monster.getFirstMentioned(), 0, 3000, 1));
    JPanel mentionedPanel = createLabelFieldPanel("Впервые упомянуто:", firstMentionedSpinner);
    formPanel.add(mentionedPanel);
    formPanel.add(Box.createRigidArea(new Dimension(0, 5)));

    // Strength
    JPanel strengthPanel = createLabelFieldPanel("Сила:", new JTextField(monster.getStrength(), 20));
    formPanel.add(strengthPanel);
    formPanel.add(Box.createRigidArea(new Dimension(0, 5)));

    // Source
    JPanel sourcePanel = createLabelFieldPanel("Источник:", new JLabel(monster.getSource()));
    formPanel.add(sourcePanel);
    formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

    // Potion (if exists)
    if (monster.getPotion() != null) {
        JLabel potionLabel = new JLabel("Ингредиенты зелья:");
        potionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(potionLabel);

        StringBuilder potionInfo = new StringBuilder();
        for (data.Ingredient ingredient : monster.getPotion().getIngredients()) {
            potionInfo.append(ingredient.getAmount())
                     .append(" из ")
                     .append(ingredient.getName())
                     .append("\n");
        }
        JTextArea potionArea = new JTextArea(potionInfo.toString(), 5, 20);
        potionArea.setLineWrap(true);
        potionArea.setEditable(false);
        formPanel.add(new JScrollPane(potionArea));
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel brewTimePanel = createLabelFieldPanel("Время варки (минуты):", 
                new JLabel(String.valueOf(monster.getPotion().getBrewTimeMinutes())));
        formPanel.add(brewTimePanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    // Save button
    JButton saveButton = new JButton("Сохранить изменения");
    saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    saveButton.addActionListener(_ -> {
        monster.setName(((JTextField)((JPanel)formPanel.getComponent(0)).getComponent(1)).getText());
        monster.setDescription(descriptionArea.getText());
        monster.setFirstMentioned((Integer) firstMentionedSpinner.getValue());
        monster.setStrength(((JTextField)((JPanel)formPanel.getComponent(6)).getComponent(1)).getText());
        JOptionPane.showMessageDialog(this, "Изменения сохранены!");
    });
    formPanel.add(saveButton);

    detailsPanel.add(formPanel);
    detailsPanel.revalidate();
    detailsPanel.repaint();
}

// Вспомогательный метод для создания панелей с меткой и полем
private JPanel createLabelFieldPanel(String labelText, JComponent field) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    
    JLabel label = new JLabel(labelText);
    label.setPreferredSize(new Dimension(150, label.getPreferredSize().height));
    panel.add(label);
    panel.add(Box.createRigidArea(new Dimension(10, 0)));
    panel.add(field);
    
    return panel;
}
}