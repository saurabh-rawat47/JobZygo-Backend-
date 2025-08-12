# JobZygo - Job Portal Platform

JobZygo is a modern full-stack job portal application that connects job seekers with employers. Built with Spring Boot backend and Next.js frontend, it demonstrates enterprise-level web application development with modern technologies and best practices.

## 🚀 Project Overview

A comprehensive job portal platform featuring secure user authentication, job management, and search capabilities. The application showcases full-stack development skills with industry-standard technologies and architectural patterns.

## ✨ Key Features

- **🔐 Secure Authentication**: JWT-based authentication system with Google OAuth 2.0 integration
- **📋 Job Management**: Complete CRUD operations for job postings with data models
- **🔍 Job Search**: Dynamic job search with filters for location, skills, and experience level
- **👥 User Management**: Role-based user system supporting job seekers and employers
- **📱 Responsive Design**: Mobile-first UI built with React patterns and Tailwind CSS
- **🛡️ Security Features**: Password encryption, protected routes, and CORS configuration

## 🏗️ Architecture & Technologies

### Backend (Spring Boot - Primary Focus)
- **Framework**: Spring Boot with Spring MVC
- **Database**: MongoDB with Spring Data MongoDB
- **Security**: Spring Security with JWT authentication
- **API Design**: RESTful web services with proper HTTP methods
- **Authentication**: Google OAuth 2.0 integration
- **Architecture**: Layered architecture (Controller → Service → Repository)

### Frontend (Next.js - AI Assisted)
- **Framework**: Next.js 15.4.6 with App Router and TypeScript
- **Styling**: Tailwind CSS for modern, responsive design
- **UI Components**: Headless UI components and Lucide React icons
- **Form Management**: React Hook Form with Zod validation
- **State Management**: React hooks and local storage
- **HTTP Client**: Axios for API communication
- **Development**: Built with assistance from Cursor AI

### Database Design
- **MongoDB Collections**: Users, JobPosts with indexed fields
- **Data Relationships**: User-to-JobPost associations
- **Indexing**: Optimized queries with email and username indexes
- **Validation**: Schema validation at both frontend and backend levels

## 📊 Technical Highlights

### Backend Architecture
```
com.tony.JobZygo/
├── controller/     # REST API endpoints
├── service/        # Business logic layer
├── repo/           # Data access layer
├── entity/         # Data models
├── config/         # Security & app configuration
└── filter/         # JWT authentication filters
```

### API Endpoints
- **Authentication**: `/auth/login`, `/auth/signup`, `/oauth/google`
- **Job Management**: CRUD operations on `/jobzygo/jobs`
- **Search**: Advanced search at `/jobzygo/search/{keyword}`
- **User Management**: Profile operations

### Security Implementation
- JWT token-based stateless authentication
- BCrypt password hashing
- CORS configuration for cross-origin requests
- Protected routes with role-based access
- OAuth 2.0 integration framework

### Data Models

**JobPost Entity**:
- Profile, experience level, job type
- Company information and salary details
- Location and required technologies array
- MongoDB ObjectId with automated indexing

**User Entity**:
- Unique username and email constraints
- Password encryption
- User type differentiation (Job Seeker/Employer)
- OAuth integration support

## 🛠️ Development Practices

- **Clean Architecture**: Separation of concerns with layered design
- **REST API Design**: Proper HTTP status codes and response structures
- **Error Handling**: Comprehensive exception handling and validation
- **Security Best Practices**: Input validation, secure headers, and authentication
- **Modern Frontend**: Component-based architecture with TypeScript
- **Responsive Design**: Mobile-first approach with Tailwind CSS
- **Code Quality**: Structured packages and clear naming conventions

## 🎯 Technical Skills Demonstrated

### Backend Development (Core Expertise)
- **Spring Boot Ecosystem**: Comprehensive understanding of Spring framework
- **Spring Security**: JWT authentication and authorization implementation
- **Spring Data MongoDB**: NoSQL database integration and optimization
- **RESTful API Development**: Design and implementation of web services
- **Exception Handling**: Comprehensive error management and validation
- **Layered Architecture**: Clean separation of concerns and maintainable code
- **OAuth 2.0 Integration**: Google authentication implementation
- **Database Design**: MongoDB schema design and indexing strategies

### Frontend Development (AI Assisted)
- **Modern React Development**: Understanding of Next.js and React patterns
- **TypeScript**: Type-safe development practices
- **Responsive UI Design**: Tailwind CSS implementation
- **Form Handling**: React Hook Form and validation
- **API Integration**: Frontend-backend communication

### Database & DevOps
- **MongoDB**: Database design, optimization, and cloud integration
- **MongoDB Atlas**: Cloud database management and configuration
- **CORS Configuration**: Cross-origin request handling
- **Environment Management**: Application properties and configuration

## 💼 Business Logic

The application handles core job portal functionalities:
- User registration and authentication flow
- Job posting creation and management
- Search and filtering capabilities
- User profile management
- Security and data protection

## 📈 Scalability Considerations

- Stateless JWT authentication for horizontal scaling
- NoSQL database for flexible data models
- Component-based frontend architecture
- RESTful API design for service separation
- CORS configuration for multiple frontend deployments

## 👨‍💻 Developer

**Saurabh** - Java Backend Developer
- **Primary Focus**: Spring Boot backend development with MongoDB integration
- **Full-Stack Capability**: End-to-end application development
- **Frontend**: Built with assistance from Cursor AI

## 🛠️ Technology Stack

**Backend Technologies (Core Expertise):**
- Spring Boot, Spring Security, Spring Data MongoDB
- JWT Authentication, Google OAuth 2.0
- MongoDB Atlas, Java 17+
- RESTful API Design, Exception Handling

**Frontend Technologies (AI Assisted):**
- Next.js, TypeScript, React
- Tailwind CSS, Headless UI, Lucide React
- React Hook Form, Zod Validation, Axios

**Development Tools:**
- Git version control
- MongoDB Atlas cloud database
- Cursor AI for frontend development assistance

---

*This project demonstrates my expertise in Java backend development with Spring Boot, while showcasing the ability to work with modern development tools and AI-assisted frontend development.*
