package com.g15.library_system.view.managementView.dashboard;

import com.g15.library_system.view.overrideComponent.RoundedPanel;

import java.awt.*;
import java.awt.event.ActionListener;

public class QuickAccessPanel extends RoundedPanel {
    DashboardCard totalBooksCard,
            lendedBooksCard,
            returnedBookCard,
            availableBooksCard,
            totalUsersCard,
            overdueBooksCard;


    QuickAccessPanel(){
        super(20, Color.WHITE, null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,15));
        // Total Books
        totalBooksCard = new DashboardCard(
                "src/main/java/view/LibraryUI/icons/manageBook2.png", // icon path
                "Total Books",
                "2000",
                new Color(255, 238, 215), // Màu nền thẻ (Cam nhạt)
                new Color(255, 160, 0),   // Màu nền icon (Cam)
                new Color(170, 105, 0)
        );

        // Lended Books
         lendedBooksCard = new DashboardCard(
                "src/main/java/view/LibraryUI/icons/lendBookIcon.png",// icon path
                "Lended Books",
                "500",
                new Color(255, 245, 200), // Màu nền thẻ (Vàng nhạt)
                new Color(255, 204, 0),   // Màu nền icon (Vàng)
                new Color(145, 117, 17)   // Màu viền thẻ (Vàng đậm/Nâu)
        );

         returnedBookCard = new DashboardCard(
                "src/main/java/view/LibraryUI/icons/manageBook2.png",// icon path
                "Returned Books",
                "200",
                new Color(235, 235, 255), // Màu nền thẻ (Tím nhạt - Lavender)
                new Color(130, 130, 250), // Màu nền icon (Tím/Xanh dương)
                new Color(90, 90, 180)    // Màu viền thẻ (Tím/Xanh dương đậm)
        );
        // Available Books
         availableBooksCard = new DashboardCard(
                "src/main/java/view/LibraryUI/icons/manageBook2.png",
                "Available Books",
                "800",
                new Color(230, 255, 255), // Màu nền thẻ (Xanh ngọc nhạt - Pale Cyan/Aqua)
                new Color(70, 200, 200),  // Màu nền icon (Xanh ngọc - Cyan/Teal)
                new Color(40, 140, 140)   // Màu viền thẻ (Xanh ngọc đậm)
        );
        //Overdue Books
        overdueBooksCard = new DashboardCard(
                "src/main/java/view/LibraryUI/icons/overdueIcon.png",
                "Overdue Books",
                "300",
                new Color(230, 255, 230), // Màu nền thẻ (Xanh lá cây nhạt)
                new Color(80, 200, 80),   // Màu nền icon (Xanh lá cây)
                new Color(50, 140, 50)     // Màu viền thẻ (Xanh lá cây đậm)
        );

        // Total Users
         totalUsersCard = new DashboardCard(
                "src/main/java/view/LibraryUI/icons/totalUserIcon.png",
                "Total Users",
                "500",

                new Color(255, 235, 235), // Màu nền thẻ (Hồng nhạt)
                new Color(255, 105, 180), // Màu nền icon (Hồng)
                new Color(180, 70, 120)   // Màu viền thẻ (Hồng đậm)
        );

        add(totalBooksCard);
        add(lendedBooksCard);
        add(returnedBookCard);
        add(availableBooksCard);
        add(overdueBooksCard);
        add(totalUsersCard);
    }

    public void setTotalBooksCardButtonListener(ActionListener listener){
        this.totalBooksCard.setDashBoardCardButtonListener(listener);
    }
    public void setLendedBooksCardButtonListener(ActionListener listener){
        this.lendedBooksCard.setDashBoardCardButtonListener(listener);
    }
    public void setReturnedBooksCardButtonListener(ActionListener listener){
        this.returnedBookCard.setDashBoardCardButtonListener(listener);
    }
    public void setAvailableBooksCardButtonListener(ActionListener listener){
        this.availableBooksCard.setDashBoardCardButtonListener(listener);
    }
    public void setTotalUsersCardButtonListener(ActionListener listener){
        this.totalUsersCard.setDashBoardCardButtonListener(listener);
    }
    public void setOverdueBooksCardButtonListener(ActionListener listener){
        this.overdueBooksCard.setDashBoardCardButtonListener(listener);
    }





}