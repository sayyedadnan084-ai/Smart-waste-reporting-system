# CleanCity — Real Online Version

This is the **real shared version** of the Smart Waste Reporting System: React frontend + Java Servlet backend + MySQL database. Complaints are stored in MySQL, so a user laptop and an admin laptop can see the same complaints online.

## Demo admin
Email: admin@cleancity.com
Password: admin123

## Local setup
1. Install JDK 11+, Maven, MySQL 8 and Tomcat 10.
2. Run `database/schema.sql` in MySQL.
3. In backend, set `DB_URL`, `DB_USER`, `DB_PASSWORD` if your MySQL details differ.
4. Build backend: `mvn clean package`.
5. Copy `backend/target/cleancity.war` to Tomcat `webapps/` and start Tomcat.
6. For frontend, `cd frontend && npm install && npm run dev`.
7. Set `VITE_API=http://localhost:8080/cleancity` if frontend and backend run separately.

## Online deployment
For a real public URL, deploy the Java/Tomcat backend as a web service/container and MySQL as a managed/private database. Netlify can host the React frontend, but it cannot execute the Java Servlet backend itself. If frontend and backend use different domains, configure the API URL and HTTPS/CORS correctly.

## Important college-project features
- User registration/login
- Waste complaint with photo, location and description
- Shared MySQL complaint storage
- User complaint history/status tracking
- Admin dashboard
- Admin status update: Pending → In Progress → Completed
- Java Servlet backend
- React frontend
