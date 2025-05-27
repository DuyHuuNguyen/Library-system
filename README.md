# 📚 Library Management System

The Library Management System manages books, users, and borrowing/returning processes with an intuitive interface for librarians and readers.

## 🔑 Key Features

- **Book Management**: Add, edit, delete, and search for books; track inventory and availability
- **User Administration**: Manage different user roles, profiles, and access control
- **Transaction Processing**: Handle borrowing/returning workflows, due dates, and notifications
- **Reporting & Analytics**: Track statistics, overdue items, and usage patterns

## 🎯 Design Patterns

- **Observer Pattern**: Notification system for overdue books and new arrivals; maintains loose coupling
- **Strategy Pattern**: Fee calculation algorithms for different reader types
- **MVC Pattern**: Separation of business logic, data, and presentation layers
- **Facade Pattern**: Simplified interface to complex library subsystems
- **Singleton Pattern**: Single instances of critical components with global access points

## 🛠️ Technology Stack

- **Backend**: Java Core + Spring framework
- **Frontend**: Swing
- **Messaging**: RabbitMQ for notifications
## 🐰 RabbitMQ workflow

![](src/main/resources/readmeImages/exchanges-topic-fanout-direct.png)



## 👨‍💻 Development Team

| No. | Full Name                  | Student ID |
|-----|----------------------------|------------|
| 01  | Nguyễn Hữu Duy             | 23130075   |
| 02  | Lê Ngọc Châu               | 23130028   |
| 03  | Phan Bá Huy Hoàng          | 23130117   |
| 04  | Nguyễn Võ Quốc Tuấn        | 23130370   |
| 05  | Trần Bảo Khang             | 23130147   |
| 06  | Nguyễn Minh Trí            | 23130345   |

## 🚀 Getting Started

### Prerequisites
- JDK 11 or higher
- Maven 3.6+

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/library-management.git
   ```

2. Navigate to the project directory and build:
   ```bash
   cd library-system && mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Application Screenshots

### Dashboard
![Dashboard](src/main/resources/readmeImages/dashboard.jpg)
### Book Management
###### 1. Table show data
![Book Management](src/main/resources/readmeImages/manager-book-table.png)
###### 2. Notification new book
![Notification](src/main/resources/readmeImages/notification-new-book.png)
###### 3. Export excel
![Export excel](src/main/resources/readmeImages/export-excel.png)
###### 4. Import excel
![](src/main/resources/readmeImages/import-excel.png)
### Borrowed Books
![Borrowed Books](src/main/resources/readmeImages/lended_books.jpg)
### Return Processing
![Return Processing](src/main/resources/readmeImages/return_books.jpg)

## Workflow

1. Login → 2. Book Search → 3. Checkout → 4. Return → 5. Administration

## License

This project is licensed under the MIT License - see the LICENSE file for details.

