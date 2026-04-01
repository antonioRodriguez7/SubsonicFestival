export async function getEntradas() {
  console.log("LLAMANDO A ENTRADAS"); // 👈 añade esto

  const response = await fetch("http://localhost:8080/api/tickets/available");

  if (!response.ok) {
    throw new Error("Error al obtener entradas");
  }

  return await response.json();
}