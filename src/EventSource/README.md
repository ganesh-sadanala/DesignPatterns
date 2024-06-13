UseCase: Building a simple bank ledger system that utilizes the [event sourcing](https://martinfowler.com/eaaDev/EventSourcing.html) pattern to maintain a transaction history. The system should allow users to perform basic banking operations such as depositing funds, withdrawing funds, and checking balances. The ledger should maintain a complete and immutable record of all transactions, enabling auditability and reconstruction of account balances at any point in time.

## Details

The service accepts two types of transactions:
1) Loads: Add money to a user (credit)

2) Authorizations: Conditionally remove money from a user (debit)

Every load or authorization PUT should return the updated balance following the transaction. Authorization declines should be saved, even if they do not impact balance calculation.


Implement the event sourcing pattern to record all banking transactions as immutable events. Each event should capture relevant information such as transaction type, amount, timestamp, and account identifier.
Define the structure of events and ensure they can be easily serialized and persisted to a data store of your choice. We do not expect you to use a persistent store (you can you in-memory object), but you can if you want. We should be able to bootstrap your project locally to test.

## Bootstrap instructions
To run this server locally, follow these steps:

1. Clone the repository to your local machine.
2. Ensure you have Java JDK, Maven installed and configured on your system.
3. Open the project in your preferred IDE.
4. Build the project using `mvn clean install`.
5. Once the build is successful, you can run the application by executing the main class `TransactionServiceApplication`.
- In most IDEs, you can right-click on the main class file, select "Run As" or "Debug As," and then choose "Java Application."
- Alternatively, you can run the following Maven command in the terminal: `mvn spring-boot:run`
6. Access the API endpoints using a tool like Postman or a web browser or terminal.

## Design considerations
1. **Model-View-Controller (MVC) Architecture:**
    - The project structure includes separate packages for models, controllers, and services, following the MVC design pattern. This separation of concerns helps maintain a clear distinction between data (models), presentation logic (controllers), and business logic (services).

2. **Dependency Injection (DI):**
    - Spring Boot's dependency injection mechanism is used extensively throughout the codebase. This approach promotes loose coupling between components and facilitates easier testing and maintenance.
   
3. **Event Sourcing Pattern:**
   - The project incorporates the Event Sourcing pattern for storing transactional data. Instead of directly modifying the current state of entities, every change is captured as a domain event (e.g., `LoadEvent`, `AuthorizationEvent`).
   - This pattern allows the application to maintain a complete audit trail of all state changes over time. By persisting events rather than state, it becomes possible to reconstruct the current state of entities by replaying events sequentially.
   - Event sourcing provides several benefits, including improved traceability, auditability, and the ability to support temporal queries and analytics.
   - The decision to use Event Sourcing was made to ensure data integrity, simplify scalability, and enable advanced features such as event replay and time travel queries.

4. **Repository Pattern:**
    - The project utilizes repository interfaces (`EventRepository` and `UserRepository`) to abstract the data access layer. This pattern provides a standardized way to interact with data storage and decouples the application logic from specific data access implementations.

5. **Service Layer:**
    - Business logic is encapsulated within service classes (`TransactionService`) rather than being directly embedded in controllers. This separation of concerns improves code readability, maintainability, and testability.

6. **Exception Handling:**
    - The code includes exception handling mechanisms to gracefully handle errors and failures. Exceptions are caught and appropriate HTTP responses are returned, adhering to the principle of robust error handling.

7. **Response Entities:**
    - HTTP response entities (`ResponseEntity`) are used to encapsulate the response data and status codes returned by the API endpoints. This approach provides flexibility in customizing response headers, status codes, and bodies.

8. **RESTful API Design:**
    - The project follows RESTful design principles, with each endpoint mapped to specific HTTP methods (e.g., PUT, GET) and resource representations (e.g., JSON payloads). This adherence to REST principles promotes interoperability, scalability, and simplicity of the API.

These design patterns and principles contribute to the overall clarity, maintainability, and extensibility of the project codebase. They facilitate modularization, separation of concerns, and adherence to best practices in software development.

## Assumptions
1. **Data Validity:**
   - The service assumes that incoming data (e.g., request payloads) is validated at the API level or by external validation mechanisms. While the service performs some basic validation checks, such as verifying that user IDs are not empty, it relies on upstream systems to enforce data integrity and correctness.
   
2. **Transactional Integrity:**
   - The service assumes that transactional integrity is maintained by the underlying database system, ensuring that operations such as user balance updates and event persistence are atomic and consistent. It does not implement custom transaction management logic within the application layer.


## Deployment considerations
If deploying this service, the following considerations should be taken into account:

1. **Containerization:**
   - The service can be containerized using Docker to ensure consistency across different environments and simplify deployment. Docker images can encapsulate the application and its dependencies, making it easy to deploy on various platforms.

2. **Orchestration:**
   - Kubernetes can be used for orchestrating containerized deployments, providing features such as scaling, load balancing, and automated rollouts. Kubernetes ensures high availability and reliability by managing containers across a cluster of nodes.

3. **Continuous Integration/Continuous Deployment (CI/CD):**
   - Implementing CI/CD pipelines with tools like Jenkins, GitLab CI/CD, or GitHub Actions automates the build, test, and deployment process. CI/CD pipelines enable frequent and reliable releases, improving development velocity and reducing manual errors.

4. **Monitoring and Logging:**
   - Monitoring and logging solutions such as Splunk and SignalFx should be integrated to track service health, performance metrics, and troubleshoot issues in real-time. Monitoring ensures proactive detection of anomalies and performance bottlenecks.

5. **Security Considerations:**
   - Implement security best practices such as encryption of sensitive data, role-based access control (RBAC), and secure communication using HTTPS. 

6. **Scalability and Elasticity:**
   - Design the service architecture to be horizontally scalable, allowing it to handle increasing loads by adding more instances or containers dynamically. Autoscaling configurations can be set up to automatically adjust the number of service replicas based on resource utilization metrics.

7. **High Availability (HA):**
   - Deploy the service across multiple availability zones or regions to ensure fault tolerance and high availability. Load balancers and health checks should be configured to distribute traffic evenly and route requests to healthy instances.

## Architectural/Workflow Diagram
![diagram.png](src%2Fmain%2Fresources%2Fdiagram.png)
