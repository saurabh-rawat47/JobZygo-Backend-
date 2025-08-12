# JobZygo

A full-stack job search and recruitment platform built with Spring Boot and Next.js.

## About

JobZygo is a comprehensive job search and recruitment platform that connects job seekers with employers. The project features a Spring Boot backend with MongoDB database and a Next.js frontend built with TypeScript.

## Features

- User authentication with Google OAuth 2.0
- MongoDB database for data storage
- RESTful API with Spring Boot
- Responsive frontend with Next.js and React
- TypeScript for type safety
- Form validation with React Hook Form and Zod

## Tech Stack

### Backend
- Spring Boot (Java)
- MongoDB
- OAuth 2.0 (Google)
- Maven

### Frontend
- Next.js 15.4.6
- React 19.1.0
- TypeScript
- Tailwind CSS
- React Hook Form
- Zod
- Axios

## Project Structure

```
JobZygo/
├── frontend/          # Next.js frontend application
└── src/              # Spring Boot backend
    └── main/
        ├── java/com/tony/JobZygo/
        │   ├── config/
        │   ├── controller/
        │   ├── entity/
        │   ├── filter/
        │   ├── repo/
        │   ├── service/
        │   └── JobZygoApplication.java
        └── resources/
            └── application.properties
```

## Getting Started

### Backend Setup
1. Ensure you have Java 17+ installed
2. Configure MongoDB connection in `application.properties`
3. Set up Google OAuth credentials
4. Run: `mvn spring-boot:run`

### Frontend Setup
1. Navigate to `frontend/` directory
2. Install dependencies: `npm install`
3. Run development server: `npm run dev`

## Configuration

Update `src/main/resources/application.properties` with your:
- MongoDB connection string
- Google OAuth client ID and secret

## Development

- Backend runs on: `http://localhost:8080`
- Frontend runs on: `http://localhost:3000`

## Author

Tony - Fresher Developer

---

*Built with Spring Boot, Next.js, and Cursor AI assistance for frontend development.*
