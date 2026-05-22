import type { ReactNode } from "react";

type ButtonProps = {
  isPressed: boolean;
  children: ReactNode;
  onClick?: () => void;
};

function Button({ isPressed, children, onClick }: ButtonProps) {
  return (
    <button
      type="button"
      className={`${isPressed ? "btn btn-secondary" : "btn btn-primary"}`}
      style={{
        height: "35px",
        width: "150px",
      }}
      disabled={isPressed}
      onClick={onClick}
    >
      {isPressed ? "Cargando..." : children}
    </button>
  );
}

type ButtonTextProps = {
  text: string;
};

export function ButtonText({ text }: ButtonTextProps) {
  return <p>{text}</p>;
}

export default Button;
