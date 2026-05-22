CREATE DATABASE ejercicio_d;

USE ejercicio_d;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS todos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    completed BOOLEAN DEFAULT FALSE,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE shared_todos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    todo_id INT,
    user_id INT,
    shared_with_id INT,
    FOREIGN KEY (todo_id) REFERENCES todos(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users (name, email, password) VALUES ('Beto', 'user1@example.com', 'password1');
INSERT INTO users (name, email, password) VALUES ('Alberto', 'user2@example.com', 'password2');

INSERT INTO todos (title, user_id) 
VALUES 
('Salir a caminar', 1),
('Ir al supermercado', 1),
('Lavar la ropa', 1),
('Pagar las cuentas', 1),
('Leer un libro', 1),
('Hacer ejercicio', 1),
('Cocinar', 1),
('Ir al parque',1),
('Limpiar la casa', 1);

INSERT INTO shared_todos (todo_id, user_id, shared_with_id) 
VALUES (1, 1, 2);

-- consulta para obtener las tareas de un usuario específico, incluyendo las compartidas con él
SELECT todos.*, shared_todos.shared_with_id
FROM todos
LEFT JOIN shared_todos ON todos.id = shared_todos.todo_id
WHERE todos.user_id = [user_id] OR shared_todos.shared_with_id = [user_id];