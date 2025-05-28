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