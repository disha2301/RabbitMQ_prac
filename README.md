# 🐇 Spring Boot RabbitMQ Messaging Service

A simple Spring Boot application demonstrating how to publish messages to RabbitMQ using AMQP protocol. This project simulates placing an order through a REST API and sending the order status via RabbitMQ.

---

## 📦 Project Structure
```
├── src
│ ├── main
│ │ ├── java
│ │ │ └── com.gevernova.rabbitmq_practice
│ │ │ ├── config # Messaging configurations (Queue, Exchange, Binding, etc.)
│ │ │ ├── entity # Entity classes (Order, OrderStatus)
│ │ │ ├── producer # REST controller to publish messages
│ │ │ └── RabbitmqPracticeApplication.java
│ │ └── resources
│ │ ├── application.properties # RabbitMQ connection settings
├── pom.xml
```

---

## 🚀 Technologies Used

- Java 17
- Spring Boot 3.5
- Spring AMQP (RabbitTemplate)
- RabbitMQ
- Maven

---

## 🛠️ Setup Instructions

### Prerequisites

- Java 17+
- Maven
- RabbitMQ running on `localhost:5672`

## Clone the Repository

```bash
git clone https://github.com/your-username/rabbitmq_practice.git
cd rabbitmq_practice
```
## Run the Application
```bash
mvn spring-boot:run
```
Application starts on port 9090

## 📬 API Usage

### POST `/orders/{restaurantName}`
Send an order message to RabbitMQ.

---

### 📝 Request Body

```json
{
  "name": "Pizza",
  "qty": 2,
  "price": 499
}
```
## 💡 How it Works

1. The controller receives an order request.
2. A UUID is generated and an `OrderStatus` object is created.
3. The `OrderStatus` is sent to the queue using `RabbitTemplate`.
4. The message is routed through a Topic Exchange using a Routing Key.
5. Consumers listening to the bound queue can receive and process the message.

## 📁 MessagingConfig Highlights

```bash
@Bean
public Queue queue() {
    return new Queue("rabbitmq_queue");
}

@Bean
public TopicExchange exchange() {
    return new TopicExchange("rabbitmq_exchange");
}

@Bean
public Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("rabbitmq_routingkey");
}
```
## 📬 Message Example

```bash
{
  "order": {
    "orderId": "f75a2...aa3",
    "name": "Pizza",
    "qty": 2,
    "price": 499
  },
  "status": "PROCESS",
  "message": "order placed successfully in Dominos"
}
```



