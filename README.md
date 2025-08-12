# JobZygo - Job Portal Platform

JobZygo is a modern full-stack job portal application that connects job seekers with employers. Built with Spring Boot backend and Next.js frontend, it provides a seamless experience for posting, searching, and managing job opportunities.

## 🚀 Features

- **User Authentication**: Secure login/signup with JWT tokens and Google OAuth integration
- **Job Management**: Create, view, edit, and delete job postings
- **Advanced Search**: Search jobs by location, skills, experience level, and company
- **User Profiles**: Manage user accounts with different user types (job seekers, employers)
- **Responsive Design**: Mobile-friendly interface built with Next.js and Tailwind CSS
- **Real-time Updates**: Dynamic job listings with real-time search capabilities

## 🏗️ Architecture

### Backend (Spring Boot)
- **Framework**: Spring Boot 3.x
- **Database**: MongoDB Atlas
- **Authentication**: JWT + Google OAuth 2.0
- **Security**: Spring Security
- **API**: RESTful endpoints with CORS enabled

### Frontend (Next.js)
- **Framework**: Next.js 15.4.6 with App Router
- **Language**: TypeScript
- **Styling**: Tailwind CSS v4
- **UI Components**: Headless UI, Lucide React icons
- **Forms**: React Hook Form with Zod validation
- **HTTP Client**: Axios

## 📁 Project Structure

```
JobZygo/
├── README.md
├── src/                          # Backend (Spring Boot)
│   ├── main/
│   │   ├── java/com/tony/JobZygo/
│   │   │   ├── config/           # Security & configuration
│   │   │   ├── controller/       # REST API controllers
│   │   │   ├── entity/           # MongoDB entities
│   │   │   ├── filter/           # JWT filters
│   │   │   ├── repo/             # Repository interfaces
│   │   │   ├── service/          # Business logic
│   │   │   └── JobZygoApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                     # Backend tests
└── frontend/                     # Frontend (Next.js)
    ├── src/
    │   ├── app/                  # Next.js App Router
    │   ├── components/           # React components
    │   ├── lib/                  # Utility functions
    │   └── types/                # TypeScript types
    ├── public/                   # Static assets
    ├── package.json
    └── README.md
```

## 🛠️ Installation & Setup

### Prerequisites
- Java 17+
- Node.js 18+
- MongoDB Atlas account
- Google OAuth credentials

### Backend Setup

1. **Configure MongoDB**:
   - Create a MongoDB Atlas cluster
   - Update `src/main/resources/application.properties` with your database URI

2. **Configure Google OAuth**:
   - Create a Google OAuth application
   - Update the client ID and secret in `application.properties`

3. **Run the backend**:
   ```bash
   # If using Maven
   ./mvnw spring-boot:run
   
   # If using Gradle
   ./gradlew bootRun
   ```

   The backend will start on `http://localhost:8080`

### Frontend Setup

1. **Navigate to frontend directory**:
   ```bash
   cd frontend
   ```

2. **Install dependencies**:
   ```bash
   npm install
   ```

3. **Run the development server**:
   ```bash
   npm run dev
   ```

   The frontend will start on `http://localhost:3000`

## 🔧 Configuration

### Backend Configuration
Update `src/main/resources/application.properties`:

```properties
spring.application.name=JobZygo
spring.data.mongodb.uri=your-mongodb-uri
spring.data.mongodb.database=your-database-name
spring.data.mongodb.auto-index-creation=true
spring.security.oauth.client.registration.google.client-id=your-google-client-id
spring.security.oauth.client.registration.google.client-secret=your-google-client-secret
```

### Frontend Configuration
The frontend is configured to connect to the backend at `http://localhost:8080`. Update API endpoints in the frontend code if your backend runs on a different port.

## 📡 API Endpoints

### Authentication
- `POST /auth/login` - User login
- `POST /auth/signup` - User registration
- `GET /auth/oauth/google` - Google OAuth login

### Job Management
- `GET /jobzygo/jobs` - Get all jobs
- `POST /jobzygo/jobs` - Create a new job post
- `GET /jobzygo/jobs/{id}` - Get job by ID
- `PUT /jobzygo/jobs/{id}` - Update job post
- `DELETE /jobzygo/jobs/{id}` - Delete job post
- `GET /jobzygo/search/{keyword}` - Search jobs by keyword

### User Management
- `GET /users/profile` - Get user profile
- `PUT /users/profile` - Update user profile

## 🎯 Key Entities

### JobPost
```java
{
  "id": "ObjectId",
  "profile": "string",
  "exp": "number",
  "jobType": "string", 
  "companyName": "string",
  "desc": "string",
  "salary": "number",
  "location": "string",
  "techs": ["string"]
}
```

### User
```java
{
  "id": "string",
  "username": "string",
  "email": "string",
  "password": "string",
  "userType": "string"
}
```

## 🔒 Security Features

- JWT-based authentication
- Google OAuth 2.0 integration
- Password encryption
- CORS configuration for frontend-backend communication
- Protected routes and API endpoints

## 🚀 Deployment

### Backend Deployment
- Package the application: `./mvnw clean package`
- Deploy the JAR file to your preferred cloud platform
- Ensure environment variables are properly configured

### Frontend Deployment
- Build the application: `npm run build`
- Deploy to Vercel, Netlify, or your preferred hosting platform
- Update API endpoints to point to your deployed backend

## 🧪 Testing

### Backend
```bash
# Run backend tests
./mvnw test
```

### Frontend
```bash
# Run frontend tests
cd frontend
npm test
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/new-feature`
3. Commit your changes: `git commit -am 'Add new feature'`
4. Push to the branch: `git push origin feature/new-feature`
5. Submit a pull request

## 📜 License

This project is licensed under the MIT License.

## 👨‍💻 Author

**Tony** - Full Stack Developer
- Backend: Spring Boot application with MongoDB integration
- Frontend: Built with assistance from Cursor AI

## 🛠️ Tech Stack

**Backend:**
- Spring Boot
- Spring Security
- Spring Data MongoDB
- JWT
- Google OAuth 2.0

**Frontend:**
- Next.js
- TypeScript
- Tailwind CSS
- React Hook Form
- Zod
- Axios

**Database:**
- MongoDB Atlas

**Development Tools:**
- Maven/Gradle
- ESLint
- PostCSS
