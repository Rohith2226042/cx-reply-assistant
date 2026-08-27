# CX Reply Assistant

An AI-powered Customer Experience (CX) Reply Assistant built with Spring Boot, Spring AI, MySQL, and a Groq OpenAI-compatible API.

The application helps customer support agents generate policy-aware customer replies, review/edit them, regenerate alternatives, and approve the final response.

---

## Features

- Customer conversation management
- Customer and order information display
- Brand-specific policy retrieval
- AI-generated customer support replies
- Policy-aware response generation
- Human review and editing workflow
- Reply regeneration
- Reply approval workflow
- Draft history stored in MySQL
- REST APIs for conversation and reply management
- Simple browser-based support-agent interface

---

## Technology Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Spring AI
- MySQL
- Groq API
- OpenAI-compatible API integration
- Maven
- HTML, CSS and JavaScript
- IntelliJ IDEA
- Git / GitHub

---

## Application Architecture

```text
                    ┌──────────────────────┐
                    │   Support Agent UI   │
                    │   HTML/CSS/JavaScript│
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │   Spring Boot REST   │
                    │     Controllers      │
                    └──────────┬───────────┘
                               │
              ┌────────────────┼────────────────┐
              │                │                │
              ▼                ▼                ▼
       ┌─────────────┐ ┌───────────────┐ ┌─────────────┐
       │ Conversation│ │ Policy        │ │ Reply Draft │
       │ Service     │ │ Retrieval     │ │ Service     │
       └──────┬──────┘ └───────┬───────┘ └──────┬──────┘
              │                │                │
              └────────────────┼────────────────┘
                               ▼
                       ┌───────────────┐
                       │   AI Service  │
                       │  Spring AI    │
                       └───────┬───────┘
                               │
                               ▼
                     ┌──────────────────┐
                     │ Groq OpenAI API  │
                     └──────────────────┘

                               │
                               ▼
                     ┌──────────────────┐
                     │      MySQL       │
                     │ Customer         │
                     │ Orders           │
                     │ Conversations    │
                     │ Messages         │
                     │ Brand Policies   │
                     │ Reply Drafts     │
                     └──────────────────┘
```

---

## Reply Generation Flow

```text
Customer Conversation
        │
        ▼
Collect Customer Messages
        │
        ▼
Retrieve Relevant Brand Policies
        │
        ▼
Build AI Prompt
        │
        ├── Brand information
        ├── Customer information
        ├── Order information
        ├── Conversation history
        └── Relevant policies
        │
        ▼
Spring AI
        │
        ▼
Groq AI Model
        │
        ▼
Generated Reply
        │
        ▼
Human Review
        │
        ├── Edit
        ├── Regenerate
        └── Approve
        │
        ▼
Final Response Stored
```

---

## Policy Retrieval

The application retrieves policies based on the customer's conversation.

Examples:

| Customer Request | Relevant Policies |
|---|---|
| Refund for damaged product | REFUND |
| Replacement for broken product | RETURN |
| Delivery/shipping question | SHIPPING |
| Cancellation request | CANCELLATION |

The retrieved policy information is passed to the AI as context.

The AI prompt instructs the model to use only the provided policy information and avoid inventing refunds, replacements, discounts, delivery promises, or other unsupported actions.

---

## Database

The application uses MySQL.

Main entities include:

- Brand
- BrandPolicy
- Customer
- CustomerOrder
- Conversation
- Message
- ReplyDraft

Example relationship:

```text
Brand
  │
  ├── Brand Policies
  │
  └── Conversations
          │
          ├── Customer
          ├── Order
          └── Messages
                  │
                  ▼
              Reply Drafts
```

---

## Environment Configuration

Sensitive credentials are not stored in the repository.

The application reads configuration from environment variables.

Required variables:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
AI_API_KEY
```

Example:

```text
DB_URL=jdbc:mysql://localhost:3306/cx_reply_assistant
DB_USERNAME=root
DB_PASSWORD=your_database_password
AI_API_KEY=your_ai_api_key
```

See `.env.example` for the required configuration format.

**Never commit real API keys or database passwords to GitHub.**

---

## Spring Boot Configuration

The application uses environment-variable placeholders:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/cx_reply_assistant}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}

spring.ai.openai.api-key=${AI_API_KEY}
spring.ai.openai.base-url=https://api.groq.com/openai/v1
spring.ai.openai.chat.model=openai/gpt-oss-20b
```

The AI integration uses Spring AI's OpenAI-compatible configuration to communicate with Groq.

---

## Prerequisites

Before running the application locally, install:

- Java 17
- MySQL
- Maven (optional because Maven Wrapper is included)
- Git

You also need a Groq API key.

---

## Database Setup

Create the MySQL database:

```sql
CREATE DATABASE cx_reply_assistant;
```

Configure the database environment variables:

```text
DB_URL=jdbc:mysql://localhost:3306/cx_reply_assistant
DB_USERNAME=root
DB_PASSWORD=your_database_password
```

The application uses JPA/Hibernate to create or update the required tables.

---

## Running the Application

### 1. Clone the repository

```bash
git clone https://github.com/Rohith2226042/cx-reply-assistant.git
```

### 2. Enter the project

```bash
cd cx-reply-assistant
```

### 3. Configure environment variables

Set:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
AI_API_KEY
```

### 4. Run the application

Using Maven Wrapper on Windows:

```bash
.\mvnw.cmd spring-boot:run
```

Or run:

```bash
mvn spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

---

## REST API

### Get Conversation

```http
GET /api/cx/conversations/{conversationId}
```

Returns customer, brand, order, messages, and policy information.

### Generate AI Reply

```http
GET /api/ai/reply/{conversationId}
```

Generates and stores a new reply draft.

### Regenerate Reply

```http
POST /api/ai/reply/{conversationId}/regenerate
```

Generates another reply draft.

### Edit Reply

```http
PUT /api/replies/{draftId}/edit
```

Updates the draft with the human-edited response.

### Approve Reply

```http
POST /api/replies/{draftId}/approve
```

Approves the selected draft and stores the final response.

---

## Human-in-the-Loop Workflow

The system does not automatically send AI responses to customers.

Instead:

```text
AI generates draft
       ↓
Support agent reviews
       ↓
Support agent edits if required
       ↓
Support agent regenerates if required
       ↓
Support agent approves
       ↓
Final response stored
```

This provides human oversight before a response becomes the approved final response.

---

## Example

Customer message:

```text
My bottle arrived damaged. Can I get a refund?
```

Relevant policy:

```text
REFUND:
Refunds are available for damaged products reported within
7 days of delivery.
```

The AI uses the conversation, order details, brand information, and retrieved policy context to generate a customer-facing reply.

The support agent can then:

- Review the response
- Edit it
- Regenerate it
- Approve it

---

## Security

Sensitive configuration is externalized through environment variables.

The repository does not contain:

- Real AI API keys
- Database passwords
- `.env` files
- IDE configuration
- Build output

`.env.example` is provided as a configuration template.

---

## Project Structure

```text
cx-reply-assistant/
│
├── .env.example
├── .gitignore
├── pom.xml
├── mvnw
├── mvnw.cmd
│
└── src/
    ├── main/
    │   ├── java/com/datastraw/cx/
    │   │
    │   └── resources/
    │       ├── application.properties
    │       └── static/
    │           └── index.html
    │
    └── test/
```

---

## Future Improvements

Possible future enhancements include:

- Authentication and role-based access
- Conversation search
- Multiple AI providers
- Vector database / semantic policy retrieval
- Confidence scoring
- Analytics dashboard
- Response quality evaluation
- Production deployment
- Automated testing expansion
- Integration with WhatsApp or other customer communication channels

---

## Author

**Rohith Rajani**

GitHub:  
https://github.com/Rohith2226042

---

## License

This project was developed as part of an assessment/project submission.