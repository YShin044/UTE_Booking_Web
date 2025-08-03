# HCMUTE Soccer Field Booking Web Application

<p align="center">
  <em>A secure and user-friendly web application for booking and managing soccer fields, developed as a final project.</em>
  <br/><br/>
  <!-- Hãy chụp ảnh màn hình giao diện đẹp nhất của trang web và chèn vào đây -->
  <img src="https://github.com/YShin044/UTE_Football_Web/blob/master/assets/UTE_Web.jpg?raw=true" alt="Website Screenshot">
</p>

<!-- HÀNG HUY HIỆU CÔNG NGHỆ -->
<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/Thymeleaf-005C0F?style=for-the-badge&logo=thymeleaf&logoColor=white" alt="Thymeleaf"/>
  <img src="https://img.shields.io/badge/OWASP-000000?style=for-the-badge&logo=owasp&logoColor=white" alt="OWASP"/>
  <img src="https://img.shields.io/badge/Security-Focused-blue?style=for-the-badge" alt="Security-Focused"/>
</p>
<!-- KẾT THÚC HÀNG HUY HIỆU -->

## ► Project Overview

This project is a fully functional web application designed to streamline the process of booking soccer fields. **Developed by our team of sophomore students**, it was built using the **Java Spring Boot** framework, a **MySQL** database, and **Thymeleaf** for dynamic server-side rendering.

A primary focus of this project was the implementation of **key security principles** to create a safe and reliable platform for users and administrators.

### Key Features:
- **User & Admin Roles:** The application supports two distinct user roles (standard `User` and `Admin`) with different levels of access and functionality.
- **Field Management:** Administrators can add, edit, and manage information about soccer fields.
- **Booking System:** Users can browse available fields, select time slots, and make bookings.
- **Secure Authentication:** A robust login system to authenticate users before granting access.
- **Security by Design:** Proactive measures were implemented throughout the development lifecycle to mitigate common web vulnerabilities.

---

## ► Core Technologies & Security Implementation

This project demonstrates practical application of modern web development and security concepts.

<table>
  <tr>
    <td valign="top" width="33%">
      <h4>Backend</h4>
      <ul>
        <li><b>Framework:</b> Java Spring Boot</li>
        <li><b>Database:</b> MySQL with Spring Data JPA</li>
        <li><b>Dependencies:</b> Spring Web, Spring Security, Spring Data JPA</li>
      </ul>
    </td>
    <td valign="top" width="33%">
      <h4>Frontend</h4>
      <ul>
        <li><b>Templating Engine:</b> Thymeleaf for server-side rendering</li>
        <li><b>Styling:</b> HTML5 & CSS3</li>
        <li><b>Client-side Scripting:</b> JavaScript</li>
      </ul>
    </td>
    <td valign="top" width="33%">
      <h4>Security Features</h4>
      <ul>
        <li><b>Authentication & Authorization:</b> Implemented with <b>Spring Security</b>.</li>
        <li><b>Role-Based Access Control (RBAC):</b> Restricts access to specific pages and functions based on user roles (e.g., only Admins can manage fields).</li>
        <li><b>Password Hashing:</b> User passwords are securely stored using a strong hashing algorithm.</li>
        <li><b>OWASP Top 10 Mitigation:</b>
            <ul>
                <li><b>SQL Injection:</b> Prevented by using Spring Data JPA and parameterized queries.</li>
                <li><b>Cross-Site Scripting (XSS):</b> Mitigated by Thymeleaf's automatic output escaping.</li>
            </ul>
        </li>
        <li><b>Secure Session Management:</b> Handled by Spring Security to protect against session hijacking.</li>
      </ul>
    </td>
  </tr>
</table>

---

## ► How to Run This Project

1.  **Prerequisites:**
    *   Java Development Kit (JDK) 17 or higher.
    *   Apache Maven.
    *   A running MySQL server instance.
2.  **Clone the repository:**
    ```bash
    git clone https://github.com/YShin044/UTE_Football_Web.git
    cd DoAnCuoiKi
    ```
3.  **Configure the database:**
    *   Open the configuration file located at `src/main/resources/application.properties`.
    *   Update the following properties to match your local MySQL environment:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/ql_dat_san_bong
        spring.datasource.username=your_mysql_username
        spring.datasource.password=your_mysql_password
        ```
    *   **Important:** The application requires a database schema. The name of the schema is defined at the end of the `spring.datasource.url` property (in this case, `ql_dat_san_bong`).
    *   Before running the application, ensure this database exists on your MySQL server. You can create it with the following SQL command:
        ```sql
        CREATE DATABASE ql_dat_san_bong;
        ```
4.  **Build and run the application:**
    ```bash
    mvn spring-boot:run
    ```
5.  **Access the application:**
    *   Open your web browser and navigate to `http://localhost:9009` (or ur port).

---

*This project, developed by our team during our second year of university, showcases our early passion and ability to collaboratively build full-stack web applications with a strong emphasis on security.*
