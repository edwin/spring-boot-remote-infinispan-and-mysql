# Spring Boot with Infinispan and MySQL

This project demonstrates the integration of Spring Boot with Infinispan for caching and MySQL for data persistence.

## Database Structure

The application uses the following database table structure:

```sql
create table test_db.t_employee
(
    gender    varchar(1)   null,
    id        bigint auto_increment
        primary key,
    firstname varchar(100) null,
    lastname  varchar(100) null
);
```

## API Endpoints

The application provides the following REST endpoints:

### Get Employee by ID
```bash
curl -X GET http://localhost:8080/employee/1
```

### Find Employees by First Name and Last Name
```bash
curl -X GET http://localhost:8080/employee/find-by-firstname-and-lastname/John/Doe
```

### Find Employees by Gender
```bash
curl -X GET http://localhost:8080/employee/find-by-gender/M
```

### Get All Cache Keys and its content from Cache
```bash
curl -X GET http://localhost:8080/employee/get-all-cache
```

## Configuration

The application is configured to connect to:
- MySQL database at `localhost:3306/test_db`
- Infinispan server at `localhost:11222`

You can modify these settings in the `application.properties` file.

### Infinispan Cache Configuration
```yaml
query-cache: 
  replicatedCache: 
    mode: "SYNC"
    statistics: "true"
    encoding: 
      mediaType: "application/x-jboss-marshalling"
```