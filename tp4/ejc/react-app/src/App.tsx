import Card, { CardBody } from "./components/Card.tsx";
import List from "./components/List.tsx";
function App() {
  const list = ["item uno", "item dos", "item tres"];
  return (
    <Card>
      <CardBody title={"Hola malditisimo mundo"} text={"texto tesxto texto"} />
      <List data={list} />
    </Card>
  );
}

export default App;
