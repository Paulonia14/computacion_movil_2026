import Card, { CardBody } from "./components/Card.tsx";
import List from "./components/List.tsx";
import Button, { ButtonText } from "./components/Button.tsx";
import { useState } from "react";
function App() {
  const list: string[] = ["Colapinto", "Hamilton", "Russel"];
  const handleSelect = (element: string) => {
    console.log(element);
  };
  const [isPressed, setIsPressed] = useState(false);
  const handleOnClick = () => {
    setIsPressed(!isPressed);
  };

  return (
    <Card>
      <CardBody title={"Hola malditisimo mundo"} text={"texto tesxto texto"} />
      {list.length !== 0 && <List data={list} onSelect={handleSelect} />}
      <Button onClick={() => handleOnClick()} isPressed={isPressed}>
        <ButtonText text={"Botón"}></ButtonText>
      </Button>
    </Card>
  );
}

export default App;
