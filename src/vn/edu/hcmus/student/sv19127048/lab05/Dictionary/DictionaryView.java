package vn.edu.hcmus.student.sv19127048.lab05.Dictionary;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;
import static javax.swing.SwingConstants.CENTER;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Objects;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import vn.edu.hcmus.student.sv19127048.lab05.DictionaryHistory.DictionaryHistoryController;
import vn.edu.hcmus.student.sv19127048.lab05.DictionaryHistory.DictionaryHistoryView;
import vn.edu.hcmus.student.sv19127048.lab05.Game.GameView;
import vn.edu.hcmus.student.sv19127048.lab05.OnThisDaySlangWord.OnThisDaySlangWordView;

/**
 * vn.edu.hcmus.student.sv19127048.lab05.Dictionary<br>
 * Created by 19127048 - Nguyen Duc Nam<br>
 * Date 12/11/2021 - 4:42 PM<br>
 * Description: JDK16<br>
 */
public class DictionaryView extends JFrame {

  /**
   * Creates new form DictionaryJFrame
   */
  public DictionaryView() {
    dictionaryController = new DictionaryController();
    dictionaryHistoryController = new DictionaryHistoryController();
    initComponents();
  }

  private void initComponents() {
    onThisDaySlangWordView = new OnThisDaySlangWordView(dictionaryController);
    gameView = new GameView(dictionaryController);

    onThisDaySlangWordView.renderOnThisDaySlangWindow();

    JTabbedPane jTab = new JTabbedPane();
    JPanel dictionaryPanel = new JPanel();
    JButton searchButton = new JButton();
    JScrollPane slangWordScrollPane = new JScrollPane();
    JScrollPane definitionScrollPane = new JScrollPane();
    JButton editButton = new JButton();
    JButton deleteButton = new JButton();
    JPanel addNewWordPanel = new JPanel();
    JLabel slangWordLabel = new JLabel();
    JLabel definitionLabel = new JLabel();
    JButton addSlangWordButton = new JButton();
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem onThisDaySlangItem = new JMenuItem();
    JMenuItem showHistoryItem = new JMenuItem();
    JMenuItem restoreDictionaryItem = new JMenuItem();
    JMenu gameMenu = new JMenu("Game");

    searchField = new JTextField();
    slangWordList = new JList<>();
    definitionTable = new JTable();
    searchByComboBox = new JComboBox<>();
    slangWordField = new JTextField();
    definitionField = new JTextField();

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        dictionaryController.saveSlangMap();
        dictionaryController.saveDefinitionMap();
        System.exit(0);
      }
    });
    setPreferredSize(new Dimension(650, 820));

    searchButton.setText("Search");
    searchButton.addActionListener(this::searchButtonActionPerformed);

    searchField.setToolTipText("Input slang word");

    loadSlangWordIntoList();
    slangWordList.addListSelectionListener(this::slangWordListValueChanged);
    slangWordScrollPane.setViewportView(slangWordList);

    definitionTable.setModel(new DefaultTableModel(
        null,
        new String [] {
            "Definition"
        }
    ));
    definitionScrollPane.setViewportView(definitionTable);

    editButton.setText("Edit");
    editButton.addActionListener(this::editButtonActionPerformed);

    deleteButton.setText("Delete");
    deleteButton.addActionListener(this::deleteButtonActionPerformed);


    searchByComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Search by slang", "Search by definition" }));

    GroupLayout dictionaryPanelLayout = new GroupLayout(dictionaryPanel);
    dictionaryPanel.setLayout(dictionaryPanelLayout);
    dictionaryPanelLayout.setHorizontalGroup(
        dictionaryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(dictionaryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dictionaryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(dictionaryPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(editButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                    .addGroup(dictionaryPanelLayout.createSequentialGroup()
                        .addGroup(dictionaryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(dictionaryPanelLayout.createSequentialGroup()
                                .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchByComboBox, 0, 165, Short.MAX_VALUE))
                            .addComponent(slangWordScrollPane)
                            .addComponent(searchField))
                        .addGap(18, 18, 18)
                        .addComponent(definitionScrollPane, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    dictionaryPanelLayout.setVerticalGroup(
        dictionaryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(dictionaryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dictionaryPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(definitionScrollPane, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                    .addGroup(dictionaryPanelLayout.createSequentialGroup()
                        .addGroup(dictionaryPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(searchButton)
                            .addComponent(searchByComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slangWordScrollPane)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dictionaryPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(deleteButton))
                .addGap(24, 24, 24))
    );

    jTab.addTab("Dictionary", dictionaryPanel);

    slangWordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24)); // NOI18N
    slangWordLabel.setHorizontalAlignment(CENTER);
    slangWordLabel.setText("Slang word:");

    slangWordField.setFont(new Font("Segoe UI", Font.PLAIN, 24)); // NOI18N
    slangWordField.setHorizontalAlignment(CENTER);

    definitionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24)); // NOI18N
    definitionLabel.setHorizontalAlignment(CENTER);
    definitionLabel.setText("Definition:");

    definitionField.setFont(new Font("Segoe UI", Font.PLAIN, 24)); // NOI18N
    definitionField.setHorizontalAlignment(CENTER);

    addSlangWordButton.setFont(new Font("Segoe UI", Font.PLAIN, 23)); // NOI18N
    addSlangWordButton.setText("Add");
    addSlangWordButton.addActionListener(this::addSlangWordButtonActionPerformed);

    GroupLayout addNewWordPanelLayout = new GroupLayout(addNewWordPanel);
    addNewWordPanel.setLayout(addNewWordPanelLayout);
    addNewWordPanelLayout.setHorizontalGroup(
        addNewWordPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(addNewWordPanelLayout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addGap(18, 18, 18)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, addNewWordPanelLayout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(addNewWordPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(slangWordLabel, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                    .addGroup(addNewWordPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(definitionLabel)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewWordPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(slangWordField)
                    .addComponent(definitionField, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addNewWordPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(addSlangWordButton))
                .addGap(67, 67, 67))
    );
    addNewWordPanelLayout.setVerticalGroup(
        addNewWordPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(addNewWordPanelLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(addNewWordPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(slangWordLabel)
                    .addComponent(slangWordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSlangWordButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(addNewWordPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(definitionLabel)
                    .addComponent(definitionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewWordPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)))
    );

    jTab.addTab("Add new word", addNewWordPanel);

    onThisDaySlangItem.setText("On this day slang");
    onThisDaySlangItem.addActionListener(this::showOnThisDaySlangItemActionPerformed);
    fileMenu.add(onThisDaySlangItem);

    showHistoryItem.setText("Show history");
    showHistoryItem.addActionListener(this::showHistoryItemActionPerformed);
    fileMenu.add(showHistoryItem);

    restoreDictionaryItem.setText("Restore default dictionary");
    restoreDictionaryItem.addActionListener(this::restoreDefaultDictionaryActionPerformed);
    fileMenu.add(restoreDictionaryItem);

    JMenuItem gameMode1Item = new JMenuItem("Mode 1");
    gameMode1Item.addActionListener(this::gameMode1ActionPerformed);
    gameMenu.add(gameMode1Item);
    JMenuItem gameMode2Item = new JMenuItem("Mode 2");
    gameMode2Item.addActionListener(this::gameMode2ActionPerformed);
    gameMenu.add(gameMode2Item);


    menuBar.add(fileMenu);
    menuBar.add(gameMenu);

    setJMenuBar(menuBar);

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTab, GroupLayout.PREFERRED_SIZE, 614, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTab)
                .addContainerGap())
    );

    pack();
  }// </editor-fold>

  /**
   * Add slang word moi vao dictionary
   * @param evt
   */
  private void addSlangWordButtonActionPerformed(ActionEvent evt) {
    // TODO add your handling code here:
    System.out.println("Slang word added");

    String newSlangWord = slangWordField.getText();
    String newDefinition = definitionField.getText();

    // Kiem tra cac truong co trong khong
    if (newSlangWord.isEmpty() || newDefinition.isEmpty()) {
      JOptionPane.showMessageDialog(
          null,
          "Slang word field or definition field is empty try again",
          "Empty field",
          ERROR_MESSAGE
      );
    } else {
      // Kiem tra slang co ton tai hay khong neu co
      // Override, duplicate
      if (dictionaryController.isSlangWordExist(newSlangWord)) {
        int state = JOptionPane.showOptionDialog(
            null,
            String.format("%s already existed, do you want to override or duplicate it",
                newSlangWord),
            "Existed slang word",
            YES_NO_CANCEL_OPTION,
            INFORMATION_MESSAGE,
            null,
            new String[]{"Override", "Duplicate", "Cancel"},
            2
        );

        switch (state) {
          // Override
          case 0 -> dictionaryController.addNewSlangWord(newSlangWord, newDefinition);
          // Duplicate
          case 1 -> dictionaryController.addNewDefinition(newSlangWord, newDefinition);
        }
      } else { // Ghi slang word moi vao dictionary
        dictionaryController.addNewSlangWord(newSlangWord, newDefinition);
      }

      definitionField.setText("");

      JOptionPane.showMessageDialog(
          null,
          "Added a new slang word successfully",
          "Successfully added",
          INFORMATION_MESSAGE
      );
    }
  }

  /**
   * Edit 1 slang word
   * @param evt edit button
   */
  private void editButtonActionPerformed(ActionEvent evt) {
    System.out.println("Clicked edit button");
    // TODO add your handling code here:
    // Kiem tra da chon tren list chua
    if (slangWordList.isSelectionEmpty()) {
      JOptionPane.showMessageDialog(
          null,
          "You did not select any word",
          "Empty select",
          INFORMATION_MESSAGE
      );
    } else {
      // Kiem tra definition co dang duoc chon khong
      int rowIdx = definitionTable.getSelectedRow();
      String definition;
      if (rowIdx == -1) {
        definition = "";
      } else {
        definition = (String) definitionTable.getValueAt(rowIdx, 0);
      }

      // Render edit field
      editView = new EditView();
      editView.getEditSlangField().setText(slangWordList.getSelectedValue());
      editView.getEditDefinitionField().setText(definition);
      editView.getConfirmEditButton().addActionListener(this::confirmEditButtonActionPerformed);
      editView.renderEditTextField();
    }
  }

  /**
   * Confirm edit slang word
   * @param evt confirm button
   */
  private void confirmEditButtonActionPerformed(ActionEvent evt){
    System.out.println("Clicked confirm button");
    // TODO add your handling code here:
    // Update slang word
    JTextField editSlangField = editView.getEditSlangField();
    JTextField editDefinition = editView.getEditDefinitionField();

    String oldSlangWord = slangWordList.getSelectedValue();
    String newSlangWord = editSlangField.getText();

    int rowIdx = definitionTable.getSelectedRow();
    String oldDefinition;
    if (rowIdx == -1) {
      oldDefinition = "";
    } else {
      oldDefinition = (String) definitionTable.getValueAt(rowIdx, 0);
    }
    String newDefinition = editDefinition.getText();

    if (newSlangWord.isEmpty()) {
      JOptionPane.showMessageDialog(
          null,
          "Your edit field is empty",
          "Empty field",
          INFORMATION_MESSAGE
      );
    } else {
      // Update slang word
      boolean isSuccess = false;
      if (!oldSlangWord.equals(newSlangWord)) {
        isSuccess = dictionaryController.updateSlangWord(oldSlangWord, newSlangWord);
      }

      // Update definition
      if (!oldDefinition.isEmpty() && !newDefinition.isEmpty() && !oldDefinition.equals(newDefinition)) {
        isSuccess = dictionaryController.updateSlangDefinition(newSlangWord, oldDefinition, newDefinition);
      }

      if (isSuccess) {
        editSlangField.setText("");
        editDefinition.setText("");
        JOptionPane.showMessageDialog(
            null,
            "Update slang word successfully",
            "Successfully Edit",
            INFORMATION_MESSAGE
        );
      } else {
        JOptionPane.showMessageDialog(
            null,
            "Edit slang word failed",
            "Edit Fail",
            ERROR_MESSAGE
        );
      }

      // Re-render slang list
      loadSlangWordIntoList();
    }
  }

  /**
   * Handle delete button event
   * @param evt delete button
   */
  private void deleteButtonActionPerformed(ActionEvent evt) {
    System.out.println("Clicked delete button");
    // TODO add your handling code here:

    // Kiem tra co dang chon tu can xoa khong
    if (slangWordList.isSelectionEmpty()) {
      JOptionPane.showMessageDialog(
          null,
          "You did not select any word",
          "Empty select",
          INFORMATION_MESSAGE
      );
    } else {
      // Confirm
      int state = JOptionPane.showConfirmDialog(
          null,
          "Are you sure delete this slang word?",
          "Confirm",
          JOptionPane.OK_CANCEL_OPTION
      );

      // Kiem tra confirm dialog
      if (state == 0) {
        String value = slangWordList.getSelectedValue();
        dictionaryController.deleteSlangWord(value);

        // Render lai slang list
        loadSlangWordIntoList();
        // Clear definition table
        definitionTable.setModel(new DefaultTableModel(null, new String [] {"Definition"}));
      }
    }
  }

  /**
   * Search button event handle
   * @param evt searchButton event
   */
  private void searchButtonActionPerformed(ActionEvent evt) {
    System.out.println("Clicked search button");
    // TODO add your handling code here:
    // Lay tu search trong searchField
    String searchStr = searchField.getText();
    String[] listStrings;

    // Ghi lai lich su search
    dictionaryHistoryController.writeSearchHistory(searchStr);

    // Neu khong co tu nao trong searchField render lai list goc
    if (searchStr.length() == 0) {
      listStrings = dictionaryController.getSlangWords();
    } else { // Neu co tu trong searchField render tu can search vao list
      // Search slang word theo definition
      if (Objects.equals(searchByComboBox.getSelectedItem(), "Search by definition")) {
        listStrings = dictionaryController.getSlangWordsByDefinition(searchStr);
      } else { // Search definition theo slang word
        if (dictionaryController.isSlangWordExist(searchStr)) {
          listStrings = new String[]{searchStr};
        } else { // Neu tu search khong ton tai render empty list
          listStrings = new String[] {""};
        }
      }
    }

    reRenderList(listStrings);
  }

  private void showOnThisDaySlangItemActionPerformed(ActionEvent evt) {
    onThisDaySlangWordView.renderOnThisDaySlangWindow();
  }

  private void showHistoryItemActionPerformed(ActionEvent evt) {
    // TODO add your handling code here:
    DictionaryHistoryView.renderHistoryWindow();
  }

  private void restoreDefaultDictionaryActionPerformed(ActionEvent evt) {
    // TODO add your handling code here:
    dictionaryController.restoreDefaultDictionary();
    loadSlangWordIntoList();
    definitionTable.setModel(new DefaultTableModel(null, new String [] {"Definition"}));
  }

  /**
   * Handle onChange cua list
   * @param evt
   */
  private void slangWordListValueChanged(ListSelectionEvent evt) {
    System.out.println("List value has changed");
    // TODO add your handling code here:
    String slangWord = slangWordList.getSelectedValue();
    if (!Objects.equals(slangWord, "")) {
      loadDefinitionsIntoTable(slangWord);
    }
  }

  /**
   * Render game mode 1
   *
   * @param evt
   */
  private void gameMode1ActionPerformed(ActionEvent evt) {
    System.out.println("Clicked mode 1");
    gameView.setGameMode(1);
    gameView.renderGameWindow();
  }

  /**
   * Render game mode 2
   * @param evt
   */
  private void gameMode2ActionPerformed(ActionEvent evt) {
    System.out.println("Clicked mode 2");
    gameView.setGameMode(2);
    gameView.renderGameWindow();
  }

  /**
   * Load definition cua slang dang duoc chon trong list vao table
   * @param slangWord slang word
   */
  private void loadDefinitionsIntoTable(String slangWord) {
    HashSet<String> data = dictionaryController.getDefinitionsBySlangWord(slangWord);
    if (data != null) {
      int size = data.size();
      Object [][] definitions = new Object[size][1];
      int i = 0;
      for (String elt: data) {
        definitions[i][0] = elt;
        ++i;
      }
      definitionTable.setModel(new DefaultTableModel(definitions, new String [] {"Definition"}));
    }
  }

  /**
   * Load slang word vao list
   */
  private void loadSlangWordIntoList() {
    slangWordList.setModel(new AbstractListModel<>() {
      final String[] strings = dictionaryController.getSlangWords();

      public int getSize() {
        return strings.length;
      }

      public String getElementAt(int i) {
        return strings[i];
      }
    });
  }

  private void reRenderList(String[] listStrings) {
    // re-render list
    slangWordList.setModel(new AbstractListModel<>() {
      final String[] strings = listStrings;

      public int getSize() {
        return strings.length;
      }

      public String getElementAt(int i) {
        return strings[i];
      }
    });
  }

  // Variables declaration - do not modify
  private JTextField definitionField;
  private JTable definitionTable;
  private JComboBox<String> searchByComboBox;
  private JTextField searchField;
  private JTextField slangWordField;
  private JList<String> slangWordList;

  private final DictionaryController dictionaryController;
  private final DictionaryHistoryController dictionaryHistoryController;

  private EditView editView;
  private GameView gameView;
  private OnThisDaySlangWordView onThisDaySlangWordView;
  // End of variables declaration
}
