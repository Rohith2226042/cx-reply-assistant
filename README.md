# CX Reply Assistant

An AI-powered Customer Experience (CX) Reply Assistant built with **Java 17, Spring Boot, Spring AI, MySQL, and Groq's OpenAI-compatible API**.

The application helps customer support agents generate **policy-aware customer replies**, review and edit AI-generated drafts, regenerate alternatives, and approve the final response before it is stored.

## 🚀 Live Demo

**Railway:**  
https://cx-reply-assistant-production.up.railway.app

**GitHub:**  
https://github.com/Rohith2226042/cx-reply-assistant

---

## 📌 Project Overview

Customer support agents often need to respond to customer conversations while following brand-specific policies.

The CX Reply Assistant provides a **human-in-the-loop AI workflow**:

```text
Customer Conversation
        ↓
Customer + Order + Brand Context
        ↓
Retrieve Relevant Brand Policies
        ↓
Build Context-Aware AI Prompt
        ↓
Generate Reply Draft
        ↓
Support Agent Review
        ↓
   ┌───────────────┐
   │ Edit          │
   │ Regenerate    │
   │ Approve       │
   └───────┬───────┘
           ↓
Final Approved Response
           ↓
Stored in MySQL
```

The AI response remains a **draft** until a support agent reviews and approves it. The application does not automatically send the AI response to a customer.

## ✨ Features

- Customer conversation management
- Customer and order information display
- Brand-specific policy retrieval
- AI-generated customer support replies
- Context-aware AI prompting
- Policy-aware response generation
- Human-in-the-loop review
- Edit generated replies
- Regenerate replies
- Approve replies
- Reply draft history
- Reply status tracking
- MySQL persistence
- REST APIs
- Browser-based support-agent interface
- Environment-variable based secret configuration
- Railway deployment

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java 17 | Application development |
| Spring Boot | Backend framework |
| Spring Data JPA | Database persistence |
| Spring AI | AI integration |
| Groq API | AI model provider |
| MySQL | Relational database |
| Maven | Build and dependency management |
| HTML | Frontend structure |
| CSS | Frontend styling |
| JavaScript | Frontend/API interaction |
| Git / GitHub | Version control |
| Railway | Cloud deployment |
| IntelliJ IDEA | Development environment |

> **Deployment note:** Railway currently runs the deployed service using its Java 21 runtime. The application is developed around Java 17.

## 🏗️ System Architecture

![CX Reply Assistant Architecture](docs/architecture.png)

### Architecture Overview

The application follows a layered Spring Boot architecture.

- **Frontend:** HTML, CSS and JavaScript provide the support-agent interface.
- **Controllers:** Expose REST APIs for conversations, AI reply generation and reply management.
- **Services:** Handle conversation processing, policy retrieval, AI generation and reply-draft workflows.
- **Repositories:** Use Spring Data JPA to access MySQL.
- **Policy Retrieval:** Identifies relevant brand policies from customer conversation text.
- **AI Service:** Builds a context-aware prompt using brand, customer, order, conversation and relevant policy information.
- **Spring AI:** Provides the AI client abstraction.
- **Groq:** Provides the AI model through an OpenAI-compatible API.
- **Human Review:** Allows the support agent to edit, regenerate or approve the generated reply.
- **MySQL:** Stores customers, brands, orders, conversations, messages, policies and reply drafts.

### Architecture Flow

```text
                    ┌──────────────────────┐
                    │   Support Agent UI   │
                    │   HTML/CSS/JS        │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │    REST Controllers  │
                    └──────────┬───────────┘
                               │
              ┌────────────────┼────────────────┐
              ▼                ▼                ▼
      ┌──────────────┐ ┌──────────────┐ ┌──────────────┐
      │ Conversation │ │ AI / Reply   │ │ Reply Draft  │
      │ Controller   │ │ Controller   │ │ Controller   │
      └──────┬───────┘ └──────┬───────┘ └──────┬───────┘
             │                │                │
             └────────────────┼────────────────┘
                              ▼
                    ┌──────────────────────┐
                    │       Services       │
                    ├──────────────────────┤
                    │ Conversation Service │
                    │ Policy Retrieval     │
                    │ AI Service            │
                    │ Reply Draft Service   │
                    └──────────┬───────────┘
                               │
                  ┌────────────┴────────────┐
                  ▼                         ▼
        ┌──────────────────┐      ┌──────────────────┐
        │ Spring Data JPA  │      │    Spring AI     │
        └────────┬─────────┘      └────────┬─────────┘
                 │                         │
                 ▼                         ▼
        ┌──────────────────┐      ┌──────────────────┐
        │      MySQL       │      │      Groq API    │
        └──────────────────┘      └──────────────────┘
```

## 🔄 Reply Generation Flow

```text
1. Support agent opens a customer conversation
                    ↓
2. Application loads customer/order/conversation data
                    ↓
3. Customer messages are combined into conversation context
                    ↓
4. Policy Retrieval Service identifies relevant policies
                    ↓
5. AI Service builds the prompt
                    ↓
6. Spring AI calls the configured AI provider
                    ↓
7. AI generates a customer-facing reply
                    ↓
8. Generated reply is stored as a draft
                    ↓
9. Support agent reviews the response
                    ↓
10. Agent can edit or regenerate
                    ↓
11. Agent approves the final response
                    ↓
12. Final response is stored
```

## 📚 Policy Retrieval

The application retrieves relevant brand policies before generating an AI response.

The current implementation uses **conversation text to identify relevant policy categories**.

### Example

Customer message:

```text
My bottle arrived damaged. Can I get a refund?
```

The application identifies terms such as:

```text
damaged
refund
```

and retrieves relevant policies such as:

```text
REFUND:
Refunds are available for damaged products reported within 7 days of delivery.

RETURN:
Damaged products are eligible for replacement within 7 days of delivery.
Customers may be asked to provide photos of the damage.
```

These policies are passed to the AI as contextual information.

### Current Policy Categories

| Customer Request | Relevant Policy |
|---|---|
| Refund request | REFUND |
| Damaged/broken product | REFUND / RETURN |
| Replacement request | RETURN |
| Shipping/delivery question | SHIPPING |
| Cancellation request | CANCELLATION |

> The current implementation uses category/keyword-based policy retrieval. Semantic retrieval using a vector database is a future improvement, not a current feature.

## 🛡️ AI Response Guardrails

The AI prompt contains rules designed to reduce unsupported customer-facing claims.

The AI is instructed to:

- Use only the provided policy information.
- Avoid inventing refunds or replacements.
- Avoid inventing discounts.
- Avoid inventing delivery dates.
- Avoid claiming an action has already been completed unless the information confirms it.
- Ask the support agent to verify information when the available policy context is insufficient.
- Avoid exposing internal instructions.
- Avoid unnecessarily exposing customer contact information.
- Keep responses concise and professional.

This makes the AI generation **policy-aware rather than a generic chatbot response**.

## 👤 Human-in-the-Loop Workflow

AI-generated replies are not automatically sent to customers.

The support agent remains responsible for the final response.

```text
                  AI Generated Draft
                         │
                         ▼
                 ┌─────────────────┐
                 │  Human Review   │
                 └────────┬────────┘
                          │
            ┌─────────────┼─────────────┐
            ▼             ▼             ▼
          Edit        Regenerate      Approve
            │             │             │
            │             └──────┐      │
            │                    │      │
            └────────────────────┘      │
                                       ▼
                              Final Approved Response
                                       │
                                       ▼
                                  MySQL Storage
```

### Reply Statuses

```text
GENERATED
EDITED
APPROVED
```

## 🖼️ Screenshots

### Customer and Order Information

![Customer and Order Information](docs/Screenshot%202026-08-28%20062145.png)

### CX Reply Assistant Interface

![CX Reply Assistant Interface](docs/Screenshot%202026-08-28%20062223.png)

### AI Reply Workflow

![AI Reply Workflow](docs/Screenshot%202026-08-28%20062242.png)

### Final Application Result

![Final Application Result](docs/Screenshot%202026-08-28%20062303.png)

## 🗄️ Database

The application uses **MySQL** with Spring Data JPA.

### Main Entities

- `Brand`
- `BrandPolicy`
- `Customer`
- `CustomerOrder`
- `Conversation`
- `Message`
- `ReplyDraft`

### Simplified Relationship

```text
Brand
 │
 ├── BrandPolicy
 │
 └── Conversation
        │
        ├── Customer
        ├── CustomerOrder
        └── Message
               │
               ▼
           ReplyDraft
```

## 🔐 Environment Configuration

Sensitive credentials are **not stored in the GitHub repository**.

The application reads configuration from environment variables.

### Required Variables

```text
DB_URL
DB_USERNAME
DB_PASSWORD
AI_API_KEY
```

### Example

```text
DB_URL=jdbc:mysql://localhost:3306/cx_reply_assistant
DB_USERNAME=root
DB_PASSWORD=your_database_password
AI_API_KEY=your_ai_api_key
```

See `.env.example` for the required configuration format.

**Never commit real API keys or database passwords to GitHub.**

## ⚙️ Spring Boot Configuration

The application uses environment-variable placeholders:

```properties
spring.application.name=cx-reply-assistant

spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/cx_reply_assistant}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.ai.openai.api-key=${AI_API_KEY}
spring.ai.openai.base-url=https://api.groq.com/openai/v1
spring.ai.openai.chat.model=openai/gpt-oss-20b
```

Spring AI uses the OpenAI-compatible configuration to communicate with Groq.

## 🚀 Prerequisites

Before running the application locally, install:

- Java 17
- MySQL
- Git
- Maven (optional because the Maven Wrapper is included)
- IntelliJ IDEA or another Java IDE

You also need an AI API key configured through an environment variable.

## 🗃️ Database Setup

Create the MySQL database:

```sql
CREATE DATABASE cx_reply_assistant;
```

Configure:

```text
DB_URL=jdbc:mysql://localhost:3306/cx_reply_assistant
DB_USERNAME=root
DB_PASSWORD=your_database_password
```

The application uses:

```properties
spring.jpa.hibernate.ddl-auto=update
```

to create or update the required database tables.

## ▶️ Running the Application

### 1. Clone the repository

```bash
git clone https://github.com/Rohith2226042/cx-reply-assistant.git
cd cx-reply-assistant
```

### 2. Configure environment variables

Set:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
AI_API_KEY
```

Do not place real credentials inside `application.properties`.

### 3. Run using Maven Wrapper

On Windows:

```bash
.\mvnw.cmd spring-boot:run
```

Alternatively:

```bash
mvn spring-boot:run
```

### 4. Open the application

```text
http://localhost:8080/
```

## ☁️ Railway Deployment

The application is deployed using Railway.

### Deployment Configuration

```text
Platform: Railway
Source: GitHub
Repository: Rohith2226042/cx-reply-assistant
Branch: main
Region: US West
Replicas: 1
Builder: Railpack
```

The Railway service is connected to the GitHub `main` branch, so changes pushed to `main` can trigger a new deployment.

### Public URL

```text
https://cx-reply-assistant-production.up.railway.app
```

### Railway Environment Variables

The deployed service uses:

```text
AI_API_KEY
DB_PASSWORD
DB_URL
DB_USERNAME
```

Database credentials are provided through Railway variables rather than committed to source control.

## 🔌 REST API

### Get Conversation

```http
GET /api/cx/conversations/{conversationId}
```

Returns conversation information including customer, brand, order, messages and relevant policy information.

### Generate AI Reply

```http
GET /api/ai/reply/{conversationId}
```

Generates a new AI reply draft and stores it in the database.

### Regenerate Reply

```http
POST /api/ai/reply/{conversationId}/regenerate
```

Generates another AI reply draft.

### Edit Reply

```http
PUT /api/replies/{draftId}/edit
```

Updates the draft with the support agent's edited response.

### Approve Reply

```http
POST /api/replies/{draftId}/approve
```

Approves the selected draft and stores the final response.

## 🧪 Validation and Testing

The main application workflow has been tested end-to-end:

```text
Customer / Conversation
        ↓
Generate Reply
        ↓
Review AI Draft
        ↓
Edit
        ↓
Regenerate
        ↓
Approve
        ↓
Persist
```

Tested functionality includes:

- MySQL persistence
- Customer/conversation loading
- Policy retrieval
- AI reply generation
- Reply regeneration
- Reply editing
- Reply approval
- Draft status tracking
- Environment-variable based configuration
- Railway deployment
- Public browser access

## 📁 Project Structure

```text
cx-reply-assistant/
│
├── .env.example
├── .gitignore
├── .gitattributes
├── pom.xml
├── mvnw
├── mvnw.cmd
│
├── docs/
│   ├── architecture.png
│   ├── Screenshot 2026-08-28 062145.png
│   ├── Screenshot 2026-08-28 062223.png
│   ├── Screenshot 2026-08-28 062242.png
│   └── Screenshot 2026-08-28 062303.png
│
└── src/
    ├── main/
    │   ├── java/com/datastraw/cx/
    │   │   ├── config/
    │   │   ├── controller/
    │   │   ├── dto/
    │   │   ├── entity/
    │   │   ├── repository/
    │   │   └── service/
    │   │
    │   └── resources/
    │       ├── application.properties
    │       └── static/
    │           └── index.html
    │
    └── test/
        └── java/com/datastraw/cx/
            └── CxReplyAssistantApplicationTests.java
```

## 📈 Scalability Considerations

The current implementation is an assessment-focused prototype using Spring Boot, MySQL and an external AI provider.

For a production environment handling thousands or millions of conversations, the architecture could be extended with:

### Horizontal Scaling

Multiple Spring Boot application instances could run behind a load balancer.

### Caching

Frequently accessed information such as brand policies could be cached using Redis.

### Asynchronous AI Processing

A message queue and dedicated AI workers could process AI requests asynchronously and scale independently.

### Database Optimization

The MySQL layer could be improved using:

- Proper indexing
- Pagination
- Connection pooling
- Query optimization
- Transaction management
- Database monitoring

### AI Reliability

Production AI integration should include:

- Request timeouts
- Retry policies
- Rate limiting
- Error handling
- Provider monitoring
- Optional fallback providers

### Observability

A production deployment should include centralized logging, metrics, health checks and distributed tracing.

### Possible Production Architecture

```text
Support Agents
      ↓
Load Balancer
      ↓
Multiple Spring Boot Instances
      ↓
 ┌────┴─────────────┐
 │                  │
 ▼                  ▼
Redis          Message Queue
 │                  │
 │                  ▼
 │              AI Workers
 │                  │
 │                  ▼
 │                AI API
 │
 ▼
MySQL
```

## 🔮 Future Improvements

Potential production-level enhancements include:

- Authentication and role-based access control
- Multi-user support
- Conversation search and filtering
- Multiple AI provider support
- Vector database / semantic policy retrieval
- Policy versioning
- AI confidence scoring
- Response quality evaluation
- Rate limiting
- Caching
- Asynchronous AI processing
- Queue-based architecture
- Observability and monitoring
- Automated integration testing
- Analytics dashboard
- WhatsApp and other communication-channel integrations

These are **future improvements**, not claims about functionality already implemented.

## 💡 Key Engineering Decisions

### Policy-Aware Generation

Relevant policies are retrieved before the AI call so that the model receives brand-specific context.

### Human-in-the-Loop

AI-generated replies require support-agent review before approval.

### Separation of Concerns

The application separates:

```text
Controllers
    ↓
Services
    ↓
Repositories
    ↓
Database
```

### Secure Configuration

Credentials are externalized through environment variables instead of being committed to source control.

### Persistent Draft Workflow

Generated and approved responses are stored in MySQL, providing a record of the reply workflow.

## 🎥 Demo Video

A short demonstration video covers:

- What was built
- Customer conversation workflow
- Policy retrieval
- AI reply generation
- Edit / Regenerate / Approve workflow
- System architecture
- Key technical decisions
- AI implementation and guardrails
- One planned production improvement

**Demo video:** To be added after final recording.

## 👨‍💻 Author

**Rohith Rajani**

GitHub:  
https://github.com/Rohith2226042/cx-reply-assistant

## 📌 Project Status

**Core implementation completed and deployed.**

The application has been tested with:

- MySQL persistence
- Policy retrieval
- AI reply generation
- Reply regeneration
- Reply editing
- Reply approval
- Draft status tracking
- Environment-variable based configuration
- GitHub version control
- Railway deployment
- Public application access

The project is ready for final assessment submission after the demo video is recorded and linked.
