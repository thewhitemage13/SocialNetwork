
# Social Network

**Description:** Developed a social network using a microservice architecture that provides flexibility, scalability and high system performance. The project includes multiple microservices, each responsible for a specific functionality of the platform, ensuring reliable and efficient interaction between components.

## Functionality
**UserService**: 
   - User creation
   - User deletions
   - User update
   - User view by id
   - View all users
   - Open a user to view full information about the user
**SubscriptionService**:
   - Subscribe to other users
   - Unsubscribe
   - View all user subscriptions
   - View all of a user's subscribers
**PostService**:
   - Post creation
   - Editing a post
   - Deleting a post
   - Opening a post to view full details
   -opening all user posts by userId
**MediaService**:
   - Upload a media file
   - Deleting media files
   - Get information about media
**LikeService**:
   - Put likes on publications
   - Put likes on comments
   - Delete likes
   - View all likes on a post
   - View all likes on a comment
**CommentService**:
   - Create comments
   - Delete comment
   - Update comment
   - Get all the comments on the post
**NotificationService**:
   - Create notifications
   - Update notice
   - Get notification by userId
   - Get all notifications by userId
**StatisticService**:
   - Creating statistics
   - Updating statistics
   - View statistics by date
   - View all statistics

## Architecture

The project is implemented based on a microservice architecture using the following components:

- **EurekaServer**: To discover microservices and simplify the management of component interactions.
- **ApiGateway**: To route requests and provide secure and optimized access to microservices.
- **UserService**: For user management.
- **SubscriptionService**: To manage subscriptions.
- **PostService**: To control the stations.
- **MediaService**: To manage media files.
- **LikeService**: To manage likes.
- **CommentService**: To manage comments.
- **NotificationService**: To manage notifications.
- **StatisticService**: Statistical Management.

## Rules of Use

- Email and phone number must be unique.
- The user id we specify in the order must exist.
- Product id we specify in the order must exist.

## Technologies

The following technologies and libraries are used in the project:

- **Java**.
- **Spring Framework**:
  - Spring Boot
  - Spring Data JPA
  - Spring Cloud
  - Spring Web
- **Amazon S3** - cloud storage for media files.
- **PostgreSQL** - relational database.
- **Kafka** - message broker for microservices interoperability.
- **Maven** - a tool for dependency management and project building.
- **Passay** - library for password security.
- **libphonenumber** - library for processing and validating phone numbers.
- **Commons-Validator** - library for data validation.
- **Design Patterns** - design patterns for creating clean and maintainable code.
- **S.O.L.I.D.** - Principles for architecture flexibility and scalability.

## Achievements
- Microservice Architecture: Designed and implemented a microservice architecture to provide flexibility and ease of scaling the application.
- Integration with Amazon S3: Implemented integration with Amazon S3 for media storage, enabling secure and efficient media management.
- Data validation: Utilized Passay, libphonenumber, and Commons-Validator libraries to ensure the security and accuracy of user data.
- Secure Communication: Seamless communication between microservices was set up using Kafka and Spring Cloud, which ensured high reliability and performance of the system.
- Design Principles: Applied S.O.L.I.D. principles and design patterns, which improved the quality of the architecture and facilitated code maintenance.

### Installation and Startup

- Installed JDK (Java Development Kit).
- Maven installed.
- Installed PostgreSQL.
- Kafka installed.
- Amazon Web Services (AWS) account with access to S3.
