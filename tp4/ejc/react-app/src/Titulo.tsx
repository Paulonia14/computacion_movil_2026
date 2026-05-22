function Titulo(props: { name: string }) {
  if (props.name != "") {
    return <h1>Hola {props.name}</h1>;
  }
  return <h1>Hola Mundo!</h1>;
}

export default Titulo;
