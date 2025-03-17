# **Estate Management System**  

## **📌 Project Overview**  
The **Estate Management System** is a comprehensive web application designed to streamline estate operations, including **user authentication, account administration, customer relationship management (CRM), and property assignments to staff members**.  

## **🛠️ Tech Stack**  
- **Languages:** Java, JavaScript  
- **Frameworks & Libraries:** Spring Boot, JSP, jQuery, Ajax  
- **Database:** MySQL  

## **🚀 Key Features**  
✅ **Advanced Property Management**  
- Full **CRUD operations** with **search and filtering** across **16 different property attributes**.  

✅ **Staff & Customer Assignment**  
- Efficiently assign **buildings and customers** to staff members for **structured management**.
![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/6a240a7f-e5de-4ee3-b7d3-b1b0603ecf5a/1fa38542-625e-42b7-875c-0e476acf4778/Untitled.png)
![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/6a240a7f-e5de-4ee3-b7d3-b1b0603ecf5a/10a4640f-a42f-4754-a714-ef3a10a9cba5/Untitled.png)


✅ **User Authentication & Role-Based Access**  
- Secure **user login & role management** for different levels of access (Admin, Staff, Customer).  

✅ **Robust Transaction Tracking**  
- Implement detailed **transaction management** to track **customer operations and property assignments**.
![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/6a240a7f-e5de-4ee3-b7d3-b1b0603ecf5a/4da90cbf-d928-45c7-9658-e9c2d783ac19/Untitled.png)
![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/6a240a7f-e5de-4ee3-b7d3-b1b0603ecf5a/f7e2da80-228d-413b-a0c2-94b12e4411c5/Untitled.png)
![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/6a240a7f-e5de-4ee3-b7d3-b1b0603ecf5a/0347cead-c5f8-4c47-96fd-02f448c35b3e/Untitled.png)

## **⚙️ Installation & Setup**  
### **🔹 Prerequisites**  
Ensure you have the following installed:  
- **Java 1.8**  
- **Maven**  
- **MySQL**  
- **Apache Tomcat**


### **🔹 Setup Instructions**  
1️⃣ **Clone the repository:**  
```sh
git clone https://github.com/yourusername/estate-management.git
cd estate-management
```
2️⃣ **Configure the database:**  
- Create a MySQL database named **`estateadvance`**.  
- Update `application.properties` in **Spring Boot** with your **MySQL credentials**:  
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/estate_db
  spring.datasource.username=root
  spring.datasource.password=yourpassword
  ```

3️⃣ **Run the application:**  
```sh
mvn spring-boot:run
```
4️⃣ **Access the application:**  
- Open **`http://localhost:8080`** in your browser.  
