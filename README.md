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
<img width="641" alt="image" src="https://github.com/user-attachments/assets/1f45d985-1e2d-45f8-ad1a-54141312231b" />
<img width="767" alt="image" src="https://github.com/user-attachments/assets/3d7d293f-0a43-4ce5-a95e-794c44ee88c1" />



✅ **Staff & Customer Assignment**  
- Efficiently assign **buildings and customers** to staff members for **structured management**.
<img width="870" alt="image" src="https://github.com/user-attachments/assets/d0c61245-cd68-49fa-b38e-58acbf1c0b04" />

<img width="644" alt="image" src="https://github.com/user-attachments/assets/5d020587-5bbc-4196-9582-f674d95c3069" />

<img width="514" alt="image" src="https://github.com/user-attachments/assets/d4a59bc9-3164-4826-973f-bccfbe9ded69" />


✅ **Robust Transaction Tracking**  
- Implement detailed **transaction management** to track **customer operations and property assignments**.
<img width="646" alt="image" src="https://github.com/user-attachments/assets/a746a670-ea88-427f-9251-ee4ccd8a4660" />

<img width="689" alt="image" src="https://github.com/user-attachments/assets/031aa5f1-34ec-4f3e-af2d-eb1f158bca86" />



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
