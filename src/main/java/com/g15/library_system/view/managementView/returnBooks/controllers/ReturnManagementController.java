package com.g15.library_system.view.managementView.returnBooks.controllers;

import com.g15.library_system.controller.ReaderController;
import com.g15.library_system.data.ReaderData;
import com.g15.library_system.dto.returnBookDTOs.ReturnBookDTO;
import com.g15.library_system.entity.Book;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.mapper.ITransactionMapper;
import com.g15.library_system.mapper.impl.TransactionMapper;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.view.managementView.returnBooks.ReturnBookPanel;
import com.g15.library_system.view.managementView.returnBooks.TestFrame;
import com.g15.library_system.view.managementView.returnBooks.commands.RefreshCommand;
import lombok.Getter;

import java.util.*;

public class ReturnManagementController implements Observer {
    //view
    private ReturnBookPanel returnBookPanel;
    private TestFrame testFrame;


    // controller
    private ReturnBookController returnBookController;

    // temp var
    private Reader readerFound;
    private List<Transaction> borrowTransactions = new ArrayList<>();
    private Map<Book, Integer> borrowingBooks = new HashMap<>();

    private ITransactionMapper transactionMapper = new TransactionMapper();
    private List<Reader> readersData = ReaderData.getInstance().getReaders();
    @Getter private List<ReturnBookDTO> returnBookDTOs = new ArrayList<>();

    //command
    private final RefreshCommand refreshCommand;


    public ReturnManagementController(ReturnBookPanel returnBookPanel, ReturnBookController returnBookController) {
        this.returnBookPanel = returnBookPanel;
        this.returnBookController = returnBookController;
        initTableData();
        this.refreshCommand = new RefreshCommand(this, returnBookPanel, transactionMapper);
        this.returnBookController.addObserver(this);
    }

    public void initTableData() {
        for (Reader reader : readersData) {
            try {
                if (reader.getLibraryCard() == null) {
                    continue;
                }

                reader.getLibraryCard().getReturnTransactions().stream()
                        .filter(transaction -> transaction.getTransactionType() == TransactionType.RETURNED)
                        .forEach(transaction ->
                                returnBookDTOs.add(transactionMapper.toReturnBookDTO(reader, transaction))
                        );

            } catch (NullPointerException e) {
                System.out.println("Reader " + reader.getId() + " doesn't have library card data");
            }
        }

        returnBookPanel.setTableData(transactionMapper.toReturnBookTableData(returnBookDTOs));
    }

    public void refreshTable() {
        refreshCommand.execute();
    }

    @Override
    public void update(Observable o, Object arg) {
        refreshTable();
        returnBookPanel.showPanel(ReturnBookPanel.TABLE_PANEL);
    }
}
