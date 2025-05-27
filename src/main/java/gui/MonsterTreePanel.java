package gui;

import data.Monster;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.Map;
import java.util.List;

public class MonsterTreePanel extends JScrollPane {
    private final JTree monsterTree;
    private final DefaultTreeModel treeModel;
    private final DefaultMutableTreeNode rootNode;
    private MonsterSelectionListener selectionListener;

    public MonsterTreePanel(Map<String, List<Monster>> monsterCollections) {
        rootNode = new DefaultMutableTreeNode("Бестиарий");
        treeModel = new DefaultTreeModel(rootNode);
        monsterTree = new JTree(treeModel);
        setViewportView(monsterTree);

        monsterTree.addTreeSelectionListener(_ -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) monsterTree.getLastSelectedPathComponent();
            if (node != null && node.getUserObject() instanceof Monster && selectionListener != null) {
                selectionListener.onMonsterSelected((Monster) node.getUserObject());
            }
        });

        updateTree(monsterCollections);
    }

    public void updateTree(Map<String, List<Monster>> monsterCollections) {
        rootNode.removeAllChildren();
        monsterCollections.forEach((fileName, monsters) -> {
            DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(fileName);
            monsters.forEach(monster -> fileNode.add(new DefaultMutableTreeNode(monster)));
            rootNode.add(fileNode);
        });
        treeModel.reload();
    }

    public void setMonsterSelectionListener(MonsterSelectionListener listener) {
        this.selectionListener = listener;
    }

    public interface MonsterSelectionListener {
        void onMonsterSelected(Monster monster);
    }
}