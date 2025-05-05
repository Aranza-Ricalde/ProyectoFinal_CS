# Task Management Project

## 📌 Overview

This is a Java-based task management application that includes both graphical (GUI) and console interfaces. It allows users to create, read, update, and delete (CRUD) tasks, as well as search, filter, and sort them.

The project follows the **Model-View-Controller (MVC)** pattern and uses **Gson** for JSON persistence.

---

## ✅ Requirements

- **Java Development Kit (JDK):** Version 8 or higher  
- **Maven:** Version 3.6 or higher  
- **Operating System:** Windows, macOS, or Linux

---

## 📁 Project Structure

```
project-root/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── main/
│   │   │   │   │   ├── Main.java
│   │   │   │   │   ├── MainConsole.java
│   │   │   ├── controlador/
│   │   │   ├── modelo/
│   │   │   ├── vista/
├── pom.xml
├── README.md

```
---

## ⚙️ Setup

### 1. Clone or Download the Project

Using Git:
```bash
git clone <repository-url>
````

Or download and extract the ZIP manually.

### 2. Install Dependencies

Ensure Maven is installed:

```bash
mvn --version
```

Then:

```bash
cd project-root
mvn clean install
```

This will download dependencies (like Gson) and build the project.

---

## 🛠️ Compilation

To compile the source code, run:

```bash
mvn compile
```

Compiled files will be placed in `target/classes`.

---

## 🚀 Execution

There are two available entry points:

* **GUI Mode** — launches a graphical interface
* **Console Mode** — launches a menu-driven terminal interface

### ▶️ Run GUI Mode

```bash
mvn exec:java -Dexec.mainClass="com.main.Main"
```

This opens the main GUI window for task management.

### ▶️ Run Console Mode

```bash
mvn exec:java -Dexec.mainClass="com.main.MainConsole"
```

This starts the terminal-based interface.

---

## 📦 Dependencies

* **Gson** — for JSON serialization/deserialization (included via Maven)
* **Swing** — GUI components (part of the JDK)

---

## 📝 Notes

* Tasks are stored in a `tareas.json` file located in the project root.
* Ensure your JDK and Maven installations are properly configured.
* If you encounter issues, verify the `JAVA_HOME` environment variable is set correctly.

