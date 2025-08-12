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

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven
- MongoDB instance

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd JobZygo-Backend
   ```

2. **Configure MongoDB**
   - Update the MongoDB connection string in `src/main/resources/application.properties`
   - Ensure your MongoDB instance is running

3. **Build and run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the application**
   - API will be available at `http://localhost:8080`
   - Swagger UI (if configured) at `http://localhost:8080/swagger-ui.html`

## 🔧 Development

### Running Tests
```bash
mvn test
```

### Building for Production
```bash
mvn clean package
```

## 🌐 CORS Configuration

The application is configured to accept requests from:
- `http://localhost:3000`
- `http://192.168.1.37:3000`

## 📝 API Documentation

### Create a Job Posting
```bash
POST /api/jobs
Content-Type: application/json

{
  "profile": "Software Engineer",
  "exp": 3,
  "jobType": "Full-time",
  "companyName": "Tech Corp",
  "desc": "We are looking for a skilled software engineer...",
  "salary": 80000,
  "location": "New York, NY",
  "techs": ["Java", "Spring Boot", "MongoDB"]
}
```

### Search Jobs
```bash
GET /api/jobs/search/software
```

### User Registration
```bash
POST /api/auth/signup
Content-Type: application/json

{
  "username": "user@example.com",
  "password": "password123"
}
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

**Tony** - *Initial work*

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- MongoDB for the flexible NoSQL database
- All contributors to this project
