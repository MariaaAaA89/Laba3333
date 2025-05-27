package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import handlers.FileImportHandler;
import data.Monster;
import gui.MonsterDetailsPanel;
import gui.MonsterTreePanel;
import handlers.JsonImportHandler;
import handlers.XmlImportHandler;
import handlers.YamlImportHandler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class BestiaryApp {
    private JFrame mainFrame;
    private final Map<String, List<Monster>> monsterCollections = new HashMap<>();
    private MonsterTreePanel treePanel;
    private MonsterDetailsPanel detailsPanel;

    public BestiaryApp() {
        initializeUI();
    }

    private void initializeUI() {
        mainFrame = new JFrame("Бестиарий");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new BorderLayout());

        JMenuBar menuBar = createMenuBar();
        mainFrame.setJMenuBar(menuBar);

        treePanel = new MonsterTreePanel(monsterCollections);
        detailsPanel = new MonsterDetailsPanel();

        treePanel.setMonsterSelectionListener(monster -> detailsPanel.showMonsterDetails(monster));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(treePanel);
        splitPane.setRightComponent(detailsPanel);
        splitPane.setDividerLocation(300);

        mainFrame.add(splitPane, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");

        JMenuItem importItem = new JMenuItem("Импортировать файлы");
        importItem.addActionListener(this::handleImport);

        JMenuItem exportJsonItem = new JMenuItem("Экспортировать в JSON");
        exportJsonItem.addActionListener(_ -> exportToFormat("json"));

        JMenuItem exportXmlItem = new JMenuItem("Экспортировать в XML");
        exportXmlItem.addActionListener(_ -> exportToFormat("xml"));

        JMenuItem exportYamlItem = new JMenuItem("Экспортировать в YAML");
        exportYamlItem.addActionListener(_ -> exportToFormat("yaml"));

        fileMenu.add(importItem);
        fileMenu.addSeparator();
        fileMenu.add(exportJsonItem);
        fileMenu.add(exportXmlItem);
        fileMenu.add(exportYamlItem);
        menuBar.add(fileMenu);
        return menuBar;
    }

    private static File[] chooseFiles() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(new FileNameExtensionFilter(
                "Файлы Бестиария (JSON, XML, YAML)", "json", "xml", "yaml", "yml"));

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFiles();
        }
        return null;
    }

    public static void exportToFile(File file, String format, List<Monster> monsters) throws Exception {
        ObjectMapper mapper = switch (format) {
            case "json" -> new ObjectMapper();
            case "xml" -> new XmlMapper();
            case "yaml" -> new YAMLMapper();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
        mapper.writeValue(new FileWriter(file), monsters);
    }

    public static List<Monster> importFile(File file) throws Exception {
        FileImportHandler jsonHandler = new JsonImportHandler();
        FileImportHandler xmlHandler = new XmlImportHandler();
        FileImportHandler yamlHandler = new YamlImportHandler();

        jsonHandler.setNextHandler(xmlHandler);
        xmlHandler.setNextHandler(yamlHandler);

        return jsonHandler.handle(file);
    }

    private void handleImport(ActionEvent e) {
        File[] files = chooseFiles();
        if (files != null) {
            for (File file : files) {
                try {
                    List<Monster> monsters = importFile(file);
                    monsterCollections.put(file.getName(), monsters);
                    treePanel.updateTree(monsterCollections);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Ошибка при импорте файла: " + file.getName() + "\n" + ex.getMessage(),
                            "Ошибка импорта", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void exportToFormat(String format) {
        if (monsterCollections.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Нет данных для экспорта", "Ошибка экспорта", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Экспорт в " + format.toUpperCase());
        fileChooser.setFileFilter(new FileNameExtensionFilter(
                format.toUpperCase() + " Файлы", format));

        if (fileChooser.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith("." + format)) {
                file = new File(file.getAbsolutePath() + "." + format);
            }

            try {
                List<Monster> allMonsters = monsterCollections.values()
                        .stream()
                        .flatMap(List::stream)
                        .toList();

                exportToFile(file, format, allMonsters);

                JOptionPane.showMessageDialog(mainFrame,
                        "Успешно экспортировано в " + file.getName(),
                        "Экспорт завершен", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainFrame,
                        "Ошибка при экспорте файла: " + ex.getMessage(),
                        "Ошибка экспорта", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}