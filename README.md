-----
Project name: Online Library Management System
------
The purpose of the project: To create a complex system for the provision of library resources, users and management.
-----
Functions:

○ Books:
■ Searching and filtering books (category, author, year of publication, etc. on).
■ Adding, updating, deleting and viewing books.
■ Inventory management of books (tracking the number of books).

○ Authors:
■ Searching and filtering authors
■ Adding, updating, deleting and viewing authors.
■ Management of authors ' books.

○ Rating and reviews:
■ Users writing ratings and reviews for books.
■ Viewing Ratings and reviews.

○ Book Reservation:
■ For users to reserve books online.
■ For users to reserve books online.
■ Tracking and managing reservation dates.

○ Book Rental:
■ Renting and returning books.
■ Tracking rental dates and return reminders.

○ Notifications and reminders:
■ Book return reminders notifications about new books and events

○ Administration:
■ System input-output and management.
■ User management (except superadmin).

○ User Module:
■ User Add, Update, Delete and view (except superadmin)..

○ Accountability:
■ Generating and viewing reports (rent statistics, most read books, etc.).

-----------------------------------------------------------------------------------------------------------------------------


Technical Task
The operations that must be performed on the modules are explained below:
------

❖ Book Management-APIs
 
1.	addBook: adds new books.
2.	updateBook: updates the information of the current book.
3.	deleteBook: deletes the book.
4.	getBookById: retrieves information about a particular book.
5.	getAllBooks: gets all the books.
   
❖  Book Search and filtering 
1.	searchBooks: searches books according to search criteria.
2.	filterBooks: filters books according to certain criteria.
Inventory Management
1.	getBookInventory: tracks and displays the current number of books.

❖ User Management-APIs

1.	addUser: adds new user.
2.	updateUser: updates the data of the current user.
3.	deleteUser: deletes the user.
4.	getUserById: retrieves information about a particular user.
5.	getAllUsers: gets all users.
6.	getUserByUsername: retrieves user information based on userName.
7.	getUsersByRole: acquires users with a certain role.
8.	deactivateUser: deactivates the user account.

❖ Registration and authorization-APIs

1.	registerUser: performs user registration.
2.	login: confirms user login.
3.	logout: performs user's exit.

❖ Author Management – APIs
The functions of adding, updating, deleting and viewing authors will be provided. The following methods will provide this functionality:
1.	addAuthor: adds a new author.
2.	updateAuthor: updates the current author's data.
3.	deleteAuthor: deletes the author.
4.	getAuthorById: obtains information about a particular author.
5.	getAllAuthors: acquires all authors.
6.	getAuthorByName: retrieves data based on the author's name.
❖ Management Of Books By Authors
1.	removeBookFromAuthor: removes the author's book.
2.	getBooksByAuthor: acquires books belonging to the author.

❖	Ratings and Reviews – APIs
1. addRatingAndReview(): Adds new rating and feedback.
2. getRatingsAndReviewsByBook(): Brings all ratings and reviews for a book.
3. getRatingsAndReviewsByUser(): It brings all ratings and reviews given by a user.
4. updateRatingAndReview(): Updates the current rating and review.
5. deleteRatingAndReview(): Removes the current rating and review.

❖ Reservations-APIs

1.	addReservation: adds new reservation for book by user.
2.	updateReservation: updates an existing reservation (for example, changes dates).
3.	cancelReservation: cancels an existing reservation.
4.	getUserReservations: gets all the active reservations of the user.
5.	getBookReservations: gets all active reservations for the book.
6.	getReservationDetails: gets detailed information about certain reservations.
7.	checkAvailability: checks whether the book is available on certain dates.


❖ Report generation-APIs 

1.	generateRentalStatistics: generates rental statistics.
2.	getMostReadBooks: gets the most read books.
3.	generateUserActivityReport: generates user activity report.
4.	getBookRentalHistory: gets the rental history of the book.
5.	getUserLoginHistory: retrieves the user's login history.


❖ APIs for notifications and reminders

1.	addReminder
○ Work to do: adds a reminder to the user to return the book.
○ Input parameters: userId, bookId, reminderDate
2.	removeReminder
○ Work to do: deletes the reminder.
○ Input parameters: reminderId
3.	getRemindersByUser
○ Work to do: gets all reminders for a specific user.
○ Input parameters: userId
4.	getUpcomingReminders
○ Work to do: gets reminders of the future (for example, after a certain date).
○ Input parameters: date
5.	sendReminderNotification
○ Work to do: sends a reminder to the user via email, SMS, or in-app notification.
○ Input parameters: userId, message
6.	addNewBookNotification
○ Work to do: adds notifications to users about new books.
○ Input parameters: userId, bookId, notificationMessage
7.	addEventNotification
○ Work to do: adds notification to users about actions.
○ Input parameters: userId, eventId, notificationMessage
8.	getNotificationsByUser
○ Work to do: gets all notifications for a specific user.
○ Input parameters: userId
9.	removeNotification
○ Work to do: deletes the notification.
○ Input parameters: notificationId

❖ Automatic check and refund-APIs
The functions of automatic checking and returning books will be provided. The following methods will provide this functionality:

1.	checkOverdueBooks
○ Work to do: checks books with a refund date past.
○ Input Settings: currentDate (today's date)
2.	sendOverdueNotices
○ Work to do: sends notifications to users for non-refundable books.
○ Access Settings: overdueBooks (list of non-refundable books)
3.	updateBookStatus
○ Work to do: updates the status of the book (for example, “returned”).
○ Input parameters: bookId, newStatus
4.	logReturnEvent
○ Work to do: records the event that occurred during the return of the book.
○ Input parameters: bookId, userId, returnDate

-----------------------------------------------------------------------------------------------------------------------------

Roles and permission
---
1.	Admin (SuperAdmin)
○ User management: ability to add, delete, update users and view all user data.
○ Book management: the ability to add, update, delete books and view the list of books.
○ Author Management: the ability to add, update, delete authors and view the list of authors.
○ Reservation management: ability to confirm or cancel book reservations.
○ Rating and Feedback Management: the ability to track, approve and delete ratings and reviews that users give for books.
○ Notifications and reminders: adding, removing and updating notifications and reminders.
○ Reports: viewing rental statistics, most read books and other reports.
○ System settings: change and manage System Settings.
○ Roles and permissions: assignment and management of roles and permissions.

2.	Library Worker (Admin)
○ Book management: the ability to add, update, delete books and view their list.
○ Reservation management: accepting and managing book reservations.
○ Reminders and notifications: sending reminders to users and tracking notifications.
○ Return of books: to carry out the return and automatic verification of books.
○ Authors management: updating and viewing authors ' information.
○ Viewing reports: viewing rental statistics and other reports.

3.	USER (User)
○ Book Search: searching and filtering books.
○ Booking:book books.
○ Rating and feedback: giving ratings and writing reviews for books.
○ Reminders: keep track of reminders.
○ Notifications: viewing notifications about new books and events.
○ User data: updating your own user data.

-----------------------------------------------------------------------------------------------------------------------------

Requirements for the project
-------
➔ Exception Handling:

◆ Error management and creation of custom exception classes in the management of book, user and author data.

➔  Unit Testing with JUnit:

◆ Write unit tests using JUnit for project testing.

◆ Create tests for each method and make sure it works correctly.

➔ Security:

◆ Use Spring Security to ensure the security of your project.

◆ Apply role-based authentication and authorization.

➔ Spring AOP : 

◆ Build log mechanism using Aspect Oriented Programming.



