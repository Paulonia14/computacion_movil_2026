import * as React from "react";
import {View, Text, TouchableOpacity, StyleSheet, Pressable} from "react-native";
import { Feather } from "@expo/vector-icons";
import { BottomSheetModal } from "@gorhom/bottom-sheet";

function CheckMark({id, completed, toggleTodo}) {
    async function toggle() {
        const response = await fetch(`http://localhost:8080/todos/${id}`, {
        method: "PUT",
        body: JSON.stringify({
            value: completed ? false : true,
        }),
        });
        const data = await response.json();
        toggleTodo(id);
        console.log(data);
    } 
    return (
        <Pressable 
            onPress={toggle}
            style={[
                styles.checkMark,
                {backgroundColor: completed === 0 ? "#1B9CFC" : "#E9E9EF"},
            ]}
        ></Pressable>
    )
}

export default function Task ({
    id,
    title,
    shared_with_id,
    completed,
    clearTodo,
    toggleTodo,
}) {
    const [isDeleteActive, setIsDeleteActive] = React.useState(false);
    const bottomSheetModalRef = React.useRef(null);
    const sharedBottomSheetRef = React.useRef(null);
    const snapPoints = ["25%", "48%", "75%"];
    const snapPointsShared = ["40%"];

    function handlePresentModal() {
        bottomSheetModalRef.current?.present();
    }

    function handlePresentShared() {
        sharedBottomSheetRef.current?.present();
    }

    async function deleteTodo() {
        const response = await fetch(`http://localhost:8080/todos/${id}`, {
            method: "DELETE",
        });
        clearTodo(id);
        console.log(response.status);
    }

    return (
        <TouchableOpacity
            onLongPress={() => setIsDeleteActive(true)}
            onPress={() => setIsDeleteActive(false)}
            activeOpacity={0.8}
            style={styles.container}
            >
            <View style={styles.containerTextCheckBox}>
                <CheckMark id={id} completed={completed} toggleTodo={toggleTodo}/>
                <Text style={styles.text}>{title}</Text>
            </View>
            {shared_with_id !== null ? (
                <Feather
                    onPress={handlePresentShared}
                    name="users"
                    size={20}
                    color="#383839"
                />
                ) : (
                <Feather
                    onPress={handlePresentModal}
                    name="share"
                    size={20}
                    color="#383839"
                />
            )}
            {isDeleteActive && (
                <Pressable onPress={deleteTodo} style={styles.deleteButton}>
                    <Text style={{ color: "white", fontWeight: "bold" }}>X</Text>
                </Pressable>
            )}
            <BottomSheetModal
                ref={bottomSheetModalRef}
                snapPoints={snapPointsShared}
                backgroundStyle={{ borderRadius: 50 , borderWidth: 4}}
                >
                    <SharedTodoModalContent 
                    id={id}
                    title={title}
                    shared={shared_with_id}
                    completed={completed}
                    />
            </BottomSheetModal>
            <BottomSheetModal
                ref={BottomSharedModalRef}
                index={2}
                snapPoints={snapPoints}
                backgroundStyle={{ borderRadius: 50 , borderWidth: 4}}
                >
                    <TodoModalContent id={id} title={title} />
                </BottomSheetModal>
        </TouchableOpacity>
    );
}

const styles = StyleSheet.create({
    container: {
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "space-between",
        padding: 15,
        backgroundColor: "#fff",
        borderRadius: 10,
        marginBottom: 10,
    },
    containerTextCheckBox: {
        flex: 1,
        flexDirection: "row",
        alignItems: "center",
    },
    contentContainer: {
        flex: 1,
        alignItems: "center",
        paddingHorizontal: 15,
    },
    row: {
        width: "100%",
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "space-between",
        marginVertical: 10,
    },
    title: {
        fontWeight: "900",
        letterSpacing: 0.5,
        fontSize: 16,
    },
    subtitle: {
        color: "#101318",
        fontSize: 14,
        fontWeight: "bold",
    },
    description: {
        color: "#56636F",
        fontSize: 13,
        fontWeight: "normal",
        width: "100%",
    },
})
