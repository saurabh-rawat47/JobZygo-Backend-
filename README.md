# JobZygo

A full-stack job search and recruitment platform built with modern web technologies.

## 🚀 Project Overview

JobZygo is a comprehensive job search and recruitment platform that connects job seekers with employers. The application features a modern, responsive interface and robust backend services.

## 🛠️ Technology Stack

### Backend
- **Framework**: Spring Boot (Java)
- **Database**: MongoDB
- **Authentication**: OAuth 2.0 (Google)
- **Build Tool**: Maven (implied from project structure)

### Frontend
- **Framework**: Next.js 15.4.6
- **Language**: TypeScript
- **UI Library**: React 19.1.0
- **Styling**: Tailwind CSS
- **Form Handling**: React Hook Form with Zod validation
- **Icons**: Lucide React
- **HTTP Client**: Axios

## 📁 Project Structure

```
JobZygo/
├── frontend/                 # Next.js frontend application
│   ├── src/                 # Source code
│   ├── public/              # Static assets
│   ├── package.json         # Frontend dependencies
│   └── README.md           # Frontend-specific documentation
├── src/                     # Backend source code
│   ├── main/
│   │   ├── java/com/tony/JobZygo/
│   │   │   ├── config/      # Configuration classes
│   │   │   ├── controller/  # REST controllers
│   │   │   ├── entity/      # Data models
│   │   │   ├── filter/      # Request/response filters
│   │   │   ├── repo/        # Data repositories
│   │   │   ├── service/     # Business logic
│   │   │   └── JobZygoApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                # Test files
└── README.md               # This file
```

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Node.js 18 or higher
- MongoDB database
- Google OAuth credentials

### Backend Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd JobZygo
   ```

2. **Configure the database**
   - Update `src/main/resources/application.properties` with your MongoDB connection string
   - Configure Google OAuth credentials

3. **Run the Spring Boot application**
   ```bash
   # Using Maven wrapper (if available)
   ./mvnw spring-boot:run
   
   # Or using Maven directly
   mvn spring-boot:run
   ```

   The backend will start on `http://localhost:8080`

### Frontend Setup

1. **Navigate to the frontend directory**
   ```bash
   cd frontend
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Start the development server**
   ```bash
   npm run dev
   ```

   The frontend will start on `http://localhost:3000`

## 🔧 Configuration

### Environment Variables

The application uses the following configuration in `application.properties`:

- `spring.application.name`: Application name
- `spring.data.mongodb.uri`: MongoDB connection string
- `spring.data.mongodb.database`: Database name
- `spring.security.oauth.client.registration.google.client-id`: Google OAuth client ID
- `spring.security.oauth.client.registration.google.client-secret`: Google OAuth client secret

### Frontend Configuration

The frontend uses Next.js configuration files:
- `next.config.ts`: Main Next.js configuration
- `tsconfig.json`: TypeScript configuration
- `tailwind.config.js`: Tailwind CSS configuration

## 📝 Available Scripts

### Backend
- `mvn spring-boot:run`: Start the Spring Boot application
- `mvn test`: Run tests
- `mvn clean install`: Build the project

### Frontend
- `npm run dev`: Start development server with Turbopack
- `npm run build`: Build for production
- `npm run start`: Start production server
- `npm run lint`: Run ESLint

## 🔐 Authentication

The application uses Google OAuth 2.0 for authentication. Make sure to:
1. Set up a Google Cloud Project
2. Configure OAuth 2.0 credentials
3. Update the client ID and secret in `application.properties`

## 🗄️ Database

The application uses MongoDB as the primary database. The connection is configured through the MongoDB URI in the application properties.

## 🧪 Testing

- Backend tests are located in `src/test/`
- Frontend tests can be added using Next.js testing utilities

## 📦 Deployment

### Backend Deployment
The Spring Boot application can be deployed to various platforms:
- AWS, Google Cloud, or Azure
- Heroku
- Docker containers

### Frontend Deployment
The Next.js application can be deployed to:
- Vercel (recommended)
- Netlify
- AWS Amplify
- Any static hosting service

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Authors

- **Tony** - Initial work

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Next.js team for the React framework
- MongoDB for the database solution
