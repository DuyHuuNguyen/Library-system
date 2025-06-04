package com.g15.library_system.view.managementView.librarians;

import com.g15.library_system.controller.LibrarianController;
import com.g15.library_system.entity.Librarian;
import com.g15.library_system.entity.User;
import com.g15.library_system.enums.ApiKey;
import com.g15.library_system.mapper.LibrarianMapper;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.repository.LibrarianRepository;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import com.g15.library_system.controller.LibrarianController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LibrarianPanel extends JPanel {
  private static final Logger log = LoggerFactory.getLogger(LibrarianPanel.class);
  private CheckboxTablePanel checkbox;
  private String[] coloums = {
    "",
    "First name",
    "Last name",
    "Email",
    "Password",
    "Phone number",
    "Avatar Key",
    "Date of birth",
    "Address"
  };
  private Object[][] data;
  private JPanel panelContent;
  private CardLayout cardLayout;
  private LibrarianToolPanel toolPanel;

  private UpdateLibrarianPanel addLibrarian;
  private ModifyLibrarianPanel modifyLibrarian;

  private Optional<Librarian> librarianModify;

  private List<Librarian> librarians;

  private LibrarianController librarianController =
      ApplicationContextProvider.getBean(LibrarianController.class);
  private LibrarianMapper librarianMapper =
      ApplicationContextProvider.getBean(LibrarianMapper.class);

  private Map<ApiKey, Runnable> mapAPIs =
      Map.of(
          ApiKey.SELECTED_TABLE,
          () -> this.findLibrarianModifySelected(),
          ApiKey.ADD,
          () -> this.addNewLibrarian(),
          ApiKey.RELOAD,
          () -> this.loadDataTable(),
          ApiKey.SEARCH,
          () -> this.findByTextOfTextFieldSearchOptionUpDataToTable());

  public static final String CONSTRAINT_TABLE = "librarian_table";
  public static final String CONSTRAINT_ADD_NEW_LIBRARIAN = "add_new_librarian";
  public static final String CONSTRAINT_MODIFY_LIBRARIAN = "modify_librarian";

  public LibrarianPanel() {
    setLayout(new BorderLayout());
    this.cardLayout = new CardLayout();
    this.panelContent = new JPanel(cardLayout);

    this.toolPanel = new LibrarianToolPanel(cardLayout, panelContent, mapAPIs);
    this.add(toolPanel, BorderLayout.NORTH);

    this.initData();

    this.checkbox = new CheckboxTablePanel(coloums, data);
    this.panelContent.add(checkbox, CONSTRAINT_TABLE);

    this.addLibrarian = new UpdateLibrarianPanel(1000, 500);
    this.panelContent.add(addLibrarian, CONSTRAINT_ADD_NEW_LIBRARIAN);
    
    this.modifyLibrarian = new ModifyLibrarianPanel(100, 500);
    this.panelContent.add(modifyLibrarian, CONSTRAINT_MODIFY_LIBRARIAN);

    add(panelContent);
  }

  private void initData() {
    this.librarians = librarianController.findALl();
    this.data = this.librarianMapper.toLibrarianData(librarians);
  }

  private void findLibrarianModifySelected() {
    var data = this.checkbox.getSelectedRowData();
    this.librarianModify = this.librarianController.findByName(data[2].toString());
    this.modifyLibrarian.addData(librarianModify);
    log.info("selected librarian {}", librarianModify.toString());
  }

  private void addNewLibrarian() {
    var newLibrarian = this.addLibrarian.getNewLibrarian();
    this.librarianController.addNewLibrarian(newLibrarian.get());
    loadDataTable();
  }

  //  private void loadDataTable() {
  //      this.removeAllDataTable();
  //      var data = librarianController.findALl();
  //
  //      this.librarians.clear();
  //
  //      this.librarians.addAll(data);
  //      this.checkbox.addDataToTable(this.librarianMapper.toLibrarianData(this.librarians));
  //  }
  private void loadDataTable() {
    List<Librarian> newData = librarianController.findALl();

  this.librarians = new ArrayList<>(newData);

  checkbox.removeAllDataTable();
  checkbox.addDataToTable(librarianMapper.toLibrarianData(librarians));
}

  private void removeAllDataTable() {
    this.checkbox.removeAllDataTable();
  }

    private void findByTextOfTextFieldSearchOptionUpDataToTable() {
        String keyword = this.toolPanel.getTextOfTextFieldSearchOption().trim().toLowerCase();

        log.info("Searching keyword: {}", keyword);

        this.removeAllDataTable();
        this.librarians.clear();

        // Gọi hàm search chung
        List<Librarian> results = librarianController.searchLibrarians(keyword);

        this.librarians.addAll(results);
        this.checkbox.addDataToTable(this.librarianMapper.toLibrarianData(this.librarians));
    }

}
