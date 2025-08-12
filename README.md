# JobZygo Backend

A Spring Boot REST API backend for a job posting platform that allows users to create, search, and manage job postings.

## 🚀 Features

- **User Authentication**: Sign up and login functionality
- **Job Posting Management**: Create and retrieve job postings
- **Search Functionality**: Search jobs by text content
- **MongoDB Integration**: NoSQL database for flexible data storage
- **RESTful API**: Clean REST endpoints for frontend integration
- **CORS Support**: Configured for frontend applications

## 🛠️ Tech Stack

- **Framework**: Spring Boot
- **Database**: MongoDB
- **Language**: Java
- **Build Tool**: Maven (implied by Spring Boot structure)
- **Authentication**: Custom authentication system

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/tony/JobZygo/
│   │   ├── config/          # Configuration classes
│   │   ├── controller/      # REST controllers
│   │   ├── entity/          # Data models
│   │   ├── filter/          # Request filters
│   │   ├── repo/            # Data repositories
│   │   ├── service/         # Business logic
│   │   └── JobZygoApplication.java
│   └── resources/
│       └── application.properties
└── test/                    # Test files
```

## 🗄️ Data Models

### User
- User authentication and profile management
- Sign up and login functionality

### JobPost
- Job posting information including:
  - Profile/Position title
  - Experience requirements
  - Job type (Full-time, Part-time, etc.)
  - Company name
  - Job description
  - Salary information
  - Location
  - Required technologies/skills

## 🔌 API Endpoints

### Authentication
- `POST /api/auth/signup` - User registration
- `POST /api/auth/login` - User login

### Job Postings
- `GET /api/jobs` - Get all job postings
- `GET /api/jobs/search/{text}` - Search jobs by text
- `POST /api/jobs` - Create a new job posting

## ⚙️ Configuration

The application is configured via `application.properties`:

```properties
spring.application.name=JobZygo
spring.data.mongodb.uri=mongodb+srv://...
spring.data.mongodb.database=tony
spring.data.mongodb.auto-index-creation=true
```





## 👨‍💻 Author

**Saurabh** - *Initial work*

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- MongoDB for the flexible NoSQL database
- All contributors to this project
