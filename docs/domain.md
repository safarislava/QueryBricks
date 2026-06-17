```mermaid
classDiagram
    class Order {
        +UUID id
        +OrderStatus status
        +place()
        +cancel()
    }
    class Customer {
        +String name
        +String email
    }
    Customer --> Order
```