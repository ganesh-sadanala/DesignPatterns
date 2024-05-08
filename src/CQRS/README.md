- Separating Read and Write Queries of the domain model.
- Can take a further step and have different datastores for reads and writes and have a mechanism
  in place to keep them in sync.
- Issues in No separation designs
  - The reads and writes happen over the same domain model. Reads and writes can have different loads and requirements.
  - Requires a mechanism to optimize our domain model and the underlying storage for them to suit the individual needs
    of the read and write operations.
- **CQRS**
  - Separation of Read and Write concerns on domain model.
  - CQRS can benefit from the following Domain-Driven Design(DDD) principles.
    - **Aggregate/Aggregator:** It is a pattern in DDD that logically groups different entities by binding entities
    to an aggregate root. This pattern provides transactional consistency between the entities.
    CQRS benefit from Aggregator pattern, which groups the write domain model, providing transactional guarantees. 
    Aggregates normally hold a cache state for better performance but can work perfectly without it.
    - **Projection/Projector:** Projection means representing domain objects in different shapes and structures.
    These projections of original data are read-only and highly optimized to provide an enhanced read experience.
    We can cache projections for better performance.
    - 