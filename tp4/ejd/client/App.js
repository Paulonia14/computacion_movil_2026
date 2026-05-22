import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";
import { SafeAreaView } from "react-native-web";
import Task from "./components/Task";
import { BottomSheetModal } from "@gorhom/bottom-sheet";
import { BottomSheetModalProvider } from "@gorhom/bottom-sheet";
import { useEffect, useState } from "react";
import InputTask from "./components/InputTask";

export default function App() {
    const [todos, setTodos] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);
    
    async function fetchData() {
        const response = await fetch("http://localhost:8080/todos/1");
        const data = await response.json();
        setTodos(data);
    }

    // Función para eliminar un todo de la lista (los muestra filtrados sin el eliminado)
    function clearTodo(id) {
        setTodos(todos.filter((todo) => todo.id !== id));
    }
    // Función para cambiar el estado de un todo (completado o no completado)
    function toggleTodo(id) {
        setTodos(
            todos.map((todo) =>
                todo.id === id
                    ? { ...todo, completed: todo.completed === 1 ? 0 : 1 } 
                    : todo
            )
        );
    }

    return (
        <BottomSheetModalProvider>
            <SafeAreaView style={styles.container}>
                <FlatList // Lista con scroll view
                    data={todos}
                    keyExtractor={(todo) => todo.id}
                    renderItem={({ item }) => (
                        <Task{...item} toggleTodo={toggleTodo} clearTodo={clearTodo} />
                    )}
                    ListHeaderComponent={() => <Text style={styles.title}> Hoy </Text>}
                    ContentContainerStyle={styles.contentContainerStyle}
                />
                <InputTask todos={todos} setTodos={setTodos} />
            </SafeAreaView>
            <StatusBar style="auto" />
        </BottomSheetModalProvider>
    );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#E9E9EF",
    alignItems: "center",
    justifyContent: "center",
  },
  contentContainerStyle: {
    padding: 20,
  },
    title: {
        fontSize: 30,
        fontWeight: "800",
        marginBottom: 15,
    },
});